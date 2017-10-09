/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package com.example.team8.urlms.model;

// line 15 "../../../../../URLMS.ump"
public class InventoryItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryItem Attributes
  private String name;
  private double cost;
  private String category;

  //InventoryItem Associations
  private InventoryManager inventoryManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryItem(String aName, double aCost, String aCategory, InventoryManager aInventoryManager)
  {
    name = aName;
    cost = aCost;
    category = aCategory;
    boolean didAddInventoryManager = setInventoryManager(aInventoryManager);
    if (!didAddInventoryManager)
    {
      throw new RuntimeException("Unable to create inventoryItem due to inventoryManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(double aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setCategory(String aCategory)
  {
    boolean wasSet = false;
    category = aCategory;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getCost()
  {
    return cost;
  }

  public String getCategory()
  {
    return category;
  }

  public InventoryManager getInventoryManager()
  {
    return inventoryManager;
  }

  public boolean setInventoryManager(InventoryManager aInventoryManager)
  {
    boolean wasSet = false;
    if (aInventoryManager == null)
    {
      return wasSet;
    }

    InventoryManager existingInventoryManager = inventoryManager;
    inventoryManager = aInventoryManager;
    if (existingInventoryManager != null && !existingInventoryManager.equals(aInventoryManager))
    {
      existingInventoryManager.removeInventoryItem(this);
    }
    inventoryManager.addInventoryItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    InventoryManager placeholderInventoryManager = inventoryManager;
    this.inventoryManager = null;
    placeholderInventoryManager.removeInventoryItem(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "cost" + ":" + getCost()+ "," +
            "category" + ":" + getCategory()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "inventoryManager = "+(getInventoryManager()!=null?Integer.toHexString(System.identityHashCode(getInventoryManager())):"null");
  }
}