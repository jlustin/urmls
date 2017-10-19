package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class ControllerTest {
	
	private static URLMS urlms;

	/**
	 * Set up once before: Load the file
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("urlms.xml");
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	/**
	 * Set up the urlms using the Application class
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		urlms = URLMSApplication.getURLMS();
	}

	/**
	 * Deletes the urlms
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void testViewStaffList() {
		//fail("Not yet implemented");
		StaffManager aStaffManager = urlms.getStaffManager();
		
		//check if the staff manager is empty
		assertEquals(0, aStaffManager.getStaffMembers().size());
		
		String name = "Feras"; //test name
		
		Controller urlmsController = new Controller(); //create instance of controller
		urlmsController.addSampleMembers(); //add some sample members
		
		//check model in memory
		assertEquals(3, aStaffManager.getStaffMembers().size()); //checks if the staff manager now contains 3 members
		assertEquals(name, aStaffManager.getStaffMember(1).getName()); //checks if the 2nd member in the list is "Feras"
		assertEquals(0, (long)urlms.getFundingManager().getTotalBalance()); //check if the balance in the funds is zero
		assertEquals(false, (urlms.getInventoryManager().hasInventoryItems())); //check if the inventory is empty
		
		//save the file
		urlmsController.save();
		
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents (same checks as above, but with loaded file)
		assertEquals(3, aStaffManager.getStaffMembers().size());
		assertEquals(name, aStaffManager.getStaffMember(1).getName());
		assertEquals(0, (long)urlms.getFundingManager().getTotalBalance());
		assertEquals(false, (urlms.getInventoryManager().hasInventoryItems()));
	}

}
