package ca.mcgill.ecse321.urlms.controller;

import java.util.HashMap;

import ca.mcgill.ecse321.urlms.model.FinancialReport;

public class FundingController extends Controller {

	public FundingController() {
		// TODO Auto-generated constructor stub
	}
	
	
	//TODO: USE CASES IMPLEMENTATION --------------------------
	
	
	/** This method will generate a financial report of the lab
	 * @return a report containing all financial information
	 */
	public FinancialReport generateFinancialReport() {
		
		//TODO: remove this when working on implementation
		return null;
	}

	
	
	/** This method will add a transaction to the lab
	 * @param amount of the cost of the transaction by double
	 * @param type of transaction by String
	 */
	public void addTransaction(double amount, String type) {
		
	}

	
	/** This method will get the net balance of the lab
	 * @return the net balance
	 */
	public double viewNetBalance() {
		
		//TODO: remove this when working on implementation
		return 0;
	}
	
	
	
	/** This method will get the balance of a specific account in the lab by type
	 * @param type of the account by String
	 * @return
	 */
	public double[] viewBalanceForSpecificAccount(String type) {
		
		//TODO: remove this when working on implementation
		return null;
	}
	
	
	/** This method will edit a financial account (?)
	 * @param type of account by String
	 */
	public void editFinancialAccount(String type) {
		
	}
	
	
	
	/** This method will give a summary of the overall URLMS Funding
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {
		
		//TODO: remove this when working on implementation
		return null;
	}

}