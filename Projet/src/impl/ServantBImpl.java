package impl;

import org.omg.CORBA.Any;

import idl.BPOA;

public class ServantBImpl extends BPOA{

	@Override
	public String testReturn(Any parametre) {
		System.out.println("B.testReturn()");
		return "B.testReturn()";
	}

	@Override
	public void testNoReturn(Any parametre) {
		System.out.println("B.testNoReturn()");
	}

	@Override
	public void testEasy() {
		System.out.println("B.testEasy()");
	}

}
