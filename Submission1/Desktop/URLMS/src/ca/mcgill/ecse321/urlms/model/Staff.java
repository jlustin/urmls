/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 65 "../../../../../URLMS.ump"
public class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Associations
  private List<StaffMember> staffMembers;
  private Lab lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(Lab aLab)
  {
    staffMembers = new ArrayList<StaffMember>();
    if (aLab == null || aLab.getStaff() != null)
    {
      throw new RuntimeException("Unable to create Staff due to aLab");
    }
    lab = aLab;
  }

  public Staff(Inventory aInventoryForLab, Funding aFundingForLab, URLMS aURLMSForLab)
  {
    staffMembers = new ArrayList<StaffMember>();
    lab = new Lab(this, aInventoryForLab, aFundingForLab, aURLMSForLab);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public StaffMember getStaffMember(int index)
  {
    StaffMember aStaffMember = staffMembers.get(index);
    return aStaffMember;
  }

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

  public Lab getLab()
  {
    return lab;
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
    Staff existingStaff = aStaffMember.getStaff();
    boolean isNewStaff = existingStaff != null && !this.equals(existingStaff);
    if (isNewStaff)
    {
      aStaffMember.setStaff(this);
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
    //Unable to remove aStaffMember, as it must always have a staff
    if (!this.equals(aStaffMember.getStaff()))
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

  public void delete()
  {
    for(int i=staffMembers.size(); i > 0; i--)
    {
      StaffMember aStaffMember = staffMembers.get(i - 1);
      aStaffMember.delete();
    }
    Lab existingLab = lab;
    lab = null;
    if (existingLab != null)
    {
      existingLab.delete();
    }
  }

}