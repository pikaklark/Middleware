package idl;


/**
* idl/Job.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public final class Job implements org.omg.CORBA.portable.IDLEntity
{
  public String nameReference = null;
  public String interfaceName = null;
  public String methodeName = null;
  public org.omg.CORBA.Any parametre = null;
  public String nameReferenceClient = null;
  public String interfaceNameClient = null;

  public Job ()
  {
  } // ctor

  public Job (String _nameReference, String _interfaceName, String _methodeName, org.omg.CORBA.Any _parametre, String _nameReferenceClient, String _interfaceNameClient)
  {
    nameReference = _nameReference;
    interfaceName = _interfaceName;
    methodeName = _methodeName;
    parametre = _parametre;
    nameReferenceClient = _nameReferenceClient;
    interfaceNameClient = _interfaceNameClient;
  } // ctor

} // class Job