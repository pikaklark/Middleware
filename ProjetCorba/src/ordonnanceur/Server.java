import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Server {

    public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, FileNotFoundException, AdapterInactive, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
        ORB orb = ORB.init(args, null);

        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

        SchedulerImpl  scheduler = new SchedulerImpl(orb);
        byte[] servantId = rootPOA.activate_object(scheduler);

        NamingContextExt namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

        Object reference = rootPOA.id_to_reference(servantId);

        NameComponent[] name = namingContext.to_name("Server");

        NameComponent[] nameFile = namingContext.to_name("server");

        NamingContext newContext= null;
        try {
            newContext = namingContext.bind_new_context(name);
        } catch (AlreadyBound e1){
            newContext = NamingContextExtHelper.narrow(namingContext.resolve_str("Server"));
        }

        try {
            newContext.bind(nameFile, reference);
        } catch (AlreadyBound e){
            System.err.println("Le " + name + "Deja utilise");
            newContext.rebind(nameFile, reference);
        }

        String referenceStr = orb.object_to_string(rootPOA.id_to_reference(servantId));

        PrintWriter file = new PrintWriter("ObjectRef");
        file.println(referenceStr);
        file.close();

        rootPOA.the_POAManager().activate();

        orb.run();
    }
}
