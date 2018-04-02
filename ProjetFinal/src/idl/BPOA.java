package idl;


/**
* idl/BPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

public abstract class BPOA extends org.omg.PortableServer.Servant
 implements idl.BOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("testReturn", new java.lang.Integer (0));
    _methods.put ("testNoReturn", new java.lang.Integer (1));
    _methods.put ("testEasy", new java.lang.Integer (2));
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
       case 0:  // idl/B/testReturn
       {
         org.omg.CORBA.Any parametre = in.read_any ();
         String $result = null;
         $result = this.testReturn (parametre);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // idl/B/testNoReturn
       {
         org.omg.CORBA.Any parametre = in.read_any ();
         this.testNoReturn (parametre);
         out = $rh.createReply();
         break;
       }

       case 2:  // idl/B/testEasy
       {
         this.testEasy ();
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
    "IDL:idl/B:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public B _this() 
  {
    return BHelper.narrow(
    super._this_object());
  }

  public B _this(org.omg.CORBA.ORB orb) 
  {
    return BHelper.narrow(
    super._this_object(orb));
  }


} // class BPOA
