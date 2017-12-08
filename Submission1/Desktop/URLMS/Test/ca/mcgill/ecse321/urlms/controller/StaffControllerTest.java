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
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class StaffControllerTest {
	
	private static URLMS urlms;
	private static StaffController controller;
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
		controller = new StaffController();
		aLab = urlms.getLab(0);
		
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
		File file = new File("urlmsTest.xml");
		file.delete();
	}
	
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

	@Test
	public void testViewStaffList() {
		
		//check if the staff manager is empty
		assertEquals(0, aLab.getStaffMembers().size());
		
		String name = "Feras"; //test name
		
		StaffController urlmsController = new StaffController(); //create instance of controller
		addSampleMembers(aLab); //add some sample members
		
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




	@Test
	public void testViewStaffMember() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		String testString = controller.viewStaffMember(0);
		
		assertEquals("Feras", testString);
	}

	@Test
	public void testEditStaffmemberRecord() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.editStaffmemberRecord(0, 123, "Victor", false, true, 100);
		assertEquals("Victor", aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssociate", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		assertEquals(123, aLab.getStaffMember(0).getId());
	}

	@Test
	public void testEditStaffmemberName() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.editStaffmemberName("Test", 0);
		assertEquals("Test", aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
	}

	@Test
	public void testViewProgressUpdate() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.addProgress("11/12/17", "Can you view this?", 0);
		assertEquals("11/12/17", aLab.getStaffMember(0).getProgressUpdate(0).getDate());
		assertEquals("Can you view this?", aLab.getStaffMember(0).getProgressUpdate(0).getDescription());
	}

	@Test
	public void testViewProgressUpdateByID() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		try {
			controller.addProgressByID("11/12/17", "Can you view this?", aLab.getStaffMember(0).getId());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("11/12/17", aLab.getStaffMember(0).getProgressUpdate(0).getDate());
		assertEquals("Can you view this?", aLab.getStaffMember(0).getProgressUpdate(0).getDescription());
	}

	@Test
	public void testAddProgress() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.addProgress("1/2/3", "Testing", 0);
		
		assertEquals("1/2/3", aLab.getStaffMember(0).getProgressUpdate(0).getDate());
		assertEquals("Testing", aLab.getStaffMember(0).getProgressUpdate(0).getDescription());
		assertEquals(1, aLab.getStaffMember(0).getProgressUpdates().size());
	}


	@Test
	public void testViewStaffMemberName() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		
	}

	@Test
	public void testViewStaffMemberID() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		String expectedID = String.valueOf(aLab.getStaffMember(0).getId());
		String testID = controller.viewStaffMemberID(0);
		
		assertEquals(expectedID, testID);
	}

	//successful test for addStaffMember
	@Test
	public void testAddStaffMember() {
		
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
	}

	@Test
	public void testRemoveStaffMember() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.removeStaffMember(0);

		assertEquals(0, aLab.getStaffMembers().size());

	}

	@Test
	public void testEditStaffmemberRecordByID() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		controller.editStaffmemberRecord(0, 123, "Victor", false, true, 100);
		assertEquals("Victor", aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssociate", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		assertEquals(123, aLab.getStaffMember(0).getId());

	}

	@Test
	public void testRemoveStaffMemberByID() {
		String err = "";
		String name = "Feras";
		int id = 0;
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());

		controller.removeStaffMember(id);

		assertEquals(0, aLab.getStaffMembers().size());
	}

	@Test
	public void testAddProgressByID() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
		try {
			controller.addProgressByID("1/2/3", "Add test by ID", aLab.getStaffMember(0).getId());
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		assertEquals("1/2/3", aLab.getStaffMember(0).getProgressUpdate(0).getDate());
		assertEquals("Add test by ID", aLab.getStaffMember(0).getProgressUpdate(0).getDescription());
	}

	@Test
	public void testGetStaffMemberByID() {
		String err = "";
		String name = "Feras";
		try {
			controller.addStaffMember(name, true, false, 111);
		} catch (InvalidInputException e) {
			err = e.getMessage();
		}
		
		assertEquals("", err);
		assertEquals(name, aLab.getStaffMember(0).getName());
		assertEquals("ResearchAssistant", aLab.getStaffMember(0).getResearchRole(0).getClass().getSimpleName());
		assertEquals(1, aLab.getStaffMembers().size());
		
	}

	
		/**
		 * This method is used for testing purposes. Three members with names
		 * Victor, Feras and Jun2Yu will be added to the current staff member list.
		 * These three members will have different IDs: 123, 111 and 222
		 * respectively.
		 */
		public void addSampleMembers(Lab aLab) {
			StaffMember member = new StaffMember("Victor", 123, 123.2, aLab);
			aLab.addStaffMember(member);

			StaffMember member2 = new StaffMember("Feras", 111, 3232, aLab);
			aLab.addStaffMember(member2);

			StaffMember member3 = new StaffMember("Jun2Yu", 222, 323, aLab);
			aLab.addStaffMember(member3);
		}
	
}
