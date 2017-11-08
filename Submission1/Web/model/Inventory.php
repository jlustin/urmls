<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Inventory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Inventory Associations
  private $inventoryItems;
  private $lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aLab = null)
  {
    if (func_num_args() == 0) { return; }

    $this->inventoryItems = array();
    if ($aLab == null || $aLab->getInventory() != null)
    {
      throw new Exception("Unable to create Inventory due to aLab");
    }
    $this->lab = $aLab;
  }
  public static function newInstance($aStaffForLab, $aFundingForLab, $aURLMSForLab)
  {
    $thisInstance = new Inventory();
    $this->inventoryItems = array();
    $thisInstance->lab = new Lab($aStaffForLab, $thisInstance, $aFundingForLab, $aURLMSForLab);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public function getLab()
  {
    return $this->lab;
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
    $existingInventory = $aInventoryItem->getInventory();
    $isNewInventory = $existingInventory != null && $this !== $existingInventory;
    if ($isNewInventory)
    {
      $aInventoryItem->setInventory($this);
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
    //Unable to remove aInventoryItem, as it must always have a inventory
    if ($this == $aInventoryItem->getInventory())
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->inventoryItems as $aInventoryItem)
    {
      $aInventoryItem->delete();
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