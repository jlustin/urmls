package ca.mcgill.ecse321.urlms.controller;

import java.util.*;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;

import ca.mcgill.ecse321.urlms.model.ResearchAssistant;
import ca.mcgill.ecse321.urlms.model.ResearchAssociate;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class StaffController extends Controller {

	/**
	 * This method will get the staff member list of the current urlms in order
	 * to view the members
	 * 
	 * @return the staffList from urlms
	 * @throws InvalidInputException 
	 */
	public List<StaffMember> viewStaffList() throws InvalidInputException {
		String error = "";
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		List<StaffMember> staffList;
			

		try {
			staffList = aLab.getStaffMembers();
			if(staffList.isEmpty()){
				error = "There are no staff members to display :(";
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException (e.getMessage());
		}

		if (error.length() > 0){
			throw new InvalidInputException (error.trim());
		}
		return staffList;
	}

	// TODO move to JUnit Test
	/**
	 * This method is used for testing purposes. Three members with names
	 * Victor, Feras and Jun2Yu will be added to the current staff member list.
	 * These three members will have different IDs: 123, 111 and 222
	 * respectively.
	 */
	public void addSampleMembers() {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember member = new StaffMember("Victor", 123, 123.2, aLab);
		aLab.addStaffMember(member);

		StaffMember member2 = new StaffMember("Feras", 111, 3232, aLab);
		aLab.addStaffMember(member2);

		StaffMember member3 = new StaffMember("Jun2Yu", 222, 323, aLab);
		aLab.addStaffMember(member3);
	}

	/**
	 * This method will get the name of a specific staff member in the list and
	 * return its name as a string.
	 * 
	 * @param int
	 *            index of the staff member in the list
	 * @return name of the staff member as a String
	 */
	public String viewStaffMember(int index) {
		return URLMSApplication.getURLMS().getLab(0).getStaffMembers().get(index).getName();
	}

	// TODO: USE CASES IMPLEMENTATION
	// -----------------------------------------------

	/**
	 * This method will edit the record of a specific staff member by ID
	 * 
	 * @param id
	 *            of the staff member by int box1 and box2 are radio buttons
	 *            selected by user box1 == Research Assistant box2 == Research
	 *            Associate
	 */
	public void editStaffmemberRecord(int index, int id, String desiredName, boolean box1, boolean box2,
			double weeklySalary) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = aLab.getStaffMember(index);
		aStaffMember.setName(desiredName);
		aStaffMember.setId(id);
		aStaffMember.setWeeklySalary(weeklySalary);
		addRoles(aStaffMember, box1, box2);

	}

	/**
	 * Edit staff name
	 * 
	 * @param desired
	 *            name change by string & id of the staff member by int
	 */
	public void editStaffmemberName(String desiredName, int id) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getStaffMember(id).setName(desiredName);
	}

	/**
	 * This method will get the progress update of a specific staff member by ID
	 * number
	 * 
	 * @param id
	 *            of the staff member by int
	 * @return the progress update of the specific member
	 */
	public List<ProgressUpdate> viewProgressUpdate(int index) {

		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		List<ProgressUpdate> progress = aLab.getStaffMember(index).getProgressUpdates();
		return progress;
	}
	
	public List<ProgressUpdate> viewProgressUpdateByID(int id) {

		StaffMember currentStaffMember = getStaffMemberByID(id);

		List<ProgressUpdate> progress = currentStaffMember.getProgressUpdates();
		return progress;
	}

	/**
	 * Add Progress Update
	 * 
	 * @param desired
	 *            date by string and desired description by string
	 */
	public void addProgress(String date, String description, int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = aLab.getStaffMember(index);
		aStaffMember.addProgressUpdate(date, description);
	}

	/**
	 * This method will get the records of a specific staff member by ID number
	 * 
	 * @param id
	 *            of the staff member by int
	 */
	public void viewStaffMemberRecord(int id) {
		// will not be used
	}

	public String viewStaffMemberName(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return aLab.getStaffMember(index).getName();
	}

	public String viewStaffMemberID(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return String.valueOf(aLab.getStaffMember(index).getId());
	}

	/**
	 * This method will add a staff member in the lab
	 * 
	 * @param name
	 *            of the staff member by String and staff Roles defined by
	 *            booleans for checkedbox/radio buttons box1==staff assistant
	 *            box2==staff associate
	 */
	public void addStaffMember(String name, boolean box1, boolean box2, double weeklySalary) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		// "auto-unique"
		int rng = (int) (Math.random() * 1000 + 1);
		aLab.addStaffMember(name, rng, weeklySalary);
		addRoles(aLab.getStaffMember(aLab.getStaffMembers().size() - 1), box1, box2);

	}

	/**
	 * This method will remove a staff member in the lab by id
	 * 
	 * @param id
	 *            of the staff member by int
	 */
	public void removeStaffMember(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getStaffMember(index).delete();

	}

	/**
	 * This method will give a summary of the overall URLMS staff
	 * 
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {

		// TODO: remove this when working on implementation
		return null;
	}

	// helper method for adding roles specificied by checkbox / radio button
	public void addRoles(StaffMember aStaffMember, boolean box1, boolean box2) {
		if (aStaffMember.hasResearchRoles()) {
			aStaffMember.getResearchRole(0).delete();
		}
		if (aStaffMember.hasResearchRoles()) {
			aStaffMember.getResearchRole(0).delete();
		}
		// add desired roles
		if (box1 && box2) {
			aStaffMember.addResearchRole(new ResearchAssistant("Why you read this", aStaffMember));
			aStaffMember.addResearchRole(new ResearchAssociate("Why you read this", aStaffMember));
		} else if (box1 && !box2) {
			aStaffMember.addResearchRole(new ResearchAssistant("Why you read this", aStaffMember));
		} else if (box2 && !box1) {
			aStaffMember.addResearchRole(new ResearchAssociate("Why you read this", aStaffMember));
		} else {
			// do nothing
		}
	}

	public void editStaffmemberRecordByID(int id, int newID, String desiredName, boolean box1, boolean box2,
			double weeklySalary) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = null;

		//if there are currently no staff members in the lab


		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}

		aStaffMember.setName(desiredName);
		aStaffMember.setId(newID);
		aStaffMember.setWeeklySalary(weeklySalary);
		addRoles(aStaffMember, box1, box2);

	}

	public void removeStaffMemberByID(int id) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);


		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				iteratedStaffMember.delete();
			}
		}

	}
	
	public void addProgressByID(String date, String description, int id) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = null;
		
		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}
		aStaffMember.addProgressUpdate(date, description);
	}

	
	public StaffMember getStaffMemberByID(int id) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = null;
		
		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}
		return aStaffMember;
	}

}
