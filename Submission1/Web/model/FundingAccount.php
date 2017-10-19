<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class FundingAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FundingAccount Attributes
  private $type;
  private $balance;

  //FundingAccount Associations
  private $expenses;
  private $fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aType, $aBalance, $aFundingManager)
  {
    $this->type = $aType;
    $this->balance = $aBalance;
    $this->expenses = array();
    $didAddFundingManager = $this->setFundingManager($aFundingManager);
    if (!$didAddFundingManager)
    {
      throw new Exception("Unable to create fundingAccount due to fundingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setType($aType)
  {
    $wasSet = false;
    $this->type = $aType;
    $wasSet = true;
    return $wasSet;
  }

  public function setBalance($aBalance)
  {
    $wasSet = false;
    $this->balance = $aBalance;
    $wasSet = true;
    return $wasSet;
  }

  public function getType()
  {
    return $this->type;
  }

  public function getBalance()
  {
    return $this->balance;
  }

  public function getExpense_index($index)
  {
    $aExpense = $this->expenses[$index];
    return $aExpense;
  }

  public function getExpenses()
  {
    $newExpenses = $this->expenses;
    return $newExpenses;
  }

  public function numberOfExpenses()
  {
    $number = count($this->expenses);
    return $number;
  }

  public function hasExpenses()
  {
    $has = $this->numberOfExpenses() > 0;
    return $has;
  }

  public function indexOfExpense($aExpense)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->expenses as $expense)
    {
      if ($expense->equals($aExpense))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFundingManager()
  {
    return $this->fundingManager;
  }

  public static function minimumNumberOfExpenses()
  {
    return 0;
  }

  public function addExpenseVia($aAmount, $aType)
  {
    return new Expense($aAmount, $aType, $this);
  }

  public function addExpense($aExpense)
  {
    $wasAdded = false;
    if ($this->indexOfExpense($aExpense) !== -1) { return false; }
    $existingFundingAccount = $aExpense->getFundingAccount();
    $isNewFundingAccount = $existingFundingAccount != null && $this !== $existingFundingAccount;
    if ($isNewFundingAccount)
    {
      $aExpense->setFundingAccount($this);
    }
    else
    {
      $this->expenses[] = $aExpense;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeExpense($aExpense)
  {
    $wasRemoved = false;
    //Unable to remove aExpense, as it must always have a fundingAccount
    if ($this !== $aExpense->getFundingAccount())
    {
      unset($this->expenses[$this->indexOfExpense($aExpense)]);
      $this->expenses = array_values($this->expenses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addExpenseAt($aExpense, $index)
  {  
    $wasAdded = false;
    if($this->addExpense($aExpense))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfExpenses()) { $index = $this->numberOfExpenses() - 1; }
      array_splice($this->expenses, $this->indexOfExpense($aExpense), 1);
      array_splice($this->expenses, $index, 0, array($aExpense));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveExpenseAt($aExpense, $index)
  {
    $wasAdded = false;
    if($this->indexOfExpense($aExpense) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfExpenses()) { $index = $this->numberOfExpenses() - 1; }
      array_splice($this->expenses, $this->indexOfExpense($aExpense), 1);
      array_splice($this->expenses, $index, 0, array($aExpense));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addExpenseAt($aExpense, $index);
    }
    return $wasAdded;
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
      $existingFundingManager->removeFundingAccount($this);
    }
    $this->fundingManager->addFundingAccount($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->expenses as $aExpense)
    {
      $aExpense->delete();
    }
    $placeholderFundingManager = $this->fundingManager;
    $this->fundingManager = null;
    $placeholderFundingManager->removeFundingAccount($this);
  }

}
?>