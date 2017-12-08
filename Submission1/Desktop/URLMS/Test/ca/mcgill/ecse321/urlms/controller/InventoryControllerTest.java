package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class InventoryControllerTest {

	private static URLMS urlms;
	private static InventoryController controller;
	private static Lab aLab;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("urlmsTest.xml");
		URLMSApplication.setFilename("urlmsTest.xml");
		PersistenceXStream.initializeModelManager("urlmsTest.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMSApplication.load();
		URLMSApplication.setURLMS(urlms);
		controller = new InventoryController();
		aLab = urlms.getLab(0);
		
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
		File file = new File("urlmsTest.xml");
		file.delete();
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
	public void testViewInventoryList() {
		String err = "";
		String name = "EV3";
		String category = "scrap hardware";
		double cost = 123;
		int quantity = 3;
		List<InventoryItem> testList = null;
		try {
			controller.addSupplyItem(name, category, cost, quantity);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		try {
			List<InventoryItem> items = controller.viewInventoryList();
			testList = items;
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
		assertEquals("EV3", aLab.getInventoryItem(0).getName());
		assertEquals("scrap hardware", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, testList.size());

	}

	@Test
	public void testAddInventoryItem() {
		String err = "";
		String name = "test add";
		String category = "item inventory";
		double cost = 123;
		int quantity = 3;
		List<InventoryItem> testList = null;
		try {
			controller.addSupplyItem(name, category, cost, quantity);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		try {
			List<InventoryItem> items = controller.viewInventoryList();
			testList = items;
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("item inventory", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, testList.size());
	}

	@Test
	public void testAddEquipmentItem() {
		String err = "";
		String name = "test add";
		String category = "equip";
		double cost = 123;
		try {
			controller.addEquipmentItem(name, category, cost);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
	
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("equip", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
	}

	@Test
	public void testAddSupplyItem() {
		String err = "";
		String name = "test add";
		String category = "SUPPLY";
		int quantity = 19;
		double cost = 123;
		try {
			controller.addSupplyItem(name, category, cost, quantity);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
	
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("SUPPLY", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
	}

	@Test
	public void testRemoveInventoryItem() {
		String err = "";
		String name = "test add";
		String category = "equip";
		double cost = 123;
		try {
			controller.addEquipmentItem(name, category, cost);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("equip", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		controller.removeInventoryItem(0);
		assertEquals(0, aLab.getInventoryItems().size());
	}

	@Test
	public void testViewInventoryItemName() {
		String err = "";
		String name = "test add";
		String category = "equip";
		double cost = 123;
		try {
			controller.addEquipmentItem(name, category, cost);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("equip", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		String testString = controller.viewInventoryItemName(0);
		
		assertEquals("test add", testString);
	}

	@Test
	public void testViewInventoryItemCost() {
		String err = "";
		String name = "test add";
		String category = "equip";
		double cost = 123;
		try {
			controller.addEquipmentItem(name, category, cost);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("equip", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		String testCost = controller.viewInventoryItemCost(0);

		assertEquals("123.0", testCost);
	}

	@Test
	public void testViewSupplyItemQuantity() {
		String err = "";
		String name = "test add";
		String category = "SUPPLY";
		int quantity = 19;
		double cost = 123;
		try {
			controller.addSupplyItem(name, category, cost, quantity);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
	
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("SUPPLY", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		String testQuantity = controller.viewSupplyItemQuantity(0);
		
		assertEquals("19", testQuantity);
	}

	@Test
	public void testEditInventoryItemDetails() {
		String err = "";
		String name = "test add";
		String category = "SUPPLY";
		int quantity = 19;
		double cost = 123;
		try {
			controller.addSupplyItem(name, category, cost, quantity);
		} catch (InvalidInputException e) {
			err = e.getMessage();
		}
	
		assertEquals("", err);
		assertEquals("test add", aLab.getInventoryItem(0).getName());
		assertEquals("SUPPLY", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		try {
			controller.editInventoryItemDetails("test add", "new test name", "test category", 111, 2);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		assertEquals("new test name", aLab.getInventoryItem(0).getName());
		assertEquals("test category", aLab.getInventoryItem(0).getCategory());
		assertEquals(1, aLab.getInventoryItems().size());
		
		
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
