package idl;


/**
* idl/APOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public class APOATie extends APOA
{

  // Constructors

  public APOATie ( idl.AOperations delegate ) {
      this._impl = delegate;
  }
  public APOATie ( idl.AOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public idl.AOperations _delegate() {
      return this._impl;
  }
  public void _delegate (idl.AOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public String testReturn (org.omg.CORBA.Any parametre)
  {
    return _impl.testReturn(parametre);
  } // testReturn

  public void testNoReturn (org.omg.CORBA.Any parametre)
  {
    _impl.testNoReturn(parametre);
  } // testNoReturn

  public void testEasy ()
  {
    _impl.testEasy();
  } // testEasy

  private idl.AOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class APOATie
