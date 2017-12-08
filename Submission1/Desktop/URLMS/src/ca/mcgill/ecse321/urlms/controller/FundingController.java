package ca.mcgill.ecse321.urlms.controller;

import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Expense;
import ca.mcgill.ecse321.urlms.model.FinancialReport;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class FundingController extends Controller {

	public FundingController() {
		// TODO Auto-generated constructor stub
	}

	// TODO: USE CASES IMPLEMENTATION --------------------------

	/**
	 * This method will generate a financial report of the lab
	 * 
	 * @return a report containing all financial information
	 * @throws InvalidInputException 
	 */
	public void addFundingAccount(String fundingType, double fundingBalance) throws InvalidInputException {
		String error = "";
		
		if(fundingType == null || fundingType.isEmpty()){
			error += "Please enter a name for the account. ";
		}
		
		//Assuming that you can start with negative balance
		//So no exception handling for that case
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		try{
		aLab.addFundingAccount(fundingType, fundingBalance);
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}
	public List<FundingAccount> viewFundingAccounts() throws InvalidInputException {
		String error = "";
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		List<FundingAccount> accountsList;
		try{
			accountsList = aLab.getFundingAccounts();
			if(accountsList.isEmpty()){
				error = "There is no funding accounts to display.";
			}
		}catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		return accountsList;
	}

	/**
	 * This method will add a transaction to the lab
	 * 
	 * @param amount
	 *            of the cost of the transaction by double
	 * @param type
	 *            of transaction by String
	 * @throws InvalidInputException 
	 */
	public void addTransaction(String date, double amount, String type, String fundingAccount) throws InvalidInputException {
		String error = "";
		
		if(date == null || date.isEmpty()){
			error += "Please enter a date. ";
		}
		if(type == null || type.isEmpty()){
			error += "Please enter a transaction type. ";
		}
		if(fundingAccount == null || fundingAccount.isEmpty()){
			error += "Please enter a funding account name";
		}
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		FundingAccount currentFundingAccount;
		try {
			currentFundingAccount = getFundingAccount(fundingAccount);
			Expense aExpense = new Expense(amount, date, type, currentFundingAccount);
			currentFundingAccount.addExpense(aExpense);
			double currentBalance = currentFundingAccount.getBalance();
			currentFundingAccount.setBalance(currentBalance-amount);
		} catch (Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	//TODO exceptions
	public void addTransaction(String date, double amount, String type, int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		Expense aExpense = new Expense(amount, date, type, aLab.getFundingAccount(index));
		aLab.getFundingAccount(index).addExpense(aExpense);
	}
	

	public FundingAccount getFundingAccount(String fundingType) throws InvalidInputException {
		String error = "";
		
		if(fundingType == null || fundingType.isEmpty()){
			error += "Please enter a name for the account. ";
		}
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		List<FundingAccount> fundings = aLab.getFundingAccounts();
		for(FundingAccount funding: fundings) {
			if(funding.getType().equals(fundingType)) {
				return funding;
			}
		}
		throw new InvalidInputException("Please enter an existing funding account");
	}
	/** This method will initiate funding accounts
	 * 	if funding accounts already exists, method will return 1
	 *  else return -1 
	 */
	public int initiateFundingAccounts() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		if(!aLab.hasFundingAccounts()) {
			try {
				addFundingAccount("Supply Funds", 0.00);
				addFundingAccount("Equipment Funds", 0.00);
				addFundingAccount("Staff Funds", 0.00);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return -1;
		}
		return 1;
	}
	/** This method will get the net balance of the lab
	 * @return the net balance
	 */
	public double viewNetBalance() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		List<FundingAccount> fundings = aLab.getFundingAccounts();
		double netBalance = 0;
		for(FundingAccount funding: fundings) {
			netBalance+=funding.getBalance();
		}
		return netBalance;
	}

	/**
	 * This method will get the balance of a specific account in the lab by type
	 * 
	 * @param type
	 *            of the account by String
	 * @return
	 */
	public double[] viewBalanceForSpecificAccount(String type) {

		// TODO: remove this when working on implementation
		return null;
	}
	public void addFunding(int index, double amount) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		double currentBalance = aLab.getFundingAccount(index).getBalance();
		aLab.getFundingAccount(index).setBalance(currentBalance+amount);
	}
	
	
	/** This method will edit a financial account (?)
	 * @param type of account by String
	 * @throws InvalidInputException 
	 */
	public void editFinancialAccount(String targetType, String newType) throws InvalidInputException {
		String error = "";
		
		if(targetType == null || targetType.isEmpty()){
			error += "Please enter the old name for the account. ";
		}
		if(newType == null || newType.isEmpty()){
			error += "Please enter the new name for the account. ";
		}
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		FundingAccount currentFundingAccount = null;
		try {
			currentFundingAccount = getFundingAccount(targetType);
			currentFundingAccount.setType(newType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String viewFundingAccountType(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getFundingAccount(index).getType();
		
	}
	public String viewFundingAccountBalance(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return String.valueOf(aLab.getFundingAccount(index).getBalance());
	}
	public List<Expense> viewFundingAccountExpenses(String fundingType){
		String error = "";
		
		if(fundingType == null || fundingType.isEmpty()){
			error += "Please enter the name for the account. ";
		}
		
		FundingAccount currentFundingAccount;
		try {
			currentFundingAccount = getFundingAccount(fundingType);
			return currentFundingAccount.getExpenses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Expense> viewFundingAccountExpenses(int index){
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getFundingAccount(index).getExpenses();
	}
	
	public Expense getExpense(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getFundingAccount(index).getExpenses().get(index);
	}
	public void removeFundingAccount(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getFundingAccount(index).delete();
	}
	
	public void removeFundingAccount(String type) {
		String error = "";
		
		if(type == null || type.isEmpty()){
			error += "Please enter the name for the account. ";
		}
		
		FundingAccount currentFundingAccount = null;
		try {
			currentFundingAccount = getFundingAccount(type);
			currentFundingAccount.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}