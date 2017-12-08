package ca.mcgill.ecse321.urlms.controller;

import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Equipment;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.SupplyType;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class InventoryController extends Controller {

	public InventoryController() {
		// TODO Auto-generated constructor stub
	}

	// TODO USE CASES IMPLEMENTATION ----------------------------------

	/**
	 * This method will get the inventory item list
	 * 
	 * @return a list of the inventory items
	 * @throws InvalidInputException 
	 */
	public List<InventoryItem> viewInventoryList() throws InvalidInputException {
		String error = "";

		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);

		List<InventoryItem> inventorylist;
		
		try {
			inventorylist = aLab.getInventoryItems();
			if (inventorylist.isEmpty()) {
				error = "There are no inventory items to display :(";
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		return inventorylist;
	}

	/**
	 * This method will add an item to the inventory list
	 * 
	 * @param name
	 *            of the item by String
	 * @param cost
	 *            of the item by double
	 * @param category
	 *            of item by String
	 * @throws InvalidInputException 
	 */
	public void addInventoryItem(String name, double cost) throws InvalidInputException {
		String error = "";
		
		if(name == null || name.isEmpty()){
			error += "Please enter a name. ";
		}
		if(cost < 0){
			error += "Please enter a valid cost. ";
		}
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		try{
		aLab.addInventoryItem(name, cost, null);
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * This method will add an equipment to the inventory list
	 * 
	 * @param aName
	 *            of the item by String
	 * @param cost
	 *            of the item by double
	 * @throws InvalidInputException 
	 */
	public void addEquipmentItem(String aName, double cost) throws InvalidInputException{
		String error = "";
		
		if(aName == null || aName.isEmpty()){
			error += "Please enter a name. ";
		}
		if(cost < 0){
			error += "Please enter a valid cost. ";
		}
		
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		try{
		Equipment temp = new Equipment(aName, cost, "Equipment", aLab, false);
		aLab.addInventoryItem(temp);
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * This method will add a supply type to the inventory list
	 * 
	 * @param aName
	 *            of the item by String
	 * @param cost
	 *            of the item by double
	 * @param quantity
	 *            of item by int
	 * @throws InvalidInputException 
	 */
	public void addSupplyItem(String aName, double cost, int quantity) throws InvalidInputException {
		String error = "";
		
		if(aName == null || aName.isEmpty()){
			error += "Please enter a name. ";
		}
		if(cost < 0){
			error += "Please enter a valid cost. ";
		}
		if(quantity <= 0){
			error += "Please enter a valid quantity. ";
		}
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		try{
		SupplyType temp = new SupplyType(aName, cost, "Supply", aLab, quantity);
		aLab.addInventoryItem(temp);
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * This method will remove an item from the inventory list
	 * 
	 * @param index
	 *            of Inventory Item by int
	 */
	public void removeInventoryItem(int index) {//TODO implement with search by name (not index)
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getInventoryItem(index).delete();
	}

	public String viewInventoryItemName(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getInventoryItem(index).getName();
	}

	public String viewInventoryItemCost(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return String.valueOf(aLab.getInventoryItem(index).getCost());
	}

	public String viewSupplyItemQuantity(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		if (aLab.getInventoryItem(index) instanceof SupplyType) {
			SupplyType temp = (SupplyType) aLab.getInventoryItem(index);
			return String.valueOf(temp.getQuantity());
		} else
			return "N/A";
	}

	/**
	 * This method will edit the details of a specific item inventory
	 * 
	 * @param name
	 *            of the the inventory item by String
	 * @throws InvalidInputException 
	 */
	public void editInventoryItemDetails(int index, String desiredName, double desiredCost, int desiredQuantity) throws InvalidInputException {
		//TODO Not use index to retrieve item but use a search by name
		String error = "";
		
		if(desiredName == null || desiredName.isEmpty()){
			error += "Please enter a name. ";
		}
		if(desiredCost < 0){
			error += "Please enter a valid cost. ";
		}
		if(desiredQuantity <= 0){
			error += "Please enter a valid quantity. ";
		}
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		try{
			if (aLab.getInventoryItem(index) instanceof SupplyType) {
				SupplyType temp = (SupplyType) aLab.getInventoryItem(index);
				temp.setName(desiredName);
				temp.setCost(desiredCost);
				temp.setQuantity(desiredQuantity);
			} else {
				Equipment temp = (Equipment) aLab.getInventoryItem(index);
				temp.setName(desiredName);
				temp.setCost(desiredCost);
			}
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	public boolean inventoryItemIsSupply(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		if (aLab.getInventoryItem(index) instanceof SupplyType) {
			return true;
		} else
			return false;
	}

	public boolean inventoryItemIsEquipment(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		if (aLab.getInventoryItem(index) instanceof Equipment) {
			return true;
		} else
			return false;
	}

	/**
	 * This method will give a summary of the overall URLMS Inventory
	 * 
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {

		// TODO: remove this when working on implementation
		return null;
	}

}
