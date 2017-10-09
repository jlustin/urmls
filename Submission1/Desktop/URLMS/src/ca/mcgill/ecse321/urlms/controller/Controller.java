package ca.mcgill.ecse321.urlms.controller;

import java.util.*;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class Controller {
	
	public List<StaffMember> viewStaffList(){
		URLMS urlms = URLMSApplication.getURLMS();
		StaffManager staffManager = urlms.getStaffManager();
		List<StaffMember> staffList = staffManager.getStaffMembers();
		
		return staffList;
		
	}
	
}
