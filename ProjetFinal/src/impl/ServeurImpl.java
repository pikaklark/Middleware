package impl;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Request;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import idl.Client;
import idl.ClientHelper;
import idl.Job;
import idl.JobHelper;
import idl.ServeurPOA;

public class ServeurImpl extends ServeurPOA {
	
	private boolean isReady;
	private ORB orb;
	private String nameServeur;
	
	/*
	 * Object en CORBA
	 * org.omg.CORBA.Object
	 */
	
	public ServeurImpl(ORB orb, String nameServeur) {
		this.orb = orb;
		isReady = true;
		this.nameServeur = nameServeur;
	}
	
	@Override
	public String toString() {
		return "Je suis "+nameServeur;
	}
	

	@Override
	public boolean isReady() {
		return isReady;
	}

	@Override
	public void execute(Job j) {
		isReady = false;
		
		org.omg.CORBA.Object obj=null;
		Request req = null;
		
		NamingContextExt namingContext = null;
		
		try {
			namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isReady = true;
			return;
		}
		
		try {
			obj = namingContext.resolve(namingContext.to_name(j.interfaceName+"/"+j.nameReference));
		} catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			System.err.println("Object"+j.nameReference+" pas trouver!!!");
			e.printStackTrace();
			isReady=true;
			return;
		}
		
		req = obj._request(j.methodeName);
		
		executeRequest(req, j, namingContext);
		
	}
	
	public void executeRequest(Request req, Job j, NamingContextExt namingContext) {
		Any param = req.add_in_arg();
		Any any = orb.create_any();
		JobHelper.insert(any, j);
		
		param.insert_any(any);
		
		param = req.add_in_arg();
		
		req.send_oneway();

		Any retour = req.return_value();
		
		
		
		Client client=null;
		NameComponent[] name;
		try {
			name = namingContext.to_name(j.interfaceNameClient+"/"+j.nameReferenceClient);
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try {
			client = ClientHelper.narrow(namingContext.resolve(name));
		} catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		client.callBack(j.id, retour);
		
	}

}
