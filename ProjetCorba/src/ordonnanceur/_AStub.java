
/**
* _AStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ordonnanceur.idl
* Thursday, March 29, 2018 at 3:40:28 PM Central European Summer Time
*/

public class _AStub extends org.omg.CORBA.portable.ObjectImpl implements A
{

  public void a1 (org.omg.CORBA.Any params, String serverRef, String objectName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("a1", false);
                $out.write_any (params);
                $out.write_string (serverRef);
                $out.write_string (objectName);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                a1 (params, serverRef, objectName        );
            } finally {
                _releaseReply ($in);
            }
  } // a1

  public void a2 (org.omg.CORBA.Any params, String serverRef, String objectName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("a2", false);
                $out.write_any (params);
                $out.write_string (serverRef);
                $out.write_string (objectName);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                a2 (params, serverRef, objectName        );
            } finally {
                _releaseReply ($in);
            }
  } // a2

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:A:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _AStub
