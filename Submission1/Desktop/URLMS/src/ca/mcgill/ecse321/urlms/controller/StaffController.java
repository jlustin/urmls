package ca.mcgill.ecse321.urlms.controller;

import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.ResearchAssistant;
import ca.mcgill.ecse321.urlms.model.ResearchAssociate;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class StaffController extends Controller {

	/**
	 * Add Progress Update
	 * 
	 * @param desired date by string and desired description by string
	 */
	public void addProgress(String date, String description, int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = aLab.getStaffMember(index);
		aStaffMember.addProgressUpdate(date, description);
	}

	public void addProgressByID(String date, String description, int id) throws InvalidInputException {
		String error = "";
		if (description.isEmpty()) {
			error += "Might want to enter a description. ";
		}
		if (date.isEmpty()) {
			error += "Might want to enter a date. ";
		}

		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = null;

		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}
		if (aStaffMember == null){
			throw new InvalidInputException("Bad ID; staff member was not found! ");
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		aStaffMember.addProgressUpdate(date, description);
	}

	// helper method for adding roles specificied by checkbox / radio button
	//TODO: simplify this code...
	public void addRoles(StaffMember aStaffMember, boolean isAssistant, boolean isAssociate) {
		if (aStaffMember.hasResearchRoles()) {
			aStaffMember.getResearchRole(0).delete();
		}
		if (aStaffMember.hasResearchRoles()) {
			aStaffMember.getResearchRole(0).delete();
		}
		// add desired roles
		if (isAssistant && isAssociate) {
			aStaffMember.addResearchRole(new ResearchAssistant("Why you read this", aStaffMember));
			aStaffMember.addResearchRole(new ResearchAssociate("Why you read this", aStaffMember));
		} else if (isAssistant && !isAssociate) {
			aStaffMember.addResearchRole(new ResearchAssistant("Why you read this", aStaffMember));
		} else if (isAssociate && !isAssistant) {
			aStaffMember.addResearchRole(new ResearchAssociate("Why you read this", aStaffMember));
		} else {
			// do nothing
		}
	}

	/**
	 * This method will add a staff member in the lab
	 * 
	 * @param name of the staff member by String and staff Roles defined by
	 *            booleans for checkedbox/radio buttons box1==staff assistant
	 *            box2==staff associate
	 * @throws InvalidInputException 
	 */
	public void addStaffMember(String name, boolean isAssistant, boolean isAssociate, double weeklySalary) throws InvalidInputException {
		String error = "";
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		int randomNumber;
		StaffMember newStaffMember;
		
		// "auto-unique"
		randomNumber = (int) (Math.random() * 1000 + 1);

		//error checking
		if (name.isEmpty()) {
			error += "Please enter a name. ";
		}
		if (weeklySalary < 0) {
			error += "Please enter a valid salary. Or try again with celery. ";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		try {
			newStaffMember = aLab.addStaffMember(name, randomNumber, weeklySalary);
			addRoles(newStaffMember, isAssistant, isAssociate);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		

	}

	/**
	 * Edit staff name
	 * 
	 * @param desired name change by string & id of the staff member by int
	 */
	public void editStaffmemberName(String desiredName, int id) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getStaffMember(id).setName(desiredName);
	}

	/**
	 * This method will edit the record of a specific staff member by ID
	 * 
	 * @param id of the staff member by int box1 and box2 are radio buttons
	 * selected by user box1 == Research Assistant box2 == Research Associate
	 */
	public void editStaffmemberRecord(int index, int id, String desiredName, boolean isAssistant, boolean isAssociate,
			double weeklySalary) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = aLab.getStaffMember(index);
		aStaffMember.setName(desiredName);
		aStaffMember.setId(id);
		aStaffMember.setWeeklySalary(weeklySalary);
		addRoles(aStaffMember, isAssistant, isAssociate);
	}

	public void editStaffmemberRecordByID(int id, int newID, String desiredName, boolean isAssistant,
			boolean isAssociate, double weeklySalary) throws InvalidInputException {
		String error = "";
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		
		StaffMember aStaffMember = null;

		

		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}
		//error checking
		// if there are currently no staff members in the lab
		if(aStaffMember == null){
			error += "There is no staff matching the entered target ID. ";
		}
		if (desiredName.isEmpty()) {
			error += "Please enter a name. ";
		}
		if (weeklySalary < 0) {
			error += "Please enter a valid salary. ";
		}
		if (id < 0 || newID < 0) {
			error += "Please enter a valid ID. ";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		
		try {
			aStaffMember.setName(desiredName);
			aStaffMember.setId(newID);
			aStaffMember.setWeeklySalary(weeklySalary);
			addRoles(aStaffMember, isAssistant, isAssociate);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public StaffMember getStaffMemberByID(int id) throws InvalidInputException {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		StaffMember aStaffMember = null;

		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				aStaffMember = iteratedStaffMember;
			}
		}
		
		if (aStaffMember == null){
			throw new InvalidInputException("Bad ID; staff member was not found! ");
		}
		return aStaffMember;
	}

	/**
	 * This method will remove a staff member in the lab by id
	 * 
	 * @param id of the staff member by int
	 */
	public void removeStaffMember(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		aLab.getStaffMember(index).delete();

	}

	//TODO: this generates ConcurrentModificationException
	public void removeStaffMemberByID(int id) throws InvalidInputException {
		boolean wasRemoved = false;
		
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);

		StaffMember m = null;

		for (StaffMember iteratedStaffMember : aLab.getStaffMembers()) {
			if (id == iteratedStaffMember.getId()) {
				m = iteratedStaffMember;
			}
		}
		if(m != null) {
			m.delete();
			wasRemoved = true;
		}
		if(!wasRemoved){
			throw new InvalidInputException("Staff Member was not deleted :( ");
		}
	}

	public List<ProgressUpdate> viewProgressUpdateByID(int id) throws InvalidInputException {

		StaffMember currentStaffMember = getStaffMemberByID(id);
		String error = "";
		List<ProgressUpdate> progress;

		try {
			progress = currentStaffMember.getProgressUpdates();
			if (progress.isEmpty()) {
				error += "There are no progress updates to display :(";
			}
			if (error.length() > 0) {
				throw new InvalidInputException(error.trim());
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}



		return progress;
	}

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
			if (staffList.isEmpty()) {
				error = "There are no staff members to display :(";
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		return staffList;
	}

	/**
	 * This method will get the name of a specific staff member in the list and
	 * return its name as a string.
	 * 
	 * @param int index of the staff member in the list
	 * @return name of the staff member as a String
	 */
	public String viewStaffMember(int index) {
		return URLMSApplication.getURLMS().getLab(0).getStaffMembers().get(index).getName();
	}

	public String viewStaffMemberID(int index) {
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		return String.valueOf(aLab.getStaffMember(index).getId());
	}
}
