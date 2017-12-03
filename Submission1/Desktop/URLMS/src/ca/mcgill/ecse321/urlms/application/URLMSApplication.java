package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.MainPage;
//import ca.mcgill.ecse321.urlms.view.NewSaveFilePO;

public class URLMSApplication {

	private static URLMS urlms; // main urlms for the whole application

	private static String filename; // persistence data file name

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URLMSApplication.setFilename("urlms.xml");
		MainPage mp = new MainPage();
		mp.setVisible(true);
		
		//TODO: add this as legitimate JUnits tests
		// //inventory tests=============================
		//
		//
		// InventoryController ic = new InventoryController();
		// ic.addEquipmentItem("Computer", 180.50);
		// System.out.println(urlms.getLab(0).getInventoryItem(0).getName());
		// ic.addSupplyItem("Crayon", 2, 100);
		// System.out.println(urlms.getLab(0).getInventoryItem(1).getName());
		// System.out.println(ic.viewSupplyItemQuantity(0));
		// System.out.println(ic.viewSupplyItemQuantity(1));
		// System.out.println(ic.viewInventoryItemName(0));
		// System.out.println(ic.viewInventoryItemName(1));
		// System.out.println("current size is "
		// +ic.viewInventoryList().size());
		// ic.removeInventoryItem(0);
		// System.out.println("current size is " +ic.viewInventoryList().size()
		// +" after deleting");
		// ic.addSupplyItem("Crayola", 2.00, 80);
		// ic.addEquipmentItem("Oscilloscope", 54.8);
		// System.out.println("current size is "
		// +ic.viewInventoryList().size());
		// System.out.println(ic.viewInventoryItemName(1));
		// System.out.println(ic.viewSupplyItemQuantity(1));
		// System.out.println(ic.viewInventoryItemCost(1));
		// System.out.println(ic.viewInventoryItemName(2));
		// System.out.println(ic.viewSupplyItemQuantity(2));
		// System.out.println(ic.viewInventoryItemCost(2));
		// ic.editInventoryItemDetails(1, "Eraser", 2.1, 100);
		// ic.editInventoryItemDetails(2, "GTX 1080", 2.5, 80);
		// System.out.println(ic.viewInventoryItemName(1));
		// System.out.println(ic.viewSupplyItemQuantity(1));
		// System.out.println(ic.viewInventoryItemCost(1));
		// System.out.println(ic.viewInventoryItemName(2));
		// System.out.println(ic.viewSupplyItemQuantity(2));
		// System.out.println(ic.viewInventoryItemCost(2));
		//
		// System.out.println("Checking if it's supply"+"\n");
		// int InventorySize = ic.viewInventoryList().size();
		// for(int i=0; i<InventorySize; i++) {
		// if(ic.inventoryItemIsSupply(i)) {
		// System.out.println(ic.viewInventoryItemName(i) + " is supply");
		// }
		// else System.out.println(ic.viewInventoryItemName(i) + " is NOT
		// supply");
		// }
		// System.out.println("\n"+ "Checking if it's equipment"+"\n");
		//
		//
		// for(int i=0; i<InventorySize; i++) {
		// if(ic.inventoryItemIsEquipment(i)) {
		// System.out.println(ic.viewInventoryItemName(i) + " is equipment");
		// }
		// else System.out.println(ic.viewInventoryItemName(i) + " is NOT
		// equipment");
		// }

		// staff tests=================================

		// StaffController sc = new StaffController();
		// sc.addStaffMember("Victor",true,true);
		// sc.addStaffMember("Eric",false,true);
		//
		// System.out.println(sc.viewStaffMemberName(0));
		// System.out.println(sc.viewStaffMemberID(0));
		// System.out.println(urlms.getLab(0).getStaffMember(0).getResearchRole(0).toString());
		// System.out.println(sc.viewStaffMemberName(1));
		// System.out.println(sc.viewStaffMemberID(1));
		// System.out.println(urlms.getLab(0).getStaffMember(1).getResearchRole(0).toString());
		// sc.removeStaffMember(0);
		// System.out.println(sc.viewStaffMemberName(0));
		// System.out.println(sc.viewStaffMemberID(0));
		// System.out.println(urlms.getLab(0).getStaffMember(0).getResearchRole(0).toString());
		// sc.addProgress("november 27", "du ma", 0);
		// sc.addProgress("dec 8", "du ma presentation", 0);
		// List<ProgressUpdate> progress = sc.viewProgressUpdate(0);
		// for(int i=0; i<progress.size();i++) {
		// System.out.println(progress.get(i).getDate());
		// System.out.println(progress.get(i).getDescription());
		// }
		// sc.addStaffMember("Feras", true, false);
		// System.out.println(urlms.getLab(0).getStaffMember(1).getId());
		// System.out.println(urlms.getLab(0).getStaffMember(1).getName());
		// sc.addStaffMember("JustinToMessUp", true, false);
		// System.out.println(urlms.getLab(0).getStaffMember(2).getId());
		// System.out.println(urlms.getLab(0).getStaffMember(2).getName());
		//

	}

	/**
	 * This method will get the current urlms. If it is null, it will fetch for
	 * the urlms saved.
	 * 
	 * @return the current urlms
	 */
	public static URLMS getURLMS() {
		if (urlms == null) {
			urlms = load();
		}
		return urlms;
	}

	/**
	 * This method will save the current urlms to the persistence. The data file
	 * will be an XLM file created using XStream.
	 */
	public static void save() {
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	/**
	 * This method will load the urlms stored in the XML data file. If no load
	 * file is found, a new save file will be created.
	 * 
	 * @return loaded urlms
	 */
	public static URLMS load() {

		PersistenceXStream.setFilename(filename);
		URLMS urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();

		// if the file does not exist, create a new save file
		if (urlms == null) {
			urlms = PersistenceXStream.initializeModelManager(filename);
			// NewSaveFilePO nsfpo = new NewSaveFilePO();
			// nsfpo.setVisible(true);
		}
		return urlms;
	}

	/**
	 * This method sets the file name to the desired name
	 * 
	 * @param newFilename
	 *            file name String
	 */
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}
