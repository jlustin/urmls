package com.example.team8.urlms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.*;

import android.content.res.XmlResourceParser;
import android.widget.Toast;

import static android.R.attr.name;

//import static com.example.team8.urlms.MainActivity.load;

public class MainActivity extends AppCompatActivity {

    Button refreshButton;
    Button viewStaffButton;
    Button addMember;
    TextView toDisplay;
    TextView appTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons
        refreshButton = (Button) findViewById(R.id.refreshButton);
        viewStaffButton = (Button) findViewById(R.id.viewStaff);
        addMember = (Button) findViewById(R.id.addMember);
        //textviews
        toDisplay = (TextView) findViewById(R.id.toDisplay);
        appTitle = (TextView) findViewById(R.id.appTitle);

        viewStaffMembers();
        refresh();
        addMember(); // not implemented

        //initiate scrolling
        toDisplay.setMovementMethod(new ScrollingMovementMethod());

    }

    //toast
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }


    //button methods

    private void refresh() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDisplay.setText("");
                toastMessage("Page refreshed.");
            }
        });
    }

    public void viewStaffMembers(){
        viewStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                XmlResourceParser parser = getResources().getXml(R.xml.stafflist);
                Controller controller = new Controller(parser);

                List<StaffMember> staffList = controller.viewStaffList();
                String name;
                int id;
                String output = "";
                for(StaffMember aMember: staffList){
                    output += aMember.getId() +" " +aMember.getName() +"\n";
                }
                toDisplay.setText(output);


                toastMessage("All members displayed.");

            }
        });
    }

    private void addMember() {
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("Member added.");
            }
        });
    }

}
