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
		
		String name = "Feras"; //test name
		
		StaffController urlmsController = new StaffController(); //create instance of controller
		urlmsController.addSampleMembers(); //add some sample members
		
		//check model in memory
		
		//save the file
		urlmsController.save();
		
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents (same checks as above, but with loaded file)
	}

}
