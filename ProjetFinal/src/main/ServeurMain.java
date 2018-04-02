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

import impl.OrdonnanceurImpl;
import impl.ServeurImpl;

public class ServeurMain {

	
	public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, AdapterInactive {
		ORB orb = (ORB) ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		
		NamingContextExt namingContext =NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

		OrdonnanceurImpl ordonnanceur = new OrdonnanceurImpl(orb, rootPOA);
		
		byte[] ordoId = rootPOA.activate_object(ordonnanceur);
		org.omg.CORBA.Object ordoRef = rootPOA.id_to_reference(ordoId);
		NameComponent[] name = namingContext.to_name("Ordonnanceur");
		
		
		try {
			namingContext.bind(name,ordoRef);
		} catch (NotFound | CannotProceed | AlreadyBound e) {
			System.err.println("Nom : " + name + " déjà utilisé" );
			namingContext.rebind(name,ordoRef);
		}
		
		NameComponent[] pere = namingContext.to_name("Serveur");
		NamingContext nodePere = null;
		
		try {
			nodePere=namingContext.bind_new_context(pere);
		} catch (AlreadyBound e) {
			nodePere=NamingContextExtHelper.narrow((namingContext).resolve_str("Serveur"));
		}
		
		
		int NB_SERVEUR = 3;
		for(int i=0;i<NB_SERVEUR;i++) {
			String nameServeur = "Serveur "+i;
			ServeurImpl s = new ServeurImpl(orb, nameServeur);
			
			byte[] sId = rootPOA.activate_object(s);
			org.omg.CORBA.Object sRef = rootPOA.id_to_reference(sId);
			NameComponent[] nameS = namingContext.to_name(nameServeur);
			
			try {
				nodePere.bind(nameS, sRef );
			} catch (AlreadyBound e) {
				System.err.println("Ce nom de serveur existe déjà!!!");
				nodePere.rebind(nameS, sRef );
			}
			
			ordonnanceur.referenceServeur(nameServeur);
		}
		
		System.out.println("SERVER READY");
		rootPOA.the_POAManager().activate();
		orb.run();
		
	}
}
