package idl;


/**
* idl/OrdonnanceurPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

public class OrdonnanceurPOATie extends OrdonnanceurPOA
{

  // Constructors

  public OrdonnanceurPOATie ( idl.OrdonnanceurOperations delegate ) {
      this._impl = delegate;
  }
  public OrdonnanceurPOATie ( idl.OrdonnanceurOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public idl.OrdonnanceurOperations _delegate() {
      return this._impl;
  }
  public void _delegate (idl.OrdonnanceurOperations delegate ) {
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
  public void execute (idl.Job j)
  {
    _impl.execute(j);
  } // execute

  public void referenceServeur (String nameReference)
  {
    _impl.referenceServeur(nameReference);
  } // referenceServeur

  private idl.OrdonnanceurOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class OrdonnanceurPOATie
