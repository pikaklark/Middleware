package idl;


/**
* idl/Job.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

public final class Job implements org.omg.CORBA.portable.IDLEntity
{
  public int id = (int)0;
  public String nameReference = null;
  public String interfaceName = null;
  public String methodeName = null;
  public org.omg.CORBA.Any parametre = null;
  public String nameReferenceClient = null;
  public String interfaceNameClient = null;

  public Job ()
  {
  } // ctor

  public Job (int _id, String _nameReference, String _interfaceName, String _methodeName, org.omg.CORBA.Any _parametre, String _nameReferenceClient, String _interfaceNameClient)
  {
    id = _id;
    nameReference = _nameReference;
    interfaceName = _interfaceName;
    methodeName = _methodeName;
    parametre = _parametre;
    nameReferenceClient = _nameReferenceClient;
    interfaceNameClient = _interfaceNameClient;
  } // ctor

} // class Job
