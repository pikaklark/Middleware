import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
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


public class ServerServantTestB2 {

    public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, FileNotFoundException, AdapterInactive, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
        ORB orb = ORB.init(args, null);

        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

        ServantTestImplB servantTestImplB = new ServantTestImplB();
        byte[] servantId = rootPOA.activate_object(servantTestImplB);

        NamingContextExt namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

        Object reference = rootPOA.id_to_reference(servantId);

        NameComponent[] name = namingContext.to_name("B");

        NameComponent[] nameFile = namingContext.to_name("B2");

        NamingContext newContext= null;
        try {
            newContext = namingContext.bind_new_context(name);
        } catch (AlreadyBound e1){
            newContext = NamingContextExtHelper.narrow(namingContext.resolve_str("B"));
        }

        try {
            newContext.bind(nameFile, reference);
        } catch (AlreadyBound e){
            System.err.println("Le " + name + "Deja utilise");
            newContext.rebind(nameFile, reference);
        }

        rootPOA.the_POAManager().activate();

        orb.run();
    }
}
