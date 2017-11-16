package com.example.team8.urlms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.*;

import static com.example.team8.urlms.R.id.toDisplay;

public class StaffMemberPage extends AppCompatActivity {

    private URLMS urlms;
    private String fileName;
    Controller c = new Controller();
    StaffController sc = new StaffController();

    TextView memberName;
    TextView memberID;
    TextView progressUpdate;

    Button backButton;
    Button viewProgressButton;
    Button editButton;

    EditText editName;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_member_page);
        //retrieve bundle
        position = getIntent().getExtras().getInt("memberPosition");

        //load controller and model
        fileName = getFilesDir().getAbsolutePath() + "/urlms.xml";
        URLMSApplication.setFilename(fileName);
        urlms = URLMSApplication.getURLMS();

        backButton = (Button) findViewById(R.id.backButton);
        setBackButton();

        editButton = (Button) findViewById(R.id.editNameButton);
        setEditButton();

        viewProgressButton = (Button) findViewById(R.id.viewProgressButton);
        setViewProgressButton();

        memberName = (TextView) findViewById(R.id.memberName);
        memberID = (TextView) findViewById(R.id.memberID);

        memberName.setText(urlms.getLab(0).getStaff().getStaffMember(position).getName());
        memberID.setText(Integer.toString(urlms.getLab(0).getStaff().getStaffMember(position).getId()));

        editName = (EditText) findViewById(R.id.editName);

        progressUpdate = (TextView) findViewById(R.id.progressText);
        progressUpdate.setVisibility(View.INVISIBLE);


    }

    private void setViewProgressButton() {
        viewProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressUpdate.setVisibility(View.VISIBLE);
                progressUpdate.setText("You did nothing you lazy bum.");
            }
        });
    }

    public void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.save();
                finish();
            }
        });
    }

    public void setEditButton(){
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().equals("")){
                    toastMessage("you didn't enter a string");
                }
                else sc.editStaffmemberName(editName.getText().toString(), position);
                sc.save();
            }
        });
    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }
}
