/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 28 "../../../../../URLMS.ump"
public class Equipment extends InventoryItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, double aCost, String aCategory, InventoryManager aInventoryManager)
  {
    super(aName, aCost, aCategory, aInventoryManager);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}