/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.sql.Date;

// line 59 "../../../../../URLMS.ump"
public class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private String title;
  private Date date;

  //Report Associations
  private Funding funding;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Report(String aTitle, Date aDate, Funding aFunding)
  {
    title = aTitle;
    date = aDate;
    boolean didAddFunding = setFunding(aFunding);
    if (!didAddFunding)
    {
      throw new RuntimeException("Unable to create report due to funding");
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

  public Funding getFunding()
  {
    return funding;
  }

  public boolean setFunding(Funding aFunding)
  {
    boolean wasSet = false;
    if (aFunding == null)
    {
      return wasSet;
    }

    Funding existingFunding = funding;
    funding = aFunding;
    if (existingFunding != null && !existingFunding.equals(aFunding))
    {
      existingFunding.removeReport(this);
    }
    funding.addReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Funding placeholderFunding = funding;
    this.funding = null;
    placeholderFunding.removeReport(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "funding = "+(getFunding()!=null?Integer.toHexString(System.identityHashCode(getFunding())):"null");
  }
}