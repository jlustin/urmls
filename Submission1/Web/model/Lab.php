<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Lab
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lab Associations
  private $staff;
  private $inventory;
  private $funding;
  private $uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStaff = null, $aInventory = null, $aFunding = null, $aURLMS = null)
  {
    if (func_num_args() == 0) { return; }

    if ($aStaff == null || $aStaff->getLab() != null)
    {
      throw new Exception("Unable to create Lab due to aStaff");
    }
    $this->staff = $aStaff;
    if ($aInventory == null || $aInventory->getLab() != null)
    {
      throw new Exception("Unable to create Lab due to aInventory");
    }
    $this->inventory = $aInventory;
    if ($aFunding == null || $aFunding->getLab() != null)
    {
      throw new Exception("Unable to create Lab due to aFunding");
    }
    $this->funding = $aFunding;
    $didAddURLMS = $this->setURLMS($aURLMS);
    if (!$didAddURLMS)
    {
      throw new Exception("Unable to create lab due to uRLMS");
    }
  }
  public static function newInstance($aTotalBalanceForFunding, $aURLMS)
  {
    $thisInstance = new Lab();
    $thisInstance->staff = new Staff($thisInstance);
    $thisInstance->inventory = new Inventory($thisInstance);
    $thisInstance->funding = new Funding($aTotalBalanceForFunding, $thisInstance);
//     $this->uRLMSs = array();
//     $this->uRLMSs[] = $aURLMS;
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStaff()
  {
    return $this->staff;
  }

  public function getInventory()
  {
    return $this->inventory;
  }

  public function getFunding()
  {
    return $this->funding;
  }

  public function getURLMS()
  {
    return $this->uRLMS;
  }

  public function setURLMS($aURLMS)
  {
    $wasSet = false;
    if ($aURLMS == null)
    {
      return $wasSet;
    }
    
    $existingURLMS = $this->uRLMS;
    $this->uRLMS = $aURLMS;
    if ($existingURLMS != null && $existingURLMS != $aURLMS)
    {
      $existingURLMS->removeLab($this);
    }
    $this->uRLMS->addLab($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $existingStaff = $this->staff;
    $this->staff = null;
    if ($existingStaff != null)
    {
      $existingStaff->delete();
    }
    $existingInventory = $this->inventory;
    $this->inventory = null;
    if ($existingInventory != null)
    {
      $existingInventory->delete();
    }
    $existingFunding = $this->funding;
    $this->funding = null;
    if ($existingFunding != null)
    {
      $existingFunding->delete();
    }
    $placeholderURLMS = $this->uRLMS;
    $this->uRLMS = null;
    $placeholderURLMS->removeLab($this);
  }

}
?>