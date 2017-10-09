/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 87 "../../../../../URLMS.ump"
public class ProgressUpdate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Associations
  private StaffMember staffMember;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgressUpdate(StaffMember aStaffMember)
  {
    boolean didAddStaffMember = setStaffMember(aStaffMember);
    if (!didAddStaffMember)
    {
      throw new RuntimeException("Unable to create progressUpdate due to staffMember");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

}