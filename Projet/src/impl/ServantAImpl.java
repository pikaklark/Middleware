package impl;

import org.omg.CORBA.Any;

import idl.APOA;

public class ServantAImpl extends APOA {

	@Override
	public String testReturn() {
		System.out.println("A.testReturn()");
		return "A.testReturn()";
	}

	@Override
	public void testNoReturn(Any parametre) {
		System.out.println("A.testNoReturn()");
	}

	@Override
	public void testEasy() {
		System.out.println("A.testEasy()");
	}


}
