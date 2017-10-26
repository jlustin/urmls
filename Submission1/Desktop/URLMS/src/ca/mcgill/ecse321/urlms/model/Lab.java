/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 8 "../../../../../URLMS.ump"
public class Lab
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lab Associations
  private Staff staff;
  private Inventory inventory;
  private Funding funding;
  private URLMS uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Lab(Staff aStaff, Inventory aInventory, Funding aFunding, URLMS aURLMS)
  {
    if (aStaff == null || aStaff.getLab() != null)
    {
      throw new RuntimeException("Unable to create Lab due to aStaff");
    }
    staff = aStaff;
    if (aInventory == null || aInventory.getLab() != null)
    {
      throw new RuntimeException("Unable to create Lab due to aInventory");
    }
    inventory = aInventory;
    if (aFunding == null || aFunding.getLab() != null)
    {
      throw new RuntimeException("Unable to create Lab due to aFunding");
    }
    funding = aFunding;
    boolean didAddURLMS = setURLMS(aURLMS);
    if (!didAddURLMS)
    {
      throw new RuntimeException("Unable to create lab due to uRLMS");
    }
  }

  public Lab(double aTotalBalanceForFunding, URLMS aURLMS)
  {
    staff = new Staff(this);
    inventory = new Inventory(this);
    funding = new Funding(aTotalBalanceForFunding, this);
    boolean didAddURLMS = setURLMS(aURLMS);
    if (!didAddURLMS)
    {
      throw new RuntimeException("Unable to create lab due to uRLMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Staff getStaff()
  {
    return staff;
  }

  public Inventory getInventory()
  {
    return inventory;
  }

  public Funding getFunding()
  {
    return funding;
  }

  public URLMS getURLMS()
  {
    return uRLMS;
  }

  public boolean setURLMS(URLMS aURLMS)
  {
    boolean wasSet = false;
    if (aURLMS == null)
    {
      return wasSet;
    }

    URLMS existingURLMS = uRLMS;
    uRLMS = aURLMS;
    if (existingURLMS != null && !existingURLMS.equals(aURLMS))
    {
      existingURLMS.removeLab(this);
    }
    uRLMS.addLab(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Staff existingStaff = staff;
    staff = null;
    if (existingStaff != null)
    {
      existingStaff.delete();
    }
    Inventory existingInventory = inventory;
    inventory = null;
    if (existingInventory != null)
    {
      existingInventory.delete();
    }
    Funding existingFunding = funding;
    funding = null;
    if (existingFunding != null)
    {
      existingFunding.delete();
    }
    URLMS placeholderURLMS = uRLMS;
    this.uRLMS = null;
    placeholderURLMS.removeLab(this);
  }

}