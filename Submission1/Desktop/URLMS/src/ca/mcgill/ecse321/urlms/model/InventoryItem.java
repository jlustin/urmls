/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 20 "../../../../../URLMS.ump"
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
  private Inventory inventory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryItem(String aName, double aCost, String aCategory, Inventory aInventory)
  {
    name = aName;
    cost = aCost;
    category = aCategory;
    boolean didAddInventory = setInventory(aInventory);
    if (!didAddInventory)
    {
      throw new RuntimeException("Unable to create inventoryItem due to inventory");
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

  public Inventory getInventory()
  {
    return inventory;
  }

  public boolean setInventory(Inventory aInventory)
  {
    boolean wasSet = false;
    if (aInventory == null)
    {
      return wasSet;
    }

    Inventory existingInventory = inventory;
    inventory = aInventory;
    if (existingInventory != null && !existingInventory.equals(aInventory))
    {
      existingInventory.removeInventoryItem(this);
    }
    inventory.addInventoryItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Inventory placeholderInventory = inventory;
    this.inventory = null;
    placeholderInventory.removeInventoryItem(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "cost" + ":" + getCost()+ "," +
            "category" + ":" + getCategory()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "inventory = "+(getInventory()!=null?Integer.toHexString(System.identityHashCode(getInventory())):"null");
  }
}