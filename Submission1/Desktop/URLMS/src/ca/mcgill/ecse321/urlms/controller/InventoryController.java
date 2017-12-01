package ca.mcgill.ecse321.urlms.controller;

import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class InventoryController extends Controller {

	public InventoryController() {
		// TODO Auto-generated constructor stub
	}

	
	//TODO USE CASES IMPLEMENTATION ----------------------------------
	
	/** This method will get the inventory item list 
	 * @return a list of the inventory items
	 */
	public List<InventoryItem> viewInventoryList() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		List<InventoryItem> inventorylist = aLab.getInventoryItems();
		return inventorylist;
	}
	
	
	
	/**This method will add an item to the inventory list
	 * @param name of the item by String
	 * @param cost of the item by double
	 * @param category of item by String
	 */
	public void addInventoryItem(String name, double cost, String category) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.addInventoryItem(name, cost, category);		
	}	
	
	/** This method will remove an item from the inventory list
	 * @param name of the item by String
	 */
	public void removeInventoryItem(String name, int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getInventoryItem(index).delete();
	}
	
	
	
	/**This method will view the details of a specific item
	 * @param name of the item by String
	 * @return the inventory item wanted
	 */
	public InventoryItem viewInventoryItemDetails(String name) {
		
		//TODO: remove this when working on the implementation
		return null;
	}
	
	
	/** This method will edit the details of a specific item inventory
	 * @param name of the the inventory item by String
	 */
	public void editInventoryItemDetails(String name) {
		
	}
	
	
	
	/** This method will give a summary of the overall URLMS Inventory 
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {
		
		//TODO: remove this when working on implementation
		return null;
	}
}
