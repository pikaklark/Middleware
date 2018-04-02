package impl;

import org.omg.CORBA.Any;

import idl.CPOA;

public class ServantCImpl extends CPOA {

	@Override
	public String testReturn(Any parametre) {
		System.out.println("C.testReturn()");
		return "C.testReturn()";
	}

	@Override
	public void testNoReturn(Any parametre) {
		System.out.println("C.testNoReturn()");
	}

	@Override
	public void testEasy() {
		System.out.println("C.testEasy()");
	}

}
