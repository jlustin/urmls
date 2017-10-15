<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class InventoryManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryManager Associations
  private $inventoryItems;
  private $uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aURLMS = null)
  {
    if (func_num_args() == 0) { return; }

    $this->inventoryItems = array();
    if ($aURLMS == null || $aURLMS->getInventoryManager() != null)
    {
      throw new Exception("Unable to create InventoryManager due to aURLMS");
    }
    $this->uRLMS = $aURLMS;
  }
  public static function newInstance($aStaffManagerForURLMS, $aFundingManagerForURLMS)
  {
    $thisInstance = new InventoryManager();
    $this->inventoryItems = array();
    $thisInstance->uRLMS = new URLMS($aStaffManagerForURLMS, $thisInstance, $aFundingManagerForURLMS);
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

  public function getURLMS()
  {
    return $this->uRLMS;
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
    $existingInventoryManager = $aInventoryItem->getInventoryManager();
    $isNewInventoryManager = $existingInventoryManager != null && $this !== $existingInventoryManager;
    if ($isNewInventoryManager)
    {
      $aInventoryItem->setInventoryManager($this);
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
    //Unable to remove aInventoryItem, as it must always have a inventoryManager
    if ($this !== $aInventoryItem->getInventoryManager())
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
    $existingURLMS = $this->uRLMS;
    $this->uRLMS = null;
    if ($existingURLMS != null)
    {
      $existingURLMS->delete();
    }
  }

}
?>