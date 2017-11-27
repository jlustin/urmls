package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Lab;
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
		//urlms.delete();
	}

	@Test
	public void testViewStaffList() {
		//fail("Not yet implemented");
		Lab aLab = urlms.getLab(0);
		
		//check if the staff manager is empty
		assertEquals(0, aLab.getStaffMembers().size());
		
		String name = "Feras"; //test name
		
		StaffController urlmsController = new StaffController(); //create instance of controller
		urlmsController.addSampleMembers(); //add some sample members
		
		//check model in memory
		assertEquals(3, aLab.getStaffMembers().size()); //checks if the staff manager now contains 3 members
		assertEquals(name, aLab.getStaffMember(1).getName()); //checks if the 2nd member in the list is "Feras"
		
		//save the file
		urlmsController.save();
		
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents (same checks as above, but with loaded file)
		assertEquals(3, aLab.getStaffMembers().size());
		assertEquals(name, aLab.getStaffMember(1).getName());
	}

}
