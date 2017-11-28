/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 23 "../../../../../../../../ump/tmp725626/model.ump"
// line 240 "../../../../../../../../ump/tmp725626/model.ump"
public class Lab
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lab Attributes
  private String name;

  //Lab Associations
  private List<StaffMember> staffMembers;
  private List<InventoryItem> inventoryItems;
  private List<FundingAccount> fundingAccounts;
  private List<Report> reports;
  private URLMS uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Lab(String aName, URLMS aURLMS)
  {
    name = aName;
    staffMembers = new ArrayList<StaffMember>();
    inventoryItems = new ArrayList<InventoryItem>();
    fundingAccounts = new ArrayList<FundingAccount>();
    reports = new ArrayList<Report>();
    boolean didAddURLMS = setURLMS(aURLMS);
    if (!didAddURLMS)
    {
      throw new RuntimeException("Unable to create lab due to uRLMS");
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

  public String getName()
  {
    return name;
  }

  public StaffMember getStaffMember(int index)
  {
    StaffMember aStaffMember = staffMembers.get(index);
    return aStaffMember;
  }

  /**
   * 1 <@>- 1 Staff;
   * 1 <@>- 1 Inventory;
   * 1 <@>- 1 Funding;
   */
  public List<StaffMember> getStaffMembers()
  {
    List<StaffMember> newStaffMembers = Collections.unmodifiableList(staffMembers);
    return newStaffMembers;
  }

  public int numberOfStaffMembers()
  {
    int number = staffMembers.size();
    return number;
  }

  public boolean hasStaffMembers()
  {
    boolean has = staffMembers.size() > 0;
    return has;
  }

  public int indexOfStaffMember(StaffMember aStaffMember)
  {
    int index = staffMembers.indexOf(aStaffMember);
    return index;
  }

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

  public FundingAccount getFundingAccount(int index)
  {
    FundingAccount aFundingAccount = fundingAccounts.get(index);
    return aFundingAccount;
  }

  public List<FundingAccount> getFundingAccounts()
  {
    List<FundingAccount> newFundingAccounts = Collections.unmodifiableList(fundingAccounts);
    return newFundingAccounts;
  }

  public int numberOfFundingAccounts()
  {
    int number = fundingAccounts.size();
    return number;
  }

  public boolean hasFundingAccounts()
  {
    boolean has = fundingAccounts.size() > 0;
    return has;
  }

  public int indexOfFundingAccount(FundingAccount aFundingAccount)
  {
    int index = fundingAccounts.indexOf(aFundingAccount);
    return index;
  }

  public Report getReport(int index)
  {
    Report aReport = reports.get(index);
    return aReport;
  }

  public List<Report> getReports()
  {
    List<Report> newReports = Collections.unmodifiableList(reports);
    return newReports;
  }

  public int numberOfReports()
  {
    int number = reports.size();
    return number;
  }

  public boolean hasReports()
  {
    boolean has = reports.size() > 0;
    return has;
  }

  public int indexOfReport(Report aReport)
  {
    int index = reports.indexOf(aReport);
    return index;
  }

  public URLMS getURLMS()
  {
    return uRLMS;
  }

  public static int minimumNumberOfStaffMembers()
  {
    return 0;
  }

  public StaffMember addStaffMember(String aName, int aId)
  {
    return new StaffMember(aName, aId, this);
  }

  public boolean addStaffMember(StaffMember aStaffMember)
  {
    boolean wasAdded = false;
    if (staffMembers.contains(aStaffMember)) { return false; }
    Lab existingLab = aStaffMember.getLab();
    boolean isNewLab = existingLab != null && !this.equals(existingLab);
    if (isNewLab)
    {
      aStaffMember.setLab(this);
    }
    else
    {
      staffMembers.add(aStaffMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaffMember(StaffMember aStaffMember)
  {
    boolean wasRemoved = false;
    //Unable to remove aStaffMember, as it must always have a lab
    if (!this.equals(aStaffMember.getLab()))
    {
      staffMembers.remove(aStaffMember);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStaffMemberAt(StaffMember aStaffMember, int index)
  {  
    boolean wasAdded = false;
    if(addStaffMember(aStaffMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffMembers()) { index = numberOfStaffMembers() - 1; }
      staffMembers.remove(aStaffMember);
      staffMembers.add(index, aStaffMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffMemberAt(StaffMember aStaffMember, int index)
  {
    boolean wasAdded = false;
    if(staffMembers.contains(aStaffMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffMembers()) { index = numberOfStaffMembers() - 1; }
      staffMembers.remove(aStaffMember);
      staffMembers.add(index, aStaffMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffMemberAt(aStaffMember, index);
    }
    return wasAdded;
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
    Lab existingLab = aInventoryItem.getLab();
    boolean isNewLab = existingLab != null && !this.equals(existingLab);
    if (isNewLab)
    {
      aInventoryItem.setLab(this);
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
    //Unable to remove aInventoryItem, as it must always have a lab
    if (!this.equals(aInventoryItem.getLab()))
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

  public static int minimumNumberOfFundingAccounts()
  {
    return 0;
  }

  public FundingAccount addFundingAccount(String aType, double aBalance)
  {
    return new FundingAccount(aType, aBalance, this);
  }

  public boolean addFundingAccount(FundingAccount aFundingAccount)
  {
    boolean wasAdded = false;
    if (fundingAccounts.contains(aFundingAccount)) { return false; }
    Lab existingLab = aFundingAccount.getLab();
    boolean isNewLab = existingLab != null && !this.equals(existingLab);
    if (isNewLab)
    {
      aFundingAccount.setLab(this);
    }
    else
    {
      fundingAccounts.add(aFundingAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFundingAccount(FundingAccount aFundingAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aFundingAccount, as it must always have a lab
    if (!this.equals(aFundingAccount.getLab()))
    {
      fundingAccounts.remove(aFundingAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFundingAccountAt(FundingAccount aFundingAccount, int index)
  {  
    boolean wasAdded = false;
    if(addFundingAccount(aFundingAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFundingAccounts()) { index = numberOfFundingAccounts() - 1; }
      fundingAccounts.remove(aFundingAccount);
      fundingAccounts.add(index, aFundingAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFundingAccountAt(FundingAccount aFundingAccount, int index)
  {
    boolean wasAdded = false;
    if(fundingAccounts.contains(aFundingAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFundingAccounts()) { index = numberOfFundingAccounts() - 1; }
      fundingAccounts.remove(aFundingAccount);
      fundingAccounts.add(index, aFundingAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFundingAccountAt(aFundingAccount, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReports()
  {
    return 0;
  }

  public Report addReport(String aTitle, Date aDate, String aContent)
  {
    return new Report(aTitle, aDate, aContent, this);
  }

  public boolean addReport(Report aReport)
  {
    boolean wasAdded = false;
    if (reports.contains(aReport)) { return false; }
    Lab existingLab = aReport.getLab();
    boolean isNewLab = existingLab != null && !this.equals(existingLab);
    if (isNewLab)
    {
      aReport.setLab(this);
    }
    else
    {
      reports.add(aReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReport(Report aReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aReport, as it must always have a lab
    if (!this.equals(aReport.getLab()))
    {
      reports.remove(aReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReportAt(Report aReport, int index)
  {  
    boolean wasAdded = false;
    if(addReport(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReports()) { index = numberOfReports() - 1; }
      reports.remove(aReport);
      reports.add(index, aReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReportAt(Report aReport, int index)
  {
    boolean wasAdded = false;
    if(reports.contains(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReports()) { index = numberOfReports() - 1; }
      reports.remove(aReport);
      reports.add(index, aReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReportAt(aReport, index);
    }
    return wasAdded;
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
    for(int i=staffMembers.size(); i > 0; i--)
    {
      StaffMember aStaffMember = staffMembers.get(i - 1);
      aStaffMember.delete();
    }
    for(int i=inventoryItems.size(); i > 0; i--)
    {
      InventoryItem aInventoryItem = inventoryItems.get(i - 1);
      aInventoryItem.delete();
    }
    for(int i=fundingAccounts.size(); i > 0; i--)
    {
      FundingAccount aFundingAccount = fundingAccounts.get(i - 1);
      aFundingAccount.delete();
    }
    for(int i=reports.size(); i > 0; i--)
    {
      Report aReport = reports.get(i - 1);
      aReport.delete();
    }
    URLMS placeholderURLMS = uRLMS;
    this.uRLMS = null;
    placeholderURLMS.removeLab(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "uRLMS = "+(getURLMS()!=null?Integer.toHexString(System.identityHashCode(getURLMS())):"null");
  }
}