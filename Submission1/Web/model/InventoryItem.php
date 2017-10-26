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
  private $inventory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aCost, $aCategory, $aInventory)
  {
    $this->name = $aName;
    $this->cost = $aCost;
    $this->category = $aCategory;
    $didAddInventory = $this->setInventory($aInventory);
    if (!$didAddInventory)
    {
      throw new Exception("Unable to create inventoryItem due to inventory");
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

  public function getInventory()
  {
    return $this->inventory;
  }

  public function setInventory($aInventory)
  {
    $wasSet = false;
    if ($aInventory == null)
    {
      return $wasSet;
    }
    
    $existingInventory = $this->inventory;
    $this->inventory = $aInventory;
    if ($existingInventory != null && $existingInventory != $aInventory)
    {
      $existingInventory->removeInventoryItem($this);
    }
    $this->inventory->addInventoryItem($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderInventory = $this->inventory;
    $this->inventory = null;
    $placeholderInventory->removeInventoryItem($this);
  }

}
?>