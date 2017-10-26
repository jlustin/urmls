<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private $name;
  private $id;

  //StaffMember Associations
  private $researchRoles;
  private $progressUpdates;
  private $staff;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aId, $aStaff)
  {
    $this->name = $aName;
    $this->id = $aId;
    $this->researchRoles = array();
    $this->progressUpdates = array();
    $didAddStaff = $this->setStaff($aStaff);
    if (!$didAddStaff)
    {
      throw new Exception("Unable to create staffMember due to staff");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setId($aId)
  {
    $wasSet = false;
    $this->id = $aId;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getId()
  {
    return $this->id;
  }

  public function getResearchRole_index($index)
  {
    $aResearchRole = $this->researchRoles[$index];
    return $aResearchRole;
  }

  public function getResearchRoles()
  {
    $newResearchRoles = $this->researchRoles;
    return $newResearchRoles;
  }

  public function numberOfResearchRoles()
  {
    $number = count($this->researchRoles);
    return $number;
  }

  public function hasResearchRoles()
  {
    $has = $this->numberOfResearchRoles() > 0;
    return $has;
  }

  public function indexOfResearchRole($aResearchRole)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->researchRoles as $researchRole)
    {
      if ($researchRole->equals($aResearchRole))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgressUpdate_index($index)
  {
    $aProgressUpdate = $this->progressUpdates[$index];
    return $aProgressUpdate;
  }

  public function getProgressUpdates()
  {
    $newProgressUpdates = $this->progressUpdates;
    return $newProgressUpdates;
  }

  public function numberOfProgressUpdates()
  {
    $number = count($this->progressUpdates);
    return $number;
  }

  public function hasProgressUpdates()
  {
    $has = $this->numberOfProgressUpdates() > 0;
    return $has;
  }

  public function indexOfProgressUpdate($aProgressUpdate)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->progressUpdates as $progressUpdate)
    {
      if ($progressUpdate->equals($aProgressUpdate))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaff()
  {
    return $this->staff;
  }

  public static function minimumNumberOfResearchRoles()
  {
    return 0;
  }

  public function addResearchRoleVia()
  {
    return new ResearchRole($this);
  }

  public function addResearchRole($aResearchRole)
  {
    $wasAdded = false;
    if ($this->indexOfResearchRole($aResearchRole) !== -1) { return false; }
    $existingStaffMember = $aResearchRole->getStaffMember();
    $isNewStaffMember = $existingStaffMember != null && $this !== $existingStaffMember;
    if ($isNewStaffMember)
    {
      $aResearchRole->setStaffMember($this);
    }
    else
    {
      $this->researchRoles[] = $aResearchRole;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeResearchRole($aResearchRole)
  {
    $wasRemoved = false;
    //Unable to remove aResearchRole, as it must always have a staffMember
    if ($this !== $aResearchRole->getStaffMember())
    {
      unset($this->researchRoles[$this->indexOfResearchRole($aResearchRole)]);
      $this->researchRoles = array_values($this->researchRoles);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addResearchRoleAt($aResearchRole, $index)
  {  
    $wasAdded = false;
    if($this->addResearchRole($aResearchRole))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfResearchRoles()) { $index = $this->numberOfResearchRoles() - 1; }
      array_splice($this->researchRoles, $this->indexOfResearchRole($aResearchRole), 1);
      array_splice($this->researchRoles, $index, 0, array($aResearchRole));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveResearchRoleAt($aResearchRole, $index)
  {
    $wasAdded = false;
    if($this->indexOfResearchRole($aResearchRole) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfResearchRoles()) { $index = $this->numberOfResearchRoles() - 1; }
      array_splice($this->researchRoles, $this->indexOfResearchRole($aResearchRole), 1);
      array_splice($this->researchRoles, $index, 0, array($aResearchRole));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addResearchRoleAt($aResearchRole, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public function addProgressUpdateVia($aDate)
  {
    return new ProgressUpdate($aDate, $this);
  }

  public function addProgressUpdate($aProgressUpdate)
  {
    $wasAdded = false;
    if ($this->indexOfProgressUpdate($aProgressUpdate) !== -1) { return false; }
    $existingStaffMember = $aProgressUpdate->getStaffMember();
    $isNewStaffMember = $existingStaffMember != null && $this !== $existingStaffMember;
    if ($isNewStaffMember)
    {
      $aProgressUpdate->setStaffMember($this);
    }
    else
    {
      $this->progressUpdates[] = $aProgressUpdate;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeProgressUpdate($aProgressUpdate)
  {
    $wasRemoved = false;
    //Unable to remove aProgressUpdate, as it must always have a staffMember
    if ($this !== $aProgressUpdate->getStaffMember())
    {
      unset($this->progressUpdates[$this->indexOfProgressUpdate($aProgressUpdate)]);
      $this->progressUpdates = array_values($this->progressUpdates);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addProgressUpdateAt($aProgressUpdate, $index)
  {  
    $wasAdded = false;
    if($this->addProgressUpdate($aProgressUpdate))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveProgressUpdateAt($aProgressUpdate, $index)
  {
    $wasAdded = false;
    if($this->indexOfProgressUpdate($aProgressUpdate) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addProgressUpdateAt($aProgressUpdate, $index);
    }
    return $wasAdded;
  }

  public function setStaff($aStaff)
  {
    $wasSet = false;
    if ($aStaff == null)
    {
      return $wasSet;
    }
    
    $existingStaff = $this->staff;
    $this->staff = $aStaff;
    if ($existingStaff != null && $existingStaff != $aStaff)
    {
      $existingStaff->removeStaffMember($this);
    }
    $this->staff->addStaffMember($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->researchRoles as $aResearchRole)
    {
      $aResearchRole->delete();
    }
    while (count($this->progressUpdates) > 0)
    {
      $aProgressUpdate = $this->progressUpdates[count($this->progressUpdates) - 1];
      $aProgressUpdate->delete();
      unset($this->progressUpdates[$this->indexOfProgressUpdate($aProgressUpdate)]);
      $this->progressUpdates = array_values($this->progressUpdates);
    }
    
    $placeholderStaff = $this->staff;
    $this->staff = null;
    $placeholderStaff->removeStaffMember($this);
  }

}
?>