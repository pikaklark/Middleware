package idl;


/**
* idl/ClientPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public class ClientPOATie extends ClientPOA
{

  // Constructors

  public ClientPOATie ( idl.ClientOperations delegate ) {
      this._impl = delegate;
  }
  public ClientPOATie ( idl.ClientOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public idl.ClientOperations _delegate() {
      return this._impl;
  }
  public void _delegate (idl.ClientOperations delegate ) {
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
  public void returnOfJob (org.omg.CORBA.Any reponse)
  {
    _impl.returnOfJob(reponse);
  } // returnOfJob

  public void initOrdonnaceur ()
  {
    _impl.initOrdonnaceur();
  } // initOrdonnaceur

  public void createAndExecuteJob (String nameRef, String interfaceName, String methodeName, org.omg.CORBA.Any parametre)
  {
    _impl.createAndExecuteJob(nameRef, interfaceName, methodeName, parametre);
  } // createAndExecuteJob

  public void printReturn ()
  {
    _impl.printReturn();
  } // printReturn

  private idl.ClientOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class ClientPOATie
