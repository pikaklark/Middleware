package idl;

/**
* idl/AnnuaireHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public final class AnnuaireHolder implements org.omg.CORBA.portable.Streamable
{
  public idl.Annuaire value = null;

  public AnnuaireHolder ()
  {
  }

  public AnnuaireHolder (idl.Annuaire initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = idl.AnnuaireHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    idl.AnnuaireHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return idl.AnnuaireHelper.type ();
  }

}
