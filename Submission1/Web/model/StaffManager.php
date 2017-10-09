<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class StaffManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffManager Associations
  private $staffMembers;
  private $uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aURLMS = null)
  {
    if (func_num_args() == 0) { return; }

    $this->staffMembers = array();
    if ($aURLMS == null || $aURLMS->getStaffManager() != null)
    {
      throw new Exception("Unable to create StaffManager due to aURLMS");
    }
    $this->uRLMS = $aURLMS;
  }
  public static function newInstance($aInventoryManagerForURLMS, $aFundingManagerForURLMS)
  {
    $thisInstance = new StaffManager();
    $this->staffMembers = array();
    $thisInstance->uRLMS = new URLMS($thisInstance, $aInventoryManagerForURLMS, $aFundingManagerForURLMS);
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

  public function getURLMS()
  {
    return $this->uRLMS;
  }

  public static function minimumNumberOfStaffMembers()
  {
    return 0;
  }

  public function addStaffMemberVia()
  {
    return new StaffMember($this);
  }

  public function addStaffMember($aStaffMember)
  {
    $wasAdded = false;
    if ($this->indexOfStaffMember($aStaffMember) !== -1) { return false; }
    $existingStaffManager = $aStaffMember->getStaffManager();
    $isNewStaffManager = $existingStaffManager != null && $this !== $existingStaffManager;
    if ($isNewStaffManager)
    {
      $aStaffMember->setStaffManager($this);
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
    //Unable to remove aStaffMember, as it must always have a staffManager
    if ($this !== $aStaffMember->getStaffManager())
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
    $existingURLMS = $this->uRLMS;
    $this->uRLMS = null;
    if ($existingURLMS != null)
    {
      $existingURLMS->delete();
    }
  }

}
?>