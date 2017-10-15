package ca.mcgill.ecse321.urlms.controller;

import android.content.res.XmlResourceParser;

import com.example.team8.urlms.R;

import java.util.*;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.Persistence;

public class Controller {

    URLMS urlms;
    public Controller(XmlResourceParser parser){
        urlms = Persistence.load(parser);
    }

    public List<StaffMember> viewStaffList(){
        StaffManager staffManager = urlms.getStaffManager();
        List<StaffMember> staffList = staffManager.getStaffMembers();
        return staffList;

    }

    public void addMember() {
        URLMS urlms = URLMSApplication.getURLMS();
        StaffManager staffManager = urlms.getStaffManager();
        StaffMember member = new StaffMember("Victor", 123, staffManager);
        staffManager.addStaffMember(member);

        StaffMember member2 = new StaffMember("Feras", 111, staffManager);
        staffManager.addStaffMember(member2);

        StaffMember member3 = new StaffMember("Jun2Yu", 222, staffManager);
        staffManager.addStaffMember(member3);
    }

}