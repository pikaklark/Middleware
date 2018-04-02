package main;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA_2_3.ORB;
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

import impl.ClientImpl;


public class ClientMain {


	public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, AdapterInactive {

		String nameClient;
		if(args.length>0) {
			nameClient=args[0];
		}
		else {
			//nameClient="DefaultName"+System.currentTimeMillis();
			nameClient="root";
		}

		ORB orb = (ORB) ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

		NamingContextExt namingContext =NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

		ClientImpl client = new ClientImpl(nameClient);

		byte[] clientId = rootPOA.activate_object(client);
		org.omg.CORBA.Object clientRef = rootPOA.id_to_reference(clientId);
		NameComponent[] nameClientComponent = namingContext.to_name(nameClient);

		NameComponent[] pereClient = namingContext.to_name("Client");
		NamingContext nodePere = null;


		try {
			nodePere=namingContext.bind_new_context(pereClient);
		} catch (NotFound | AlreadyBound | CannotProceed e) {
			nodePere=NamingContextExtHelper.narrow((namingContext).resolve_str("Client"));
		}
		
		try {
			nodePere.bind(nameClientComponent, clientRef );
		} catch (AlreadyBound e) {
			System.err.println("Ce nom de client existe déjà!!!");
			nodePere.rebind(nameClientComponent, clientRef );
		}
		
		System.out.println("Le client:"+ nameClient+" est prêt à être utilisé");
		rootPOA.the_POAManager().activate();
		orb.run();

	}
}
