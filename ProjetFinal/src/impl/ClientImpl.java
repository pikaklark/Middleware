package impl;

import java.util.ArrayList;

import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

import idl.ClientPOA;

public class ClientImpl extends ClientPOA {
	
	private ArrayList<StructCallBack> callBacks;
	private String clientName;

	
	public ClientImpl(String clientName) {
		callBacks = new ArrayList<>();
		this.clientName = clientName;
	}

	@Override
	public void callBack(int idJob, Any retour) {
		StructCallBack tmp = new StructCallBack(idJob, retour);
		callBacks.add(tmp);
	}
	
	@Override
	public void findRetour(int idJob) {
		for(StructCallBack s : callBacks) {
			if(s.id_job == idJob) {
				s.printRetour();
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Je suis le Client "+clientName;
	}
	
	public static class StructCallBack{
		int id_job;
		Any retour;
		
		public StructCallBack(int id_job, Any retour) {
			this.id_job = id_job;
			this.retour = retour;
		}
		
		public int getId() {
			return id_job;
		}
		
		public void printRetour() {
			switch( retour.type().kind().value()) {
				case TCKind._tk_boolean:
					System.out.println(retour.extract_boolean());
					break;
				case TCKind._tk_long:
					System.out.println(retour.extract_long());
					break;
				case TCKind._tk_string:
					System.out.println(retour.extract_string());
					break;
				case TCKind._tk_null:
					System.err.println("Type pas reconnu");
					break;
				default:
					System.err.println("Aucun type");
					break;
			}
		}
	}

	@Override
	public String _toString() {
		return "Je suis le Client "+clientName;
	}

}
