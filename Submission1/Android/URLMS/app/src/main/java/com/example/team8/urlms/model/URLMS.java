/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package com.example.team8.urlms.model;

// line 3 "../../../../../URLMS.ump"
public class URLMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private StaffManager staffManager;
  private InventoryManager inventoryManager;
  private FundingManager fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public URLMS(StaffManager aStaffManager, InventoryManager aInventoryManager, FundingManager aFundingManager)
  {
    if (aStaffManager == null || aStaffManager.getURLMS() != null)
    {
      throw new RuntimeException("Unable to create URLMS due to aStaffManager");
    }
    staffManager = aStaffManager;
    if (aInventoryManager == null || aInventoryManager.getURLMS() != null)
    {
      throw new RuntimeException("Unable to create URLMS due to aInventoryManager");
    }
    inventoryManager = aInventoryManager;
    if (aFundingManager == null || aFundingManager.getURLMS() != null)
    {
      throw new RuntimeException("Unable to create URLMS due to aFundingManager");
    }
    fundingManager = aFundingManager;
  }

  public URLMS(double aTotalBalanceForFundingManager)
  {
    staffManager = new StaffManager(this);
    inventoryManager = new InventoryManager(this);
    fundingManager = new FundingManager(aTotalBalanceForFundingManager, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public StaffManager getStaffManager()
  {
    return staffManager;
  }

  public InventoryManager getInventoryManager()
  {
    return inventoryManager;
  }

  public FundingManager getFundingManager()
  {
    return fundingManager;
  }

  public void delete()
  {
    StaffManager existingStaffManager = staffManager;
    staffManager = null;
    if (existingStaffManager != null)
    {
      existingStaffManager.delete();
    }
    InventoryManager existingInventoryManager = inventoryManager;
    inventoryManager = null;
    if (existingInventoryManager != null)
    {
      existingInventoryManager.delete();
    }
    FundingManager existingFundingManager = fundingManager;
    fundingManager = null;
    if (existingFundingManager != null)
    {
      existingFundingManager.delete();
    }
  }

}