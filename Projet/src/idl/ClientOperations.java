package idl;


/**
* idl/ClientOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ordonnanceur.idl
* vendredi 23 mars 2018 15 h 31 CET
*/

public interface ClientOperations 
{
  void returnOfJob (org.omg.CORBA.Any reponse);
  void initOrdonnaceur ();
  void createAndExecuteJob (String nameRef, String interfaceName, String methodeName, org.omg.CORBA.Any parametre);
  void printReturn ();
} // interface ClientOperations
