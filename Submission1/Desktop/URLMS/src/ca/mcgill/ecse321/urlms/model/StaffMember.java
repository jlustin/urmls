/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 65 "../../../../../URLMS.ump"
public class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private String name;
  private int id;

  //StaffMember Associations
  private List<ResearchRole> researchRoles;
  private List<ProgressUpdate> progressUpdates;
  private StaffManager staffManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffMember(String aName, int aId, StaffManager aStaffManager)
  {
    name = aName;
    id = aId;
    researchRoles = new ArrayList<ResearchRole>();
    progressUpdates = new ArrayList<ProgressUpdate>();
    boolean didAddStaffManager = setStaffManager(aStaffManager);
    if (!didAddStaffManager)
    {
      throw new RuntimeException("Unable to create staffMember due to staffManager");
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

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public ResearchRole getResearchRole(int index)
  {
    ResearchRole aResearchRole = researchRoles.get(index);
    return aResearchRole;
  }

  public List<ResearchRole> getResearchRoles()
  {
    List<ResearchRole> newResearchRoles = Collections.unmodifiableList(researchRoles);
    return newResearchRoles;
  }

  public int numberOfResearchRoles()
  {
    int number = researchRoles.size();
    return number;
  }

  public boolean hasResearchRoles()
  {
    boolean has = researchRoles.size() > 0;
    return has;
  }

  public int indexOfResearchRole(ResearchRole aResearchRole)
  {
    int index = researchRoles.indexOf(aResearchRole);
    return index;
  }

  public ProgressUpdate getProgressUpdate(int index)
  {
    ProgressUpdate aProgressUpdate = progressUpdates.get(index);
    return aProgressUpdate;
  }

  public List<ProgressUpdate> getProgressUpdates()
  {
    List<ProgressUpdate> newProgressUpdates = Collections.unmodifiableList(progressUpdates);
    return newProgressUpdates;
  }

  public int numberOfProgressUpdates()
  {
    int number = progressUpdates.size();
    return number;
  }

  public boolean hasProgressUpdates()
  {
    boolean has = progressUpdates.size() > 0;
    return has;
  }

  public int indexOfProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    int index = progressUpdates.indexOf(aProgressUpdate);
    return index;
  }

  public StaffManager getStaffManager()
  {
    return staffManager;
  }

  public static int minimumNumberOfResearchRoles()
  {
    return 0;
  }

  public ResearchRole addResearchRole()
  {
    return new ResearchRole(this);
  }

  public boolean addResearchRole(ResearchRole aResearchRole)
  {
    boolean wasAdded = false;
    if (researchRoles.contains(aResearchRole)) { return false; }
    StaffMember existingStaffMember = aResearchRole.getStaffMember();
    boolean isNewStaffMember = existingStaffMember != null && !this.equals(existingStaffMember);
    if (isNewStaffMember)
    {
      aResearchRole.setStaffMember(this);
    }
    else
    {
      researchRoles.add(aResearchRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResearchRole(ResearchRole aResearchRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aResearchRole, as it must always have a staffMember
    if (!this.equals(aResearchRole.getStaffMember()))
    {
      researchRoles.remove(aResearchRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResearchRoleAt(ResearchRole aResearchRole, int index)
  {  
    boolean wasAdded = false;
    if(addResearchRole(aResearchRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResearchRoles()) { index = numberOfResearchRoles() - 1; }
      researchRoles.remove(aResearchRole);
      researchRoles.add(index, aResearchRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResearchRoleAt(ResearchRole aResearchRole, int index)
  {
    boolean wasAdded = false;
    if(researchRoles.contains(aResearchRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResearchRoles()) { index = numberOfResearchRoles() - 1; }
      researchRoles.remove(aResearchRole);
      researchRoles.add(index, aResearchRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResearchRoleAt(aResearchRole, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public ProgressUpdate addProgressUpdate()
  {
    return new ProgressUpdate(this);
  }

  public boolean addProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    boolean wasAdded = false;
    if (progressUpdates.contains(aProgressUpdate)) { return false; }
    StaffMember existingStaffMember = aProgressUpdate.getStaffMember();
    boolean isNewStaffMember = existingStaffMember != null && !this.equals(existingStaffMember);
    if (isNewStaffMember)
    {
      aProgressUpdate.setStaffMember(this);
    }
    else
    {
      progressUpdates.add(aProgressUpdate);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    boolean wasRemoved = false;
    //Unable to remove aProgressUpdate, as it must always have a staffMember
    if (!this.equals(aProgressUpdate.getStaffMember()))
    {
      progressUpdates.remove(aProgressUpdate);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addProgressUpdateAt(ProgressUpdate aProgressUpdate, int index)
  {  
    boolean wasAdded = false;
    if(addProgressUpdate(aProgressUpdate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgressUpdates()) { index = numberOfProgressUpdates() - 1; }
      progressUpdates.remove(aProgressUpdate);
      progressUpdates.add(index, aProgressUpdate);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProgressUpdateAt(ProgressUpdate aProgressUpdate, int index)
  {
    boolean wasAdded = false;
    if(progressUpdates.contains(aProgressUpdate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgressUpdates()) { index = numberOfProgressUpdates() - 1; }
      progressUpdates.remove(aProgressUpdate);
      progressUpdates.add(index, aProgressUpdate);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProgressUpdateAt(aProgressUpdate, index);
    }
    return wasAdded;
  }

  public boolean setStaffManager(StaffManager aStaffManager)
  {
    boolean wasSet = false;
    if (aStaffManager == null)
    {
      return wasSet;
    }

    StaffManager existingStaffManager = staffManager;
    staffManager = aStaffManager;
    if (existingStaffManager != null && !existingStaffManager.equals(aStaffManager))
    {
      existingStaffManager.removeStaffMember(this);
    }
    staffManager.addStaffMember(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=researchRoles.size(); i > 0; i--)
    {
      ResearchRole aResearchRole = researchRoles.get(i - 1);
      aResearchRole.delete();
    }
    while (progressUpdates.size() > 0)
    {
      ProgressUpdate aProgressUpdate = progressUpdates.get(progressUpdates.size() - 1);
      aProgressUpdate.delete();
      progressUpdates.remove(aProgressUpdate);
    }
    
    StaffManager placeholderStaffManager = staffManager;
    this.staffManager = null;
    placeholderStaffManager.removeStaffMember(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staffManager = "+(getStaffManager()!=null?Integer.toHexString(System.identityHashCode(getStaffManager())):"null");
  }
}