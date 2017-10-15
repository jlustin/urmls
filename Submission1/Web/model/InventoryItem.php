<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class InventoryItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryItem Attributes
  private $name;
  private $cost;
  private $category;

  //InventoryItem Associations
  private $inventoryManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aCost, $aCategory, $aInventoryManager)
  {
    $this->name = $aName;
    $this->cost = $aCost;
    $this->category = $aCategory;
    $didAddInventoryManager = $this->setInventoryManager($aInventoryManager);
    if (!$didAddInventoryManager)
    {
      throw new Exception("Unable to create inventoryItem due to inventoryManager");
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

  public function setCost($aCost)
  {
    $wasSet = false;
    $this->cost = $aCost;
    $wasSet = true;
    return $wasSet;
  }

  public function setCategory($aCategory)
  {
    $wasSet = false;
    $this->category = $aCategory;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getCost()
  {
    return $this->cost;
  }

  public function getCategory()
  {
    return $this->category;
  }

  public function getInventoryManager()
  {
    return $this->inventoryManager;
  }

  public function setInventoryManager($aInventoryManager)
  {
    $wasSet = false;
    if ($aInventoryManager == null)
    {
      return $wasSet;
    }
    
    $existingInventoryManager = $this->inventoryManager;
    $this->inventoryManager = $aInventoryManager;
    if ($existingInventoryManager != null && $existingInventoryManager != $aInventoryManager)
    {
      $existingInventoryManager->removeInventoryItem($this);
    }
    $this->inventoryManager->addInventoryItem($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderInventoryManager = $this->inventoryManager;
    $this->inventoryManager = null;
    $placeholderInventoryManager->removeInventoryItem($this);
  }

}
?>