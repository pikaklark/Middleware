import org.omg.CORBA.Any;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Request;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ServantTestImplB extends BPOA{

    @Override
    public void b1(Any params, String serverRef, String objectName) {
        System.out.println("B");
        System.out.println(serverRef);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            NamingContextExt namingContext = NamingContextExtHelper.narrow(_orb().resolve_initial_references("NameService"));
            // Sending callback to server
            Request req = namingContext.resolve(namingContext.to_name(serverRef))._request("callback");
            Any any = req.add_in_arg();
            any.insert_string(objectName);
            req.send_oneway();

            // Sending result to client
            TaskParam param = TaskParamHelper.extract(params);
            req = namingContext.resolve(namingContext.to_name(param.clientRef))._request("callback");
            any = req.add_in_arg();
            any.insert_long(param.id);
            any = req.add_in_arg();
            any.insert_any(param.params);
            req.send_oneway();
        } catch (CannotProceed | InvalidName | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound e) {
            e.printStackTrace();
        }


    }
}
