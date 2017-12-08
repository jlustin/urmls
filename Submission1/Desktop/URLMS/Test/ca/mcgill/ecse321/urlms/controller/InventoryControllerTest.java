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
