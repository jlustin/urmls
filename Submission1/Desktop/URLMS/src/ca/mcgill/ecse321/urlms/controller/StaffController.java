package ca.mcgill.ecse321.urlms.controller;

import java.util.*;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;


public class StaffController extends Controller {
	
	/**
	 * This method will get the staff member list of the current urlms in order to view the members
	 * @return the staffList from urlms
	 */
	public List<StaffMember> viewStaffList(){
		URLMS urlms = URLMSApplication.getURLMS();
		Lab aLab = urlms.getLab(0);
		Staff aStaff = aLab.getStaff();
		
		List<StaffMember> staffList = aStaff.getStaffMembers();
		
		return staffList;
	}
	
	//TODO move to JUnit Test
	/**
	 * This method is used for testing purposes. Three members with names Victor, Feras and Jun2Yu will be
	 * added to the current staff member list. These three members will have different IDs: 123, 111 and 222
	 * respectively.
	 */
	public void addSampleMembers() {
		URLMS urlms = URLMSApplication.getURLMS();
		Staff aStaff = urlms.getLab(0).getStaff();
		StaffMember member = new StaffMember("Victor", 123, aStaff);
		aStaff.addStaffMember(member);
		
		StaffMember member2 = new StaffMember("Feras", 111, aStaff);
		aStaff.addStaffMember(member2);
		
		StaffMember member3 = new StaffMember("Jun2Yu", 222, aStaff);
		aStaff.addStaffMember(member3);
	}
	
	
	/**
	 * This method will get the name of a specific staff member in the list and return its name as a string.
	 * @param int index of the staff member in the list
	 * @return name of the staff member as a String
	 */
	public String viewStaffMember(int index){
		return URLMSApplication.getURLMS().getLab(0).getStaff().getStaffMembers().get(index).getName();
	}
	
	//TODO: USE CASES IMPLEMENTATION -----------------------------------------------
	
	
	/** This method will edit the record of a specific staff member by ID
	 * @param id of the staff member by int
	 */
	public void editStaffmemberRecord(int id) {
		
	}
	
	
	/** This method will get the progress update of a specific staff member by ID number
	 * @param id of the staff member by int
	 * @return the progress update of the specific member
	 */
	public ProgressUpdate viewProgressUpdate(int id) {
		
		//TODO: remove this when working on implementation
		return null;
	}
	
	
	/** This method will get the records of a specific staff member by ID number
	 * @param id of the staff member by int
	 */
	public void viewStaffMemberRecord(int id) {
		
	}
	
	
	/** This method will add a staff member in the lab
	 * @param name of the staff member by String
	 * @param id of the staff member by int
	 */
	public void addStaffMember(String name, int id) {
		
	}
	
	
	
	/** This method will remove a staff member in the lab by id
	 * @param id of the staff member by int
	 */
	public void removeStaffMember(int id) {
		
	}
	
	
	
	/** This method will give a summary of the overall URLMS staff
	 * @return a hash map containing all information
	 */
	public HashMap<String, String> viewStatus() {
		
		//TODO: remove this when working on implementation
		return null;
	}

}
