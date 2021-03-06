package idl;


/**
* idl/OrdonnanceurPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public abstract class OrdonnanceurPOA extends org.omg.PortableServer.Servant
 implements idl.OrdonnanceurOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("execute", new java.lang.Integer (0));
    _methods.put ("createThreadExecutor", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // idl/Ordonnanceur/execute
       {
         idl.Job j = idl.JobHelper.read (in);
         this.execute (j);
         out = $rh.createReply();
         break;
       }

       case 1:  // idl/Ordonnanceur/createThreadExecutor
       {
         this.createThreadExecutor ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:idl/Ordonnanceur:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Ordonnanceur _this() 
  {
    return OrdonnanceurHelper.narrow(
    super._this_object());
  }

  public Ordonnanceur _this(org.omg.CORBA.ORB orb) 
  {
    return OrdonnanceurHelper.narrow(
    super._this_object(orb));
  }


} // class OrdonnanceurPOA
