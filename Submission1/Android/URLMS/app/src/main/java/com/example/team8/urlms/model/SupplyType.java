/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package com.example.team8.urlms.model;

// line 22 "../../../../../URLMS.ump"
public class SupplyType extends InventoryItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SupplyType Attributes
  private int quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SupplyType(String aName, double aCost, String aCategory, InventoryManager aInventoryManager, int aQuantity)
  {
    super(aName, aCost, aCategory, aInventoryManager);
    quantity = aQuantity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]";
  }
}