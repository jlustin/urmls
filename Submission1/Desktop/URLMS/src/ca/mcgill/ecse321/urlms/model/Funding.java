/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 38 "../../../../../URLMS.ump"
public class Funding
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Funding Attributes
  private double totalBalance;

  //Funding Associations
  private List<FundingAccount> fundingAccounts;
  private List<Report> reports;
  private Lab lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Funding(double aTotalBalance, Lab aLab)
  {
    totalBalance = aTotalBalance;
    fundingAccounts = new ArrayList<FundingAccount>();
    reports = new ArrayList<Report>();
    if (aLab == null || aLab.getFunding() != null)
    {
      throw new RuntimeException("Unable to create Funding due to aLab");
    }
    lab = aLab;
  }

  public Funding(double aTotalBalance, Staff aStaffForLab, Inventory aInventoryForLab, URLMS aURLMSForLab)
  {
    totalBalance = aTotalBalance;
    fundingAccounts = new ArrayList<FundingAccount>();
    reports = new ArrayList<Report>();
    lab = new Lab(aStaffForLab, aInventoryForLab, this, aURLMSForLab);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalBalance(double aTotalBalance)
  {
    boolean wasSet = false;
    totalBalance = aTotalBalance;
    wasSet = true;
    return wasSet;
  }

  public double getTotalBalance()
  {
    return totalBalance;
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

  public Lab getLab()
  {
    return lab;
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
    Funding existingFunding = aFundingAccount.getFunding();
    boolean isNewFunding = existingFunding != null && !this.equals(existingFunding);
    if (isNewFunding)
    {
      aFundingAccount.setFunding(this);
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
    //Unable to remove aFundingAccount, as it must always have a funding
    if (!this.equals(aFundingAccount.getFunding()))
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

  public Report addReport(String aTitle, Date aDate)
  {
    return new Report(aTitle, aDate, this);
  }

  public boolean addReport(Report aReport)
  {
    boolean wasAdded = false;
    if (reports.contains(aReport)) { return false; }
    Funding existingFunding = aReport.getFunding();
    boolean isNewFunding = existingFunding != null && !this.equals(existingFunding);
    if (isNewFunding)
    {
      aReport.setFunding(this);
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
    //Unable to remove aReport, as it must always have a funding
    if (!this.equals(aReport.getFunding()))
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

  public void delete()
  {
    while (fundingAccounts.size() > 0)
    {
      FundingAccount aFundingAccount = fundingAccounts.get(fundingAccounts.size() - 1);
      aFundingAccount.delete();
      fundingAccounts.remove(aFundingAccount);
    }
    
    for(int i=reports.size(); i > 0; i--)
    {
      Report aReport = reports.get(i - 1);
      aReport.delete();
    }
    Lab existingLab = lab;
    lab = null;
    if (existingLab != null)
    {
      existingLab.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalBalance" + ":" + getTotalBalance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lab = "+(getLab()!=null?Integer.toHexString(System.identityHashCode(getLab())):"null");
  }
}