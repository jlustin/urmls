/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 15 "../../../../../URLMS.ump"
public class Inventory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Inventory Associations
  private List<InventoryItem> inventoryItems;
  private Lab lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Inventory(Lab aLab)
  {
    inventoryItems = new ArrayList<InventoryItem>();
    if (aLab == null || aLab.getInventory() != null)
    {
      throw new RuntimeException("Unable to create Inventory due to aLab");
    }
    lab = aLab;
  }

  public Inventory(Staff aStaffForLab, Funding aFundingForLab, URLMS aURLMSForLab)
  {
    inventoryItems = new ArrayList<InventoryItem>();
    lab = new Lab(aStaffForLab, this, aFundingForLab, aURLMSForLab);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public InventoryItem getInventoryItem(int index)
  {
    InventoryItem aInventoryItem = inventoryItems.get(index);
    return aInventoryItem;
  }

  public List<InventoryItem> getInventoryItems()
  {
    List<InventoryItem> newInventoryItems = Collections.unmodifiableList(inventoryItems);
    return newInventoryItems;
  }

  public int numberOfInventoryItems()
  {
    int number = inventoryItems.size();
    return number;
  }

  public boolean hasInventoryItems()
  {
    boolean has = inventoryItems.size() > 0;
    return has;
  }

  public int indexOfInventoryItem(InventoryItem aInventoryItem)
  {
    int index = inventoryItems.indexOf(aInventoryItem);
    return index;
  }

  public Lab getLab()
  {
    return lab;
  }

  public static int minimumNumberOfInventoryItems()
  {
    return 0;
  }

  public InventoryItem addInventoryItem(String aName, double aCost, String aCategory)
  {
    return new InventoryItem(aName, aCost, aCategory, this);
  }

  public boolean addInventoryItem(InventoryItem aInventoryItem)
  {
    boolean wasAdded = false;
    if (inventoryItems.contains(aInventoryItem)) { return false; }
    Inventory existingInventory = aInventoryItem.getInventory();
    boolean isNewInventory = existingInventory != null && !this.equals(existingInventory);
    if (isNewInventory)
    {
      aInventoryItem.setInventory(this);
    }
    else
    {
      inventoryItems.add(aInventoryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInventoryItem(InventoryItem aInventoryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aInventoryItem, as it must always have a inventory
    if (!this.equals(aInventoryItem.getInventory()))
    {
      inventoryItems.remove(aInventoryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addInventoryItemAt(InventoryItem aInventoryItem, int index)
  {  
    boolean wasAdded = false;
    if(addInventoryItem(aInventoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
      inventoryItems.remove(aInventoryItem);
      inventoryItems.add(index, aInventoryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInventoryItemAt(InventoryItem aInventoryItem, int index)
  {
    boolean wasAdded = false;
    if(inventoryItems.contains(aInventoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
      inventoryItems.remove(aInventoryItem);
      inventoryItems.add(index, aInventoryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInventoryItemAt(aInventoryItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=inventoryItems.size(); i > 0; i--)
    {
      InventoryItem aInventoryItem = inventoryItems.get(i - 1);
      aInventoryItem.delete();
    }
    Lab existingLab = lab;
    lab = null;
    if (existingLab != null)
    {
      existingLab.delete();
    }
  }

}