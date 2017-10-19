/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 10 "../../../../../URLMS.ump"
public class InventoryManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryManager Associations
  private List<InventoryItem> inventoryItems;
  private URLMS uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryManager(URLMS aURLMS)
  {
    inventoryItems = new ArrayList<InventoryItem>();
    if (aURLMS == null || aURLMS.getInventoryManager() != null)
    {
      throw new RuntimeException("Unable to create InventoryManager due to aURLMS");
    }
    uRLMS = aURLMS;
  }

  public InventoryManager(StaffManager aStaffManagerForURLMS, FundingManager aFundingManagerForURLMS)
  {
    inventoryItems = new ArrayList<InventoryItem>();
    uRLMS = new URLMS(aStaffManagerForURLMS, this, aFundingManagerForURLMS);
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

  public URLMS getURLMS()
  {
    return uRLMS;
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
    InventoryManager existingInventoryManager = aInventoryItem.getInventoryManager();
    boolean isNewInventoryManager = existingInventoryManager != null && !this.equals(existingInventoryManager);
    if (isNewInventoryManager)
    {
      aInventoryItem.setInventoryManager(this);
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
    //Unable to remove aInventoryItem, as it must always have a inventoryManager
    if (!this.equals(aInventoryItem.getInventoryManager()))
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
    URLMS existingURLMS = uRLMS;
    uRLMS = null;
    if (existingURLMS != null)
    {
      existingURLMS.delete();
    }
  }

}