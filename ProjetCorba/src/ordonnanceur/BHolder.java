
/**
* BHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ordonnanceur.idl
* Thursday, March 29, 2018 at 3:40:28 PM Central European Summer Time
*/

public final class BHolder implements org.omg.CORBA.portable.Streamable
{
  public B value = null;

  public BHolder ()
  {
  }

  public BHolder (B initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BHelper.type ();
  }

}