package ca.mcgill.ecse321.urlms.controller;

import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Expense;
import ca.mcgill.ecse321.urlms.model.FinancialReport;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.URLMS;

import ca.mcgill.ecse321.urlms.model.FinancialReport;

public class FundingController extends Controller {

	public FundingController() {
		// TODO Auto-generated constructor stub
	}

	// TODO: USE CASES IMPLEMENTATION --------------------------

	/**
	 * This method will generate a financial report of the lab
	 * 
	 * @return a report containing all financial information
	 */
	public FinancialReport generateFinancialReport() {	
		//TODO: remove this when working on implementation
		return null;
	}
	public void addFundingAccount(String fundingType, double fundingBalance) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.addFundingAccount(fundingType, fundingBalance);
	}
	public List<FundingAccount> viewFundingAccounts() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getFundingAccounts();
	}

	/**
	 * This method will add a transaction to the lab
	 * 
	 * @param amount
	 *            of the cost of the transaction by double
	 * @param type
	 *            of transaction by String
	 */
	public void addTransaction(String date, double amount, String type, String fundingAccount) {
		FundingAccount currentFundingAccount;
		try {
			currentFundingAccount = getFundingAccount(fundingAccount);
			Expense aExpense = new Expense(amount, date, type, currentFundingAccount);
			currentFundingAccount.addExpense(aExpense);
			double currentBalance = currentFundingAccount.getBalance();
			currentFundingAccount.setBalance(currentBalance-amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addTransaction(String date, double amount, String type, int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		Expense aExpense = new Expense(amount, date, type, aLab.getFundingAccount(index));
		aLab.getFundingAccount(index).addExpense(aExpense);
	}
	

	public FundingAccount getFundingAccount(String fundingType) throws Exception {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		List<FundingAccount> fundings = aLab.getFundingAccounts();
		for(FundingAccount funding: fundings) {
			if(funding.getType().equals(fundingType)) {
				return funding;
			}
		}
			throw new Exception("Please enter a valid funding account");
	}
	/** This method will initiate funding accounts
	 * 	if funding accounts already exists, method will return 1
	 *  else return -1 
	 */
	public int initiateFundingAccounts() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		if(!aLab.hasFundingAccounts()) {
			addFundingAccount("Supply Funds", 0.00);
			addFundingAccount("Equipment Funds", 0.00);
			addFundingAccount("Staff Funds", 0.00);
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
	 */
	public void editFinancialAccount(String targetType, String newType) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		FundingAccount currentFundingAccount = null;
		try {
			currentFundingAccount = getFundingAccount(targetType);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentFundingAccount.setType(newType);
		

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
		
		FundingAccount currentFundingAccount = null;
		try {
			currentFundingAccount = getFundingAccount(type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentFundingAccount.delete();
	}
	
	/** This method will give a summary of the overall URLMS Funding
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {

		// TODO: remove this when working on implementation
		return null;
	}

}