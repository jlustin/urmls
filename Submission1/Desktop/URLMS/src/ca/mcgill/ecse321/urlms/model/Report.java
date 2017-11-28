/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.sql.Date;

// line 149 "../../../../../../../../ump/tmp725626/model.ump"
// line 270 "../../../../../../../../ump/tmp725626/model.ump"
public class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private String title;
  private Date date;
  private String content;

  //Report Associations
  private Lab lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Report(String aTitle, Date aDate, String aContent, Lab aLab)
  {
    title = aTitle;
    date = aDate;
    content = aContent;
    boolean didAddLab = setLab(aLab);
    if (!didAddLab)
    {
      throw new RuntimeException("Unable to create report due to lab");
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

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
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

  public String getContent()
  {
    return content;
  }

  public Lab getLab()
  {
    return lab;
  }

  public boolean setLab(Lab aLab)
  {
    boolean wasSet = false;
    if (aLab == null)
    {
      return wasSet;
    }

    Lab existingLab = lab;
    lab = aLab;
    if (existingLab != null && !existingLab.equals(aLab))
    {
      existingLab.removeReport(this);
    }
    lab.addReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Lab placeholderLab = lab;
    this.lab = null;
    placeholderLab.removeReport(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "content" + ":" + getContent()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "lab = "+(getLab()!=null?Integer.toHexString(System.identityHashCode(getLab())):"null");
  }
}