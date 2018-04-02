package main;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA_2_3.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import idl.Client;
import idl.ClientHelper;
import idl.Job;
import idl.Ordonnanceur;
import idl.OrdonnanceurHelper;

public class UserClass {

	public static void main(String[] args) throws org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, InterruptedException {
		ORB orb = (ORB) ORB.init(args, null);
		NamingContextExt namingContext=null;
		Client me=null;
		try {
			namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent[] name = namingContext.to_name("Client/root");
			me =ClientHelper.narrow(namingContext.resolve(name));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Ordonnanceur ordonnanceur=null;
		try {
			namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent[] name = namingContext.to_name("Ordonnanceur");
			ordonnanceur = OrdonnanceurHelper.narrow(namingContext.resolve(name));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(me._toString());
		
		Any para = orb.create_any();
		Job j = new Job(0, "A1", "A", "testReturn", para, "root", "Client");
		ordonnanceur.execute(j);
		
		Job j1 = new Job(2, "A2", "A", "testEasy", para, "root", "Client");
		ordonnanceur.execute(j1);
		
		Thread.sleep(5000);
		me.findRetour(0);
		
		

	}
}
