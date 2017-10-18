package ca.mcgill.ecse321.urlms.controller;

import java.util.*;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class Controller {
	
	/**
	 * This method will get the staff member list of the current urlms in order to view the members
	 * @return the staffList from urlms
	 */
	public List<StaffMember> viewStaffList(){
		URLMS urlms = URLMSApplication.getURLMS();
		StaffManager staffManager = urlms.getStaffManager();
		List<StaffMember> staffList = staffManager.getStaffMembers();
		
		return staffList;
	}
	
	/**
	 * This method is used for testing purposes. Three members with names Victor, Feras and Jun2Yu will be
	 * added to the current staff member list. These three members will have different IDs: 123, 111 and 222
	 * respectively.
	 */
	public void addSampleMembers() {
		URLMS urlms = URLMSApplication.getURLMS();
		StaffManager staffManager = urlms.getStaffManager();
		StaffMember member = new StaffMember("Victor", 123, staffManager);
		staffManager.addStaffMember(member);

		StaffMember member2 = new StaffMember("Feras", 111, staffManager);
		staffManager.addStaffMember(member2);
		
		StaffMember member3 = new StaffMember("Jun2Yu", 222, staffManager);
		staffManager.addStaffMember(member3);
	}
	
	/**
	 * This method calls the save method from the applicationg to save the current urlms to the persistence.
	 */
	public void save() {
		URLMSApplication.save();
	}
	
	/**
	 * This method will get the name of a specific staff member in the list and return its name as a string.
	 * @param index of the staff member in the list
	 * @return name of the staff member as a String
	 */
	public String viewStaffMember(int index){
		return URLMSApplication.getURLMS().getStaffManager().getStaffMembers().get(index).getName();
	}
	

}
