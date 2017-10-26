<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Funding
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Funding Attributes
  private $totalBalance;

  //Funding Associations
  private $fundingAccounts;
  private $reports;
  private $lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTotalBalance = null, $aLab = null)
  {
    if (func_num_args() == 0) { return; }

    $this->totalBalance = $aTotalBalance;
    $this->fundingAccounts = array();
    $this->reports = array();
    if ($aLab == null || $aLab->getFunding() != null)
    {
      throw new Exception("Unable to create Funding due to aLab");
    }
    $this->lab = $aLab;
  }
  public static function newInstance($aTotalBalance, $aStaffForLab, $aInventoryForLab, $aURLMSForLab)
  {
    $thisInstance = new Funding();
    $thisInstance->totalBalance = $aTotalBalance;
    $this->fundingAccounts = array();
    $this->reports = array();
    $thisInstance->lab = new Lab($aStaffForLab, $aInventoryForLab, $thisInstance, $aURLMSForLab);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTotalBalance($aTotalBalance)
  {
    $wasSet = false;
    $this->totalBalance = $aTotalBalance;
    $wasSet = true;
    return $wasSet;
  }

  public function getTotalBalance()
  {
    return $this->totalBalance;
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

  public function getLab()
  {
    return $this->lab;
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
    $existingFunding = $aFundingAccount->getFunding();
    $isNewFunding = $existingFunding != null && $this !== $existingFunding;
    if ($isNewFunding)
    {
      $aFundingAccount->setFunding($this);
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
    //Unable to remove aFundingAccount, as it must always have a funding
    if ($this !== $aFundingAccount->getFunding())
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

  public function addReportVia($aTitle, $aDate)
  {
    return new Report($aTitle, $aDate, $this);
  }

  public function addReport($aReport)
  {
    $wasAdded = false;
    if ($this->indexOfReport($aReport) !== -1) { return false; }
    $existingFunding = $aReport->getFunding();
    $isNewFunding = $existingFunding != null && $this !== $existingFunding;
    if ($isNewFunding)
    {
      $aReport->setFunding($this);
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
    //Unable to remove aReport, as it must always have a funding
    if ($this !== $aReport->getFunding())
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->fundingAccounts) > 0)
    {
      $aFundingAccount = $this->fundingAccounts[count($this->fundingAccounts) - 1];
      $aFundingAccount->delete();
      unset($this->fundingAccounts[$this->indexOfFundingAccount($aFundingAccount)]);
      $this->fundingAccounts = array_values($this->fundingAccounts);
    }
    
    foreach ($this->reports as $aReport)
    {
      $aReport->delete();
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