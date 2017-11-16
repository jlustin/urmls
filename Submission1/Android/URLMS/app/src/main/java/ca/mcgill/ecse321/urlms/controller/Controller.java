package ca.mcgill.ecse321.urlms.controller;

import android.content.res.XmlResourceParser;
import android.database.Cursor;

import com.example.team8.urlms.R;

import java.util.*;
import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.DatabaseHelper;
import ca.mcgill.ecse321.urlms.persistence.Persistence;

import static ca.mcgill.ecse321.urlms.persistence.DatabaseHelper.urlms;

public class Controller {
    private String fileName;
    URLMS urlms = new URLMS(0);
    Cursor cursor;

//    public Controller(XmlResourceParser parser){
//        urlms = Persistence.load(parser);
//    }
    public Controller(){
//        urlms = new URLMS(0);
//        StaffManager sm = urlms.getStaffManager();
//        while(cursor.moveToNext()){
//            sm.addStaffMember(cursor.getString(1),Integer.parseInt(cursor.getString(1)));
//        }
    }

//
//    public List<StaffMember> viewStaffList(){
//        StaffManager staffManager = urlms.getStaffManager();
//        List<StaffMember> staffList = staffManager.getStaffMembers();
//        return staffList;
//
//    }
//
//    public void addMember() {
//        URLMS urlms = URLMSApplication.getURLMS();
//        StaffManager staffManager = urlms.getStaffManager();
//        StaffMember member = new StaffMember("Victor", 123, staffManager);
//        staffManager.addStaffMember(member);
//
//        StaffMember member2 = new StaffMember("Feras", 111, staffManager);
//        staffManager.addStaffMember(member2);
//
//        StaffMember member3 = new StaffMember("Jun2Yu", 222, staffManager);
//        staffManager.addStaffMember(member3);
//    }
    public List<StaffMember> viewMembers(Cursor cursor){
        StaffManager sm = this.urlms.getStaffManager();
        while(cursor.moveToNext()){
            if(cursor.getString(0)!=null){
            sm.addStaffMember(cursor.getString(1),Integer.parseInt(cursor.getString(0)));
        }}
        return sm.getStaffMembers();
    }

}
