package idl;


/**
* idl/AHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

abstract public class AHelper
{
  private static String  _id = "IDL:idl/A:1.0";

  public static void insert (org.omg.CORBA.Any a, idl.A that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static idl.A extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (idl.AHelper.id (), "A");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static idl.A read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, idl.A value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static idl.A narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof idl.A)
      return (idl.A)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      idl._AStub stub = new idl._AStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static idl.A unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof idl.A)
      return (idl.A)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      idl._AStub stub = new idl._AStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
