package idl;

/**
* idl/BHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public final class BHolder implements org.omg.CORBA.portable.Streamable
{
  public idl.B value = null;

  public BHolder ()
  {
  }

  public BHolder (idl.B initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = idl.BHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    idl.BHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return idl.BHelper.type ();
  }

}
