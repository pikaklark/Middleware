import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Client {

/*    private void registerInNamingContext(POA rootPOA, String ctx, String ref, String servantId) throws InvalidName {
        NamingContextExt namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

        org.omg.CORBA.Object reference = rootPOA.id_to_reference(servantId);

        NameComponent[] name = namingContext.to_name(ctx);

        NameComponent[] nameFile = namingContext.to_name(ref);
    }*/

    static public void main(String[] args) throws IOException, org.omg.CORBA.ORBPackage.InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, InvalidName, NotFound, CannotProceed, AdapterInactive, InterruptedException {
        ORB orb = ORB.init(args, null);

        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

        ClientCallBackImpl clientCallBack = new ClientCallBackImpl();
        byte[] servantId = rootPOA.activate_object(clientCallBack);

        NamingContextExt namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

        Object reference = rootPOA.id_to_reference(servantId);

        NameComponent[] name = namingContext.to_name("Client");

        NameComponent[] nameFile = namingContext.to_name("client");

        NamingContext newContext= null;
        try {
            newContext = namingContext.bind_new_context(name);
        } catch (AlreadyBound e1){
            newContext = NamingContextExtHelper.narrow(namingContext.resolve_str("Client"));
        }

        try {
            newContext.bind(nameFile, reference);
        } catch (AlreadyBound e){
            System.err.println("Le " + name + "Deja utilise");
            newContext.rebind(nameFile, reference);
        }

        rootPOA.the_POAManager().activate();

        BufferedReader fileReader = new BufferedReader(new FileReader("ObjectRef"));
        String stringIOR = fileReader.readLine();
        fileReader.close();
        org.omg.CORBA.Object referenceScheduler = orb.string_to_object(stringIOR);

        Scheduler scheduler = SchedulerHelper.narrow(referenceScheduler);

        Any params = orb.create_any();
        params.insert_long(10000);
        TaskParam param = new TaskParam(1, "", "A", "a1", "Client/client", params);
        TaskParam param2 = new TaskParam(2, "", "A", "a2", "Client/client", params);
        TaskParam param3 = new TaskParam(3, "", "B", "b1", "Client/client", params);
        TaskParam param31 = new TaskParam(31, "", "B", "b1", "Client/client", params);
        TaskParam param32 = new TaskParam(32, "", "B", "b1", "Client/client", params);
        TaskParam param33 = new TaskParam(33, "", "B", "b1", "Client/client", params);
        TaskParam param34 = new TaskParam(34, "", "B", "b1", "Client/client", params);

        //for (int i = 0; i < 10; i++) {
        //    scheduler.task(param);
        //    scheduler.task(param2);
        //    scheduler.task(param3);
        //}

        scheduler.task(param3);
        scheduler.task(param31);
        scheduler.task(param32);
        scheduler.task(param33);
        Thread.sleep(1000);
        scheduler.task(param34);

        orb.run();
    }
}

