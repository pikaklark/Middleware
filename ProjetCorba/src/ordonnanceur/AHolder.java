
/**
* AHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ordonnanceur.idl
* Thursday, March 29, 2018 at 3:40:28 PM Central European Summer Time
*/

public final class AHolder implements org.omg.CORBA.portable.Streamable
{
  public A value = null;

  public AHolder ()
  {
  }

  public AHolder (A initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AHelper.type ();
  }

}