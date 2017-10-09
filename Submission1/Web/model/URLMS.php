<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class URLMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private $staffManager;
  private $inventoryManager;
  private $fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStaffManager = null, $aInventoryManager = null, $aFundingManager = null)
  {
    if (func_num_args() == 0) { return; }

    if ($aStaffManager == null || $aStaffManager->getURLMS() != null)
    {
      throw new Exception("Unable to create URLMS due to aStaffManager");
    }
    $this->staffManager = $aStaffManager;
    if ($aInventoryManager == null || $aInventoryManager->getURLMS() != null)
    {
      throw new Exception("Unable to create URLMS due to aInventoryManager");
    }
    $this->inventoryManager = $aInventoryManager;
    if ($aFundingManager == null || $aFundingManager->getURLMS() != null)
    {
      throw new Exception("Unable to create URLMS due to aFundingManager");
    }
    $this->fundingManager = $aFundingManager;
  }
  public static function newInstance($aTotalBalanceForFundingManager)
  {
    $thisInstance = new URLMS();
    $thisInstance->staffManager = new StaffManager($thisInstance);
    $thisInstance->inventoryManager = new InventoryManager($thisInstance);
    $thisInstance->fundingManager = new FundingManager($aTotalBalanceForFundingManager, $thisInstance);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStaffManager()
  {
    return $this->staffManager;
  }

  public function getInventoryManager()
  {
    return $this->inventoryManager;
  }

  public function getFundingManager()
  {
    return $this->fundingManager;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $existingStaffManager = $this->staffManager;
    $this->staffManager = null;
    if ($existingStaffManager != null)
    {
      $existingStaffManager->delete();
    }
    $existingInventoryManager = $this->inventoryManager;
    $this->inventoryManager = null;
    if ($existingInventoryManager != null)
    {
      $existingInventoryManager->delete();
    }
    $existingFundingManager = $this->fundingManager;
    $this->fundingManager = null;
    if ($existingFundingManager != null)
    {
      $existingFundingManager->delete();
    }
  }

}
?>