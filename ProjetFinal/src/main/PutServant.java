package main;

import org.omg.CORBA_2_3.ORB;
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

import impl.ServantAImpl;
import impl.ServantBImpl;
import impl.ServantCImpl;

public class PutServant {

	
	public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CORBA.ORBPackage.InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, AdapterInactive {
		ORB orb = (ORB) ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

		NamingContextExt namingContext =NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

		ServantAImpl A1 = new ServantAImpl();
		ServantAImpl A2 = new ServantAImpl();
		ServantBImpl B1 = new ServantBImpl();
		ServantCImpl C1 = new ServantCImpl();
		
		NameComponent[] pereA = namingContext.to_name("A");
		NameComponent[] pereB = namingContext.to_name("B");
		NameComponent[] pereC = namingContext.to_name("C");
		
		NamingContext nodePereA = null;
		NamingContext nodePereB = null;
		NamingContext nodePereC = null;
		
		try {
			nodePereA=namingContext.bind_new_context(pereA);
		} catch (NotFound | AlreadyBound | CannotProceed e) {
			nodePereA=NamingContextExtHelper.narrow((namingContext).resolve_str("A"));
		}
		
		try {
			nodePereB=namingContext.bind_new_context(pereB);
		} catch (NotFound | AlreadyBound | CannotProceed e) {
			nodePereB=NamingContextExtHelper.narrow((namingContext).resolve_str("B"));
		}
		
		try {
			nodePereC=namingContext.bind_new_context(pereC);
		} catch (NotFound | AlreadyBound | CannotProceed e) {
			nodePereC=NamingContextExtHelper.narrow((namingContext).resolve_str("C"));
		}

		byte[] A1Id = rootPOA.activate_object(A1);
		org.omg.CORBA.Object A1Ref = rootPOA.id_to_reference(A1Id);
		NameComponent[] A1Component = namingContext.to_name("A1");

		
		byte[] A2Id = rootPOA.activate_object(A2);
		org.omg.CORBA.Object A2Ref = rootPOA.id_to_reference(A2Id);
		NameComponent[] A2Component = namingContext.to_name("A2");
		
		byte[] B1Id = rootPOA.activate_object(B1);
		org.omg.CORBA.Object B1Ref = rootPOA.id_to_reference(B1Id);
		NameComponent[] B1Component = namingContext.to_name("B1");
		
		byte[] C1Id = rootPOA.activate_object(C1);
		org.omg.CORBA.Object C1Ref = rootPOA.id_to_reference(C1Id);
		NameComponent[] C1Component = namingContext.to_name("C1");
		
		try {
			nodePereA.bind(A1Component, A1Ref );
		} catch (AlreadyBound e) {
			System.err.println("Ce nom de servant existe déjà!!!");
			nodePereA.rebind(A1Component, A1Ref );
		}
		
		try {
			nodePereA.bind(A2Component, A2Ref );
		} catch (AlreadyBound e) {
			System.err.println("Ce nom de servant existe déjà!!!");
			nodePereA.rebind(A2Component, A2Ref );
		}
		
		try {
			nodePereB.bind(B1Component, B1Ref );
		} catch (AlreadyBound e) {
			System.err.println("Ce nom de servant existe déjà!!!");
			nodePereB.rebind(B1Component, B1Ref );
		}
		
		try {
			nodePereC.bind(C1Component, C1Ref );
		} catch (AlreadyBound e) {
			System.err.println("Ce nom de servant existe déjà!!!");
			nodePereC.rebind(C1Component, C1Ref );
		}
		
		System.out.println("Les servants sont prêt à être utilisé");
		rootPOA.the_POAManager().activate();
		orb.run();
	}
}
