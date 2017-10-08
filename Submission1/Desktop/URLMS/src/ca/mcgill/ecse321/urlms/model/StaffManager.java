/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 60 "../../../../../URLMS.ump"
public class StaffManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffManager Associations
  private List<StaffMember> staffMembers;
  private URLMS uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffManager(URLMS aURLMS)
  {
    staffMembers = new ArrayList<StaffMember>();
    if (aURLMS == null || aURLMS.getStaffManager() != null)
    {
      throw new RuntimeException("Unable to create StaffManager due to aURLMS");
    }
    uRLMS = aURLMS;
  }

  public StaffManager(InventoryManager aInventoryManagerForURLMS, FundingManager aFundingManagerForURLMS)
  {
    staffMembers = new ArrayList<StaffMember>();
    uRLMS = new URLMS(this, aInventoryManagerForURLMS, aFundingManagerForURLMS);
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

  public URLMS getURLMS()
  {
    return uRLMS;
  }

  public static int minimumNumberOfStaffMembers()
  {
    return 0;
  }

  public StaffMember addStaffMember()
  {
    return new StaffMember(this);
  }

  public boolean addStaffMember(StaffMember aStaffMember)
  {
    boolean wasAdded = false;
    if (staffMembers.contains(aStaffMember)) { return false; }
    StaffManager existingStaffManager = aStaffMember.getStaffManager();
    boolean isNewStaffManager = existingStaffManager != null && !this.equals(existingStaffManager);
    if (isNewStaffManager)
    {
      aStaffMember.setStaffManager(this);
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
    //Unable to remove aStaffMember, as it must always have a staffManager
    if (!this.equals(aStaffMember.getStaffManager()))
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
    URLMS existingURLMS = uRLMS;
    uRLMS = null;
    if (existingURLMS != null)
    {
      existingURLMS.delete();
    }
  }

}