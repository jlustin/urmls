/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.sql.Date;

// line 103 "../../../../../URLMS.ump"
public class ProgressUpdate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Attributes
  private Date date;
  private String description;

  //ProgressUpdate Associations
  private StaffMember staffMember;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgressUpdate(Date aDate, String aDescription, StaffMember aStaffMember)
  {
    date = aDate;
    description = aDescription;
    boolean didAddStaffMember = setStaffMember(aStaffMember);
    if (!didAddStaffMember)
    {
      throw new RuntimeException("Unable to create progressUpdate due to staffMember");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }

  public StaffMember getStaffMember()
  {
    return staffMember;
  }

  public boolean setStaffMember(StaffMember aStaffMember)
  {
    boolean wasSet = false;
    if (aStaffMember == null)
    {
      return wasSet;
    }

    StaffMember existingStaffMember = staffMember;
    staffMember = aStaffMember;
    if (existingStaffMember != null && !existingStaffMember.equals(aStaffMember))
    {
      existingStaffMember.removeProgressUpdate(this);
    }
    staffMember.addProgressUpdate(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    StaffMember placeholderStaffMember = staffMember;
    this.staffMember = null;
    placeholderStaffMember.removeProgressUpdate(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "staffMember = "+(getStaffMember()!=null?Integer.toHexString(System.identityHashCode(getStaffMember())):"null");
  }
}