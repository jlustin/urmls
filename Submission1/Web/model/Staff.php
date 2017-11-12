<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Associations
  private $staffMembers;
  private $lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aLab = null)
  {
    if (func_num_args() == 0) { return; }

    $this->staffMembers = array();
    if ($aLab == null || $aLab->getStaff() != null)
    {
      throw new Exception("Unable to create Staff due to aLab");
    }
    $this->lab = $aLab;
  }
  public static function newInstance($aInventoryForLab, $aFundingForLab, $aURLMSForLab)
  {
    $thisInstance = new Staff();
    $this->staffMembers = array();
    $thisInstance->lab = new Lab($thisInstance, $aInventoryForLab, $aFundingForLab, $aURLMSForLab);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStaffMember_index($index)
  {
    $aStaffMember = $this->staffMembers[$index];
    return $aStaffMember;
  }

  public function getStaffMembers()
  {
    $newStaffMembers = $this->staffMembers;
    return $newStaffMembers;
  }

  public function numberOfStaffMembers()
  {
    $number = count($this->staffMembers);
    return $number;
  }

  public function hasStaffMembers()
  {
    $has = $this->numberOfStaffMembers() > 0;
    return $has;
  }

  public function indexOfStaffMember($aStaffMember)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffMembers as $staffMember)
    {
      if ($staffMember->equals($aStaffMember))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getLab()
  {
    return $this->lab;
  }

  public static function minimumNumberOfStaffMembers()
  {
    return 0;
  }

  public function addStaffMemberVia($aName, $aId)
  {
    return new StaffMember($aName, $aId, $this);
  }

  public function addStaffMember($aStaffMember)
  {
    $wasAdded = false;
    if ($this->indexOfStaffMember($aStaffMember) !== -1) { return false; }
    $existingStaff = $aStaffMember->getStaff();
    $isNewStaff = $existingStaff != null && $this !== $existingStaff;
    if ($isNewStaff)
    {
      $aStaffMember->setStaff($this);
    }
    else
    {
      $this->staffMembers[] = $aStaffMember;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStaffMember($aStaffMember)
  {
    $wasRemoved = false;
    //Unable to remove aStaffMember, as it must always have a staff
    if (!$this == $aStaffMember->getStaff())
    {
      unset($this->staffMembers[$this->indexOfStaffMember($aStaffMember)]);
      $this->staffMembers = array_values($this->staffMembers);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStaffMemberAt($aStaffMember, $index)
  {  
    $wasAdded = false;
    if($this->addStaffMember($aStaffMember))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffMembers()) { $index = $this->numberOfStaffMembers() - 1; }
      array_splice($this->staffMembers, $this->indexOfStaffMember($aStaffMember), 1);
      array_splice($this->staffMembers, $index, 0, array($aStaffMember));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffMemberAt($aStaffMember, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaffMember($aStaffMember) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffMembers()) { $index = $this->numberOfStaffMembers() - 1; }
      array_splice($this->staffMembers, $this->indexOfStaffMember($aStaffMember), 1);
      array_splice($this->staffMembers, $index, 0, array($aStaffMember));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffMemberAt($aStaffMember, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->staffMembers as $aStaffMember)
    {
      $aStaffMember->delete();
    }
    $existingLab = $this->lab;
    $this->lab = null;
    if ($existingLab != null)
    {
      $existingLab->delete();
    }
  }

}
?>