/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 62 "../../../../../../../../ump/tmp574231/model.ump"
// line 140 "../../../../../../../../ump/tmp574231/model.ump"
public class Expense
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expense Attributes
  private double amount;
  private String type;

  //Expense Associations
  private FundingAccount fundingAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Expense(double aAmount, String aType, FundingAccount aFundingAccount)
  {
    amount = aAmount;
    type = aType;
    boolean didAddFundingAccount = setFundingAccount(aFundingAccount);
    if (!didAddFundingAccount)
    {
      throw new RuntimeException("Unable to create expense due to fundingAccount");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(double aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public double getAmount()
  {
    return amount;
  }

  public String getType()
  {
    return type;
  }

  public FundingAccount getFundingAccount()
  {
    return fundingAccount;
  }

  public boolean setFundingAccount(FundingAccount aFundingAccount)
  {
    boolean wasSet = false;
    if (aFundingAccount == null)
    {
      return wasSet;
    }

    FundingAccount existingFundingAccount = fundingAccount;
    fundingAccount = aFundingAccount;
    if (existingFundingAccount != null && !existingFundingAccount.equals(aFundingAccount))
    {
      existingFundingAccount.removeExpense(this);
    }
    fundingAccount.addExpense(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FundingAccount placeholderFundingAccount = fundingAccount;
    this.fundingAccount = null;
    placeholderFundingAccount.removeExpense(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "amount" + ":" + getAmount()+ "," +
            "type" + ":" + getType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fundingAccount = "+(getFundingAccount()!=null?Integer.toHexString(System.identityHashCode(getFundingAccount())):"null");
  }
}