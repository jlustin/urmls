package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

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
		//urlms.delete();
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
		//fail("Not yet implemented");
		Lab aLab = urlms.getLab(0);
		
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
		
		//TODO delete urlms here cuz the rest of the test is useless otherwise
		
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents (same checks as above, but with loaded file)
		assertEquals(3, aLab.getStaffMembers().size());
		assertEquals(name, aLab.getStaffMember(1).getName());
	}

	@Test
	public void testAddSampleMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewStaffMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditStaffmemberRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditStaffmemberName() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewProgressUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewProgressUpdateByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProgress() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewStaffMemberRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewStaffMemberName() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewStaffMemberID() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStaffMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveStaffMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRoles() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditStaffmemberRecordByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveStaffMemberByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProgressByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStaffMemberByID() {
		fail("Not yet implemented");
	}

	// TODO move to JUnit Test
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
