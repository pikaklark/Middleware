package idl;


/**
* idl/ClientPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
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
  public void callBack (int idJob, org.omg.CORBA.Any retour)
  {
    _impl.callBack(idJob, retour);
  } // callBack

  public String _toString ()
  {
    return _impl._toString();
  } // _toString

  public void findRetour (int idJob)
  {
    _impl.findRetour(idJob);
  } // findRetour

  private idl.ClientOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class ClientPOATie