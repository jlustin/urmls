<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private $title;
  private $date;

  //Report Associations
  private $fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aDate, $aFundingManager)
  {
    $this->title = $aTitle;
    $this->date = $aDate;
    $didAddFundingManager = $this->setFundingManager($aFundingManager);
    if (!$didAddFundingManager)
    {
      throw new Exception("Unable to create report due to fundingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTitle($aTitle)
  {
    $wasSet = false;
    $this->title = $aTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setDate($aDate)
  {
    $wasSet = false;
    $this->date = $aDate;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getDate()
  {
    return $this->date;
  }

  public function getFundingManager()
  {
    return $this->fundingManager;
  }

  public function setFundingManager($aFundingManager)
  {
    $wasSet = false;
    if ($aFundingManager == null)
    {
      return $wasSet;
    }
    
    $existingFundingManager = $this->fundingManager;
    $this->fundingManager = $aFundingManager;
    if ($existingFundingManager != null && $existingFundingManager != $aFundingManager)
    {
      $existingFundingManager->removeReport($this);
    }
    $this->fundingManager->addReport($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderFundingManager = $this->fundingManager;
    $this->fundingManager = null;
    $placeholderFundingManager->removeReport($this);
  }

}
?>