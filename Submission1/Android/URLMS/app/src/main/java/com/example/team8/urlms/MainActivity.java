package com.example.team8.urlms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.persistence.*;

import android.content.res.XmlResourceParser;
import android.widget.Toast;

//import static com.example.team8.urlms.MainActivity.load;

public class MainActivity extends AppCompatActivity {



    Button refreshButton;
    Button viewStaffButton;
    Button addMember;
    Button updateMember;
    Button deleteMember;
    Button deleteAll;
    TextView toDisplay;
    TextView appTitle;
    EditText editName;
    EditText editID;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        //buttons
        refreshButton = (Button) findViewById(R.id.refreshButton);
        viewStaffButton = (Button) findViewById(R.id.viewStaff);
        addMember = (Button) findViewById(R.id.addMember);
        updateMember = (Button) findViewById(R.id.updateMember);
        deleteMember = (Button) findViewById(R.id.deleteMember);
        deleteAll = (Button) findViewById(R.id.deleteAll);

        //textviews
        toDisplay = (TextView) findViewById(R.id.toDisplay);
        appTitle = (TextView) findViewById(R.id.appTitle);
        //inputs
        editName = (EditText) findViewById(R.id.editName);
        editID = (EditText) findViewById(R.id.editID);

        //initialize buttons
        viewStaffMembers();
        refresh();
        addMember(); // uses SQL
        updateMember(); //uses SQL
        deleteMember(); //uses SQL
        deleteAll();

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

//                XmlResourceParser parser = getResources().getXml(R.xml.stafflist);
//                Controller controller = new Controller(parser);
//
//                List<StaffMember> staffList = controller.viewStaffList();
//                String name;
//                int id;
//                String output = "";
//                for(StaffMember aMember: staffList){
//                    output += aMember.getId() +" " +aMember.getName() +"\n";
//                }
//                toDisplay.setText(output);

                Cursor result = myDb.getAllData();
                if(result.getCount()==0){
                    //show message
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("ID :" + result.getString(0)+"\n");
                    buffer.append("ID :" + result.getString(1)+"\n\n");
                }
                //show all Data
                toDisplay.setText(buffer);
                toastMessage("All members displayed.");

            }
        });
    }

    private void addMember() {
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editName.getText().toString());
                if(isInserted) {
                    toastMessage("Member added.");
                }
                else toastMessage("Nope.");
            }
        });
    }
    private void updateMember() {
        updateMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate =  myDb.updateData(editID.getText().toString(), editName.getText().toString());
                if(isUpdate){
                    toastMessage("Successfully updated member "+ editName.getText().toString()+".");
                } else toastMessage("Data has not been updated.");
            }
        });
    }

    private void deleteMember() {
        deleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = myDb.deleteData(editID.getText().toString());
                if(result>0){
                    toastMessage("Member has been deleted.");
                }else toastMessage("Invalid ID, member not deleted");
            }
        });
    }
    public void deleteAll() {
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = myDb.deleteAll();
                if(result==1){
                    toastMessage("All members deleted");
                }
            }
        });
    }

}