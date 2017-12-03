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
	 */
	public List<InventoryItem> viewInventoryList() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);

		List<InventoryItem> inventorylist = aLab.getInventoryItems();
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
	 */
	public void addInventoryItem(String name, double cost) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.addInventoryItem(name, cost, null);
	}

	/**
	 * This method will add an equipment to the inventory list
	 * 
	 * @param aName
	 *            of the item by String
	 * @param cost
	 *            of the item by double
	 */
	public void addEquipmentItem(String aName, double cost) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		Equipment temp = new Equipment(aName, cost, "Equipment", aLab, false);
		aLab.addInventoryItem(temp);
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
	 */
	public void addSupplyItem(String aName, double cost, int quantity) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		SupplyType temp = new SupplyType(aName, cost, "Supply", aLab, quantity);
		aLab.addInventoryItem(temp);
	}

	/**
	 * This method will remove an item from the inventory list
	 * 
	 * @param index
	 *            of Inventory Item by int
	 */
	public void removeInventoryItem(int index) {
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
			return "This item is of type Equipment, so there's no quantity specified.";
	}

	/**
	 * This method will view the details of a specific item
	 * 
	 * @param name
	 *            of the item by String
	 * @return the inventory item wanted
	 */
	public InventoryItem viewInventoryItemDetails(String name) {

		// NOT USED
		return null;
	}

	/**
	 * This method will edit the details of a specific item inventory
	 * 
	 * @param name
	 *            of the the inventory item by String
	 */
	public void editInventoryItemDetails(int index, String desiredName, double desiredCost, int desiredQuantity) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
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
