package idl;

/**
* idl/ClientHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* lundi 2 avril 2018 20 h 13 CEST
*/

public final class ClientHolder implements org.omg.CORBA.portable.Streamable
{
  public idl.Client value = null;

  public ClientHolder ()
  {
  }

  public ClientHolder (idl.Client initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = idl.ClientHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    idl.ClientHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return idl.ClientHelper.type ();
  }

}
