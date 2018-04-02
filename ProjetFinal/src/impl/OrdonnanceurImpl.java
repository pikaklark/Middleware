package impl;

import java.util.ArrayList;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA_2_3.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;


import idl.Job;
import idl.OrdonnanceurPOA;
import idl.Serveur;
import idl.ServeurHelper;

public class OrdonnanceurImpl extends OrdonnanceurPOA {

	private ORB orb;
	private POA rootPOA;
	
	private ArrayList<Job> pile;
	
	private Thread executor;
	
	private NamingContextExt namingContext;
	
	private ArrayList<String> serveurs;
	
	public OrdonnanceurImpl(ORB orb, POA rootPOA) throws InvalidName {
		serveurs = new ArrayList<>();
		this.orb = orb;
		this.rootPOA = rootPOA;
		
		pile = new ArrayList<>();
		
		namingContext =NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

		executor = new Thread( () -> {
			while(!Thread.currentThread().isInterrupted()) {
				if(pile.isEmpty()) {
					//La pile est vide rien a faire
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					//Il y a quelque chose dans la pile
					Job j = pile.get(0);
					if(sendJob(j)) {
						//le job a bien été envoyé à un serveur
						pile.remove(0);
					}
					else {
						//Tout les serveurs sont surment occuper
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}	
		);
		
		executor.start();
	}
	
	public POA getRootPOA() {
		return rootPOA;
	}
	
	public void exitOrdonnanceur(){
		executor.interrupt();
		try {
			executor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("Ok Normal");
			e.printStackTrace();
		}
	}
	
	public boolean sendJob(Job j) {
		try {
			namingContext =NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String nameServeur: serveurs) {
			
			Serveur s = null;
			
			try {
				NameComponent[] name = namingContext.to_name("Serveur/"+nameServeur);
				s =ServeurHelper.narrow(namingContext.resolve(name));
			} catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
				//Ne rien faire c'est normal
			}
			
			if(s != null && s.isReady() ) {
				//Le serveur est près a recevoir le job
				s.execute(j);
				return true;
			}
		}
		
		
		return false;
	}
	
	@Override
	public void execute(Job j) {
		pile.add(j);
	}


	@Override
	public void referenceServeur(String nameReference) {
		serveurs.add(nameReference);
	}

}
