/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 40 "../../../../../URLMS.ump"
public class FundingAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FundingAccount Attributes
  private String type;
  private double balance;

  //FundingAccount Associations
  private List<Expense> expenses;
  private FundingManager fundingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FundingAccount(String aType, double aBalance, FundingManager aFundingManager)
  {
    type = aType;
    balance = aBalance;
    expenses = new ArrayList<Expense>();
    boolean didAddFundingManager = setFundingManager(aFundingManager);
    if (!didAddFundingManager)
    {
      throw new RuntimeException("Unable to create fundingAccount due to fundingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(double aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public String getType()
  {
    return type;
  }

  public double getBalance()
  {
    return balance;
  }

  public Expense getExpense(int index)
  {
    Expense aExpense = expenses.get(index);
    return aExpense;
  }

  public List<Expense> getExpenses()
  {
    List<Expense> newExpenses = Collections.unmodifiableList(expenses);
    return newExpenses;
  }

  public int numberOfExpenses()
  {
    int number = expenses.size();
    return number;
  }

  public boolean hasExpenses()
  {
    boolean has = expenses.size() > 0;
    return has;
  }

  public int indexOfExpense(Expense aExpense)
  {
    int index = expenses.indexOf(aExpense);
    return index;
  }

  public FundingManager getFundingManager()
  {
    return fundingManager;
  }

  public static int minimumNumberOfExpenses()
  {
    return 0;
  }

  public Expense addExpense(double aAmount, String aType)
  {
    return new Expense(aAmount, aType, this);
  }

  public boolean addExpense(Expense aExpense)
  {
    boolean wasAdded = false;
    if (expenses.contains(aExpense)) { return false; }
    FundingAccount existingFundingAccount = aExpense.getFundingAccount();
    boolean isNewFundingAccount = existingFundingAccount != null && !this.equals(existingFundingAccount);
    if (isNewFundingAccount)
    {
      aExpense.setFundingAccount(this);
    }
    else
    {
      expenses.add(aExpense);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeExpense(Expense aExpense)
  {
    boolean wasRemoved = false;
    //Unable to remove aExpense, as it must always have a fundingAccount
    if (!this.equals(aExpense.getFundingAccount()))
    {
      expenses.remove(aExpense);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addExpenseAt(Expense aExpense, int index)
  {  
    boolean wasAdded = false;
    if(addExpense(aExpense))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenses()) { index = numberOfExpenses() - 1; }
      expenses.remove(aExpense);
      expenses.add(index, aExpense);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveExpenseAt(Expense aExpense, int index)
  {
    boolean wasAdded = false;
    if(expenses.contains(aExpense))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenses()) { index = numberOfExpenses() - 1; }
      expenses.remove(aExpense);
      expenses.add(index, aExpense);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addExpenseAt(aExpense, index);
    }
    return wasAdded;
  }

  public boolean setFundingManager(FundingManager aFundingManager)
  {
    boolean wasSet = false;
    if (aFundingManager == null)
    {
      return wasSet;
    }

    FundingManager existingFundingManager = fundingManager;
    fundingManager = aFundingManager;
    if (existingFundingManager != null && !existingFundingManager.equals(aFundingManager))
    {
      existingFundingManager.removeFundingAccount(this);
    }
    fundingManager.addFundingAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=expenses.size(); i > 0; i--)
    {
      Expense aExpense = expenses.get(i - 1);
      aExpense.delete();
    }
    FundingManager placeholderFundingManager = fundingManager;
    this.fundingManager = null;
    placeholderFundingManager.removeFundingAccount(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "type" + ":" + getType()+ "," +
            "balance" + ":" + getBalance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fundingManager = "+(getFundingManager()!=null?Integer.toHexString(System.identityHashCode(getFundingManager())):"null");
  }
}