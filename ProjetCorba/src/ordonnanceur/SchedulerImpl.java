import org.omg.CORBA.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SchedulerImpl extends SchedulerPOA {

    private ORB orb;
    private Thread refreshInterface;
    private ConcurrentHashMap<String, ServantInterface> servants = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    private static int REFRESH_RATE = 10 * 60 * 60 * 1000;

    private static class ServantInterface {

        private List<String> interfaces = new ArrayList<>();
        private boolean available = true;

        public ServantInterface() {

        }

        public void addInterface(String nameInterface) {
            if (!interfaces.contains(nameInterface)) {
                interfaces.add(nameInterface);
            }
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public boolean containsAnInterface(String nameInterface) {
            return interfaces.contains(nameInterface);
        }

        @Override
        public String toString() {
            return interfaces.stream().collect(Collectors.joining(", ", "[", "]"));
        }
    }

    public void explore(NamingContext namingContext, String accumulator) throws NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        BindingListHolder blh = new BindingListHolder();
        BindingIteratorHolder bih = new BindingIteratorHolder();

        namingContext.list(Integer.MAX_VALUE, blh, bih);


        for (Binding b : blh.value) {
            System.out.println(b.binding_name[0].id);

            if (b.binding_type == BindingType.ncontext) {
                if(b.binding_name[0].id.equals("Server") || b.binding_name[0].id.equals("Client"))
                    continue;
                NamingContext nextContext= NamingContextExtHelper
                        .narrow(namingContext.resolve(b.binding_name));
                explore(nextContext, b.binding_name[0].id);
            } else {
                servants.computeIfAbsent(b.binding_name[0].id, (x) -> new ServantInterface())
                        .addInterface(accumulator);
            }
        }
    }

    public SchedulerImpl(ORB orb){
        this.orb = orb;

        refreshInterface = new Thread( () -> {
            try {
                NamingContextExt namingContext = NamingContextExtHelper.narrow(orb
                        .resolve_initial_references("NameService"));
                for(;;) {
                    explore(namingContext, "");
                    Thread.sleep(REFRESH_RATE);
                }
            } catch (CannotProceed | InvalidName | NotFound | org.omg.CosNaming.NamingContextPackage.InvalidName e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        refreshInterface.start();
    }


    private List<String> getFreeList(NamingContextExt namingContext, String interfaceName){
        return servants.keySet().stream()
                .filter(objectName -> servants.get(objectName).containsAnInterface(interfaceName))
                .filter(objectName -> servants.get(objectName).isAvailable())
                .collect(Collectors.toList());

    }

    public void call(TaskParam param){
        System.err.println("DEBUT DE LA METHODE");
        NamingContextExt namingContext = null;
        try {
            namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }

        Request req = null;
        org.omg.CORBA.Object resolve = null;
        synchronized (lock) {
            List<String> objects = getFreeList(namingContext, param.interfaceName);


            if (objects.isEmpty()) {
                //TODO envoyer erreur au client
                System.out.println("Aucun servant dispo");
                sendNullToClient(param.clientRef, param.id, namingContext);
                return;
            }

            for (String object : objects) {
                try {
                    servants.get(object).setAvailable(false);
                    resolve = namingContext.resolve(namingContext.to_name(param.interfaceName + "/" + object));
                    req = resolve._request(param.methodName);
                    if (sendRequest(req, param, object))
                        return;
                    servants.remove(object);
                } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
                    servants.remove(object);
                }
            }

        }
        sendNullToClient(param.clientRef, param.id, namingContext);
    }
    private void sendNullToClient(String clientRef, int id, NamingContextExt namingContext){
        Any any;
        try {
            Request req = namingContext.resolve(namingContext.to_name(clientRef))._request("callback");
            any = req.add_in_arg();
            any.insert_long(id);
            any = req.add_in_arg();
            any.insert_any(orb.create_any());
            req.send_oneway();
        } catch (NotFound | org.omg.CosNaming.NamingContextPackage.InvalidName | CannotProceed e) {
            return;
        }

    }

    private boolean sendRequest(Request req, TaskParam param, String objectName){
        Any arg = req.add_in_arg();
        Any any = orb.create_any();
        TaskParamHelper.insert(any, param);
        arg.insert_any(any);

        // server ref
        arg = req.add_in_arg();
        arg.insert_string("Server/server");

        //objectName
        arg = req.add_in_arg();
        arg.insert_string(objectName);

        System.err.println("Invocation de la requete");
        try {
            req.send_oneway();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /*public Any request(String objectName, String interfaceName, String operatorName, Any params)  {
        System.out.println("Appel de la method request avec" + objectName);
        Any res = call(objectName, operatorName, params);
        System.out.println("Operation terminée");
        return res;
    }*/

    @Override
    public void task(TaskParam param) {
        call(param);
    }

    @Override
    public void callback(String objectName) {
        System.out.println("CAllback de " + objectName);
        servants.getOrDefault(objectName, new ServantInterface()).setAvailable(true);
    }
}
