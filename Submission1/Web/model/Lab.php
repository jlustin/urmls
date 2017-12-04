<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Lab
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lab Attributes
  private $name;

  //Lab Associations

  /**
   * 1 <@>- 1 Staff;
   * 1 <@>- 1 Inventory;
   * 1 <@>- 1 Funding;
   */
  private $staffMembers;
  private $inventoryItems;
  private $fundingAccounts;
  private $reports;
  private $uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aURLMS)
  {
    $this->name = $aName;
    $this->staffMembers = array();
    $this->inventoryItems = array();
    $this->fundingAccounts = array();
    $this->reports = array();
    $didAddURLMS = $this->setURLMS($aURLMS);
    if (!$didAddURLMS)
    {
      throw new Exception("Unable to create lab due to uRLMS");
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

  public function getName()
  {
    return $this->name;
  }

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

  public function getInventoryItem_index($index)
  {
    $aInventoryItem = $this->inventoryItems[$index];
    return $aInventoryItem;
  }

  public function getInventoryItems()
  {
    $newInventoryItems = $this->inventoryItems;
    return $newInventoryItems;
  }

  public function numberOfInventoryItems()
  {
    $number = count($this->inventoryItems);
    return $number;
  }

  public function hasInventoryItems()
  {
    $has = $this->numberOfInventoryItems() > 0;
    return $has;
  }

  public function indexOfInventoryItem($aInventoryItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->inventoryItems as $inventoryItem)
    {
      if ($inventoryItem->equals($aInventoryItem))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFundingAccount_index($index)
  {
    $aFundingAccount = $this->fundingAccounts[$index];
    return $aFundingAccount;
  }

  public function getFundingAccounts()
  {
    $newFundingAccounts = $this->fundingAccounts;
    return $newFundingAccounts;
  }

  public function numberOfFundingAccounts()
  {
    $number = count($this->fundingAccounts);
    return $number;
  }

  public function hasFundingAccounts()
  {
    $has = $this->numberOfFundingAccounts() > 0;
    return $has;
  }

  public function indexOfFundingAccount($aFundingAccount)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->fundingAccounts as $fundingAccount)
    {
      if ($fundingAccount->equals($aFundingAccount))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getReport_index($index)
  {
    $aReport = $this->reports[$index];
    return $aReport;
  }

  public function getReports()
  {
    $newReports = $this->reports;
    return $newReports;
  }

  public function numberOfReports()
  {
    $number = count($this->reports);
    return $number;
  }

  public function hasReports()
  {
    $has = $this->numberOfReports() > 0;
    return $has;
  }

  public function indexOfReport($aReport)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->reports as $report)
    {
      if ($report->equals($aReport))
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

  public function addStaffMemberVia($aName, $aId)
  {
    return new StaffMember($aName, $aId, $this);
  }

  public function addStaffMember($aStaffMember)
  {
    $wasAdded = false;
    if ($this->indexOfStaffMember($aStaffMember) !== -1) { return false; }
    $existingLab = $aStaffMember->getLab();
    $isNewLab = $existingLab != null && $this !== $existingLab;
    if ($isNewLab)
    {
      $aStaffMember->setLab($this);
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
    //Unable to remove aStaffMember, as it must always have a lab
    if ($this !== $aStaffMember->getLab())
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

  public static function minimumNumberOfInventoryItems()
  {
    return 0;
  }

  public function addInventoryItemVia($aName, $aCost, $aCategory)
  {
    return new InventoryItem($aName, $aCost, $aCategory, $this);
  }

  public function addInventoryItem($aInventoryItem)
  {
    $wasAdded = false;
    if ($this->indexOfInventoryItem($aInventoryItem) !== -1) { return false; }
    $existingLab = $aInventoryItem->getLab();
    $isNewLab = $existingLab != null && $this !== $existingLab;
    if ($isNewLab)
    {
      $aInventoryItem->setLab($this);
    }
    else
    {
      $this->inventoryItems[] = $aInventoryItem;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeInventoryItem($aInventoryItem)
  {
    $wasRemoved = false;
    //Unable to remove aInventoryItem, as it must always have a lab
    if ($this !== $aInventoryItem->getLab())
    {
      unset($this->inventoryItems[$this->indexOfInventoryItem($aInventoryItem)]);
      $this->inventoryItems = array_values($this->inventoryItems);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addInventoryItemAt($aInventoryItem, $index)
  {  
    $wasAdded = false;
    if($this->addInventoryItem($aInventoryItem))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInventoryItems()) { $index = $this->numberOfInventoryItems() - 1; }
      array_splice($this->inventoryItems, $this->indexOfInventoryItem($aInventoryItem), 1);
      array_splice($this->inventoryItems, $index, 0, array($aInventoryItem));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveInventoryItemAt($aInventoryItem, $index)
  {
    $wasAdded = false;
    if($this->indexOfInventoryItem($aInventoryItem) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInventoryItems()) { $index = $this->numberOfInventoryItems() - 1; }
      array_splice($this->inventoryItems, $this->indexOfInventoryItem($aInventoryItem), 1);
      array_splice($this->inventoryItems, $index, 0, array($aInventoryItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addInventoryItemAt($aInventoryItem, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfFundingAccounts()
  {
    return 0;
  }

  public function addFundingAccountVia($aType, $aBalance)
  {
    return new FundingAccount($aType, $aBalance, $this);
  }

  public function addFundingAccount($aFundingAccount)
  {
    $wasAdded = false;
    if ($this->indexOfFundingAccount($aFundingAccount) !== -1) { return false; }
    $existingLab = $aFundingAccount->getLab();
    $isNewLab = $existingLab != null && $this !== $existingLab;
    if ($isNewLab)
    {
      $aFundingAccount->setLab($this);
    }
    else
    {
      $this->fundingAccounts[] = $aFundingAccount;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFundingAccount($aFundingAccount)
  {
    $wasRemoved = false;
    //Unable to remove aFundingAccount, as it must always have a lab
    if ($this !== $aFundingAccount->getLab())
    {
      unset($this->fundingAccounts[$this->indexOfFundingAccount($aFundingAccount)]);
      $this->fundingAccounts = array_values($this->fundingAccounts);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFundingAccountAt($aFundingAccount, $index)
  {  
    $wasAdded = false;
    if($this->addFundingAccount($aFundingAccount))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccounts()) { $index = $this->numberOfFundingAccounts() - 1; }
      array_splice($this->fundingAccounts, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccounts, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFundingAccountAt($aFundingAccount, $index)
  {
    $wasAdded = false;
    if($this->indexOfFundingAccount($aFundingAccount) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccounts()) { $index = $this->numberOfFundingAccounts() - 1; }
      array_splice($this->fundingAccounts, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccounts, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFundingAccountAt($aFundingAccount, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfReports()
  {
    return 0;
  }

  public function addReportVia($aTitle, $aDate, $aContent)
  {
    return new Report($aTitle, $aDate, $aContent, $this);
  }

  public function addReport($aReport)
  {
    $wasAdded = false;
    if ($this->indexOfReport($aReport) !== -1) { return false; }
    $existingLab = $aReport->getLab();
    $isNewLab = $existingLab != null && $this !== $existingLab;
    if ($isNewLab)
    {
      $aReport->setLab($this);
    }
    else
    {
      $this->reports[] = $aReport;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeReport($aReport)
  {
    $wasRemoved = false;
    //Unable to remove aReport, as it must always have a lab
    if ($this !== $aReport->getLab())
    {
      unset($this->reports[$this->indexOfReport($aReport)]);
      $this->reports = array_values($this->reports);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addReportAt($aReport, $index)
  {  
    $wasAdded = false;
    if($this->addReport($aReport))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfReports()) { $index = $this->numberOfReports() - 1; }
      array_splice($this->reports, $this->indexOfReport($aReport), 1);
      array_splice($this->reports, $index, 0, array($aReport));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveReportAt($aReport, $index)
  {
    $wasAdded = false;
    if($this->indexOfReport($aReport) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfReports()) { $index = $this->numberOfReports() - 1; }
      array_splice($this->reports, $this->indexOfReport($aReport), 1);
      array_splice($this->reports, $index, 0, array($aReport));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addReportAt($aReport, $index);
    }
    return $wasAdded;
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
    foreach ($this->staffMembers as $aStaffMember)
    {
      $aStaffMember->delete();
    }
    foreach ($this->inventoryItems as $aInventoryItem)
    {
      $aInventoryItem->delete();
    }
    foreach ($this->fundingAccounts as $aFundingAccount)
    {
      $aFundingAccount->delete();
    }
    foreach ($this->reports as $aReport)
    {
      $aReport->delete();
    }
    $placeholderURLMS = $this->uRLMS;
    $this->uRLMS = null;
    $placeholderURLMS->removeLab($this);
  }

}
?>