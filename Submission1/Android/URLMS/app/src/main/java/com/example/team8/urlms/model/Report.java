/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package com.example.team8.urlms.model;
import java.sql.Date;

// line 54 "../../../../../URLMS.ump"
public class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private String title;
  private Date date;

  //Report Associations
  private FundingManager fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Report(String aTitle, Date aDate, FundingManager aFundingManager)
  {
    title = aTitle;
    date = aDate;
    boolean didAddFundingManager = setFundingManager(aFundingManager);
    if (!didAddFundingManager)
    {
      throw new RuntimeException("Unable to create report due to fundingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public Date getDate()
  {
    return date;
  }

  public FundingManager getFundingManager()
  {
    return fundingManager;
  }

  public boolean setFundingManager(FundingManager aFundingManager)
  {
    boolean wasSet = false;
    if (aFundingManager == null)
    {
      return wasSet;
    }

    FundingManager existingFundingManager = fundingManager;
    fundingManager = aFundingManager;
    if (existingFundingManager != null && !existingFundingManager.equals(aFundingManager))
    {
      existingFundingManager.removeReport(this);
    }
    fundingManager.addReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FundingManager placeholderFundingManager = fundingManager;
    this.fundingManager = null;
    placeholderFundingManager.removeReport(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "fundingManager = "+(getFundingManager()!=null?Integer.toHexString(System.identityHashCode(getFundingManager())):"null");
  }
}