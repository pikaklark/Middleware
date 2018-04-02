package impl;

public class StructObject {
	private String nameObject;
	private String nameInterface;
	
	public StructObject(String obj, String inter) {
		this.nameObject = obj;
		this.nameInterface = inter;
	}
	
	public boolean isSameObject(String name) {
		return this.nameObject.equals(name);
	}
	
	public boolean isSameInterface(String name) {
		return this.nameInterface.equals(name);
	}
}
