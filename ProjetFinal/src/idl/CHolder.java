package idl;

/**
* idl/CHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

public final class CHolder implements org.omg.CORBA.portable.Streamable
{
  public idl.C value = null;

  public CHolder ()
  {
  }

  public CHolder (idl.C initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = idl.CHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    idl.CHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return idl.CHelper.type ();
  }

}