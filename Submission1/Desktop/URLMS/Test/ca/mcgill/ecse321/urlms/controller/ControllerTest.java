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
		urlms.delete();
	}

	@Test
	public void testViewStaffList() {
		//fail("Not yet implemented");
		StaffManager aStaffManager = urlms.getStaffManager();
		
		assertEquals(0, aStaffManager.getStaffMembers().size());
		
		String name = "Feras";
		
		Controller urlmsController = new Controller();
		urlmsController.addSampleMembers();
		
		//check model in memory
		assertEquals(3, aStaffManager.getStaffMembers().size());
		assertEquals(name, aStaffManager.getStaffMember(1).getName());
		assertEquals(0, (long)urlms.getFundingManager().getTotalBalance());
		assertEquals(false, (urlms.getInventoryManager().hasInventoryItems()));
		
		urlmsController.save();
		//load stuff(?)
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		assertEquals(3, aStaffManager.getStaffMembers().size());
		assertEquals(name, aStaffManager.getStaffMember(1).getName());
		assertEquals(0, (long)urlms.getFundingManager().getTotalBalance());
		assertEquals(false, (urlms.getInventoryManager().hasInventoryItems()));
	}

}
