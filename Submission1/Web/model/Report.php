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
  private $funding;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aDate, $aFunding)
  {
    $this->title = $aTitle;
    $this->date = $aDate;
    $didAddFunding = $this->setFunding($aFunding);
    if (!$didAddFunding)
    {
      throw new Exception("Unable to create report due to funding");
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

  public function getFunding()
  {
    return $this->funding;
  }

  public function setFunding($aFunding)
  {
    $wasSet = false;
    if ($aFunding == null)
    {
      return $wasSet;
    }
    
    $existingFunding = $this->funding;
    $this->funding = $aFunding;
    if ($existingFunding != null && $existingFunding != $aFunding)
    {
      $existingFunding->removeReport($this);
    }
    $this->funding->addReport($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderFunding = $this->funding;
    $this->funding = null;
    $placeholderFunding->removeReport($this);
  }

}
?>