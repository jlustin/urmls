package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class InventoryControllerTest {

	private static URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("urlms.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMSApplication.getURLMS();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//TODO Add in appropriate JUnit tests
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

	@Test
	public void testInventoryController() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewInventoryList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInventoryItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEquipmentItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSupplyItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveInventoryItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewInventoryItemName() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewInventoryItemCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewSupplyItemQuantity() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewInventoryItemDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditInventoryItemDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testInventoryItemIsSupply() {
		fail("Not yet implemented");
	}

	@Test
	public void testInventoryItemIsEquipment() {
		fail("Not yet implemented");
	}

}
