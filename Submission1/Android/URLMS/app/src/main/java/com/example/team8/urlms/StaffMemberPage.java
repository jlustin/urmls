package com.example.team8.urlms;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.ResearchAssistant;
import ca.mcgill.ecse321.urlms.model.ResearchAssociate;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.*;

import static android.R.id.input;
import static ca.mcgill.ecse321.urlms.application.URLMSApplication.save;
import static com.example.team8.urlms.R.id.toDisplay;

public class StaffMemberPage extends AppCompatActivity {

    private URLMS urlms;
    private String fileName;

    private String m_Date;
    private String m_ProgressUpdateText;

    Controller c = new Controller();
    StaffController sc = new StaffController();

    TextView memberName;
    TextView memberID;
    TextView progressUpdate;

    Button backButton;
    Button viewProgressButton;
    Button addProgressButton;
    Button editButton;
    Button deleteButton;

    EditText editName;
    EditText editId;

    CheckBox researchAssistantBox;
    CheckBox researchAssociateBox;

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
        editButton = (Button) findViewById(R.id.editNameButton);
        viewProgressButton = (Button) findViewById(R.id.viewProgressButton);
        addProgressButton = (Button) findViewById(R.id.addProgress);
        deleteButton = (Button) findViewById(R.id.deleteButton);


        memberName = (TextView) findViewById(R.id.memberName);
        memberName.setText(sc.viewStaffMemberName(position));

        memberID = (TextView) findViewById(R.id.memberID);
        memberID.setText(sc.viewStaffMemberID(position));

        editName = (EditText) findViewById(R.id.editName);
        editName.setText(sc.viewStaffMemberName(position));
        editId = (EditText) findViewById(R.id.editId);
        editId.setText(sc.viewStaffMemberID(position));

        progressUpdate = (TextView) findViewById(R.id.progressText);
        progressUpdate.setVisibility(View.INVISIBLE);

        researchAssistantBox = (CheckBox) findViewById(R.id.researchAssistantBox);
        researchAssociateBox = (CheckBox) findViewById(R.id.researchAssociateBox);

        List<StaffMember> members = sc.viewStaffList();
        StaffMember currentMember = members.get(position);

        if (currentMember.hasResearchRoles()) {
            int rolesSize = currentMember.getResearchRoles().size();
            for (int i = 0; i < rolesSize; i++) {
                if (currentMember.getResearchRole(i) instanceof ResearchAssistant) {
                    researchAssistantBox.setChecked(true);
                }
                if (currentMember.getResearchRole(i) instanceof ResearchAssociate) {
                    researchAssociateBox.setChecked(true);
                }
            }
        }
        setEditButton();
        setBackButton();
        setViewProgressButton();
        setAddProgressButton();
        setDeleteButton();

    }

    private void setAddProgressButton(){
        addProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(StaffMemberPage.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_progress, null);
                final EditText mDate = (EditText) mView.findViewById(R.id.editTextDate);
                final EditText mProgress = (EditText) mView.findViewById(R.id.editTextProgress);
                Button mConfirm = (Button) mView.findViewById(R.id.buttonConfirm);
                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!mDate.getText().toString().isEmpty() && !mProgress.getText().toString().isEmpty()){
                            sc.addProgress(mDate.getText().toString(), mProgress.getText().toString(),position);
                            toastMessage("Progress Updated");
                        }
                        else{
                            toastMessage("Please fill any empty fields.");
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        sc.save();
      }
    private void setViewProgressButton() {
        viewProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressUpdate.setVisibility(View.VISIBLE);
                List<ProgressUpdate> progress = sc.viewProgressUpdate(position);
                String progressToDisplay = "";
                for(int i=0; i<progress.size();i++){
                    progressToDisplay+= progress.get(i).getDate()+"\n";
                    progressToDisplay+= progress.get(i).getDescription()+"\n";
                    progressUpdate.setText(progressToDisplay);
                }
            }
        });
        sc.save();
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
                if(editName.getText().toString().isEmpty()){
                    toastMessage("you didn't enter a string");
                }
                else sc.editStaffmemberRecord(position,Integer.parseInt(editId.getText().toString()), editName.getText().toString(), researchAssistantBox.isChecked(), researchAssociateBox.isChecked());
                toastMessage("Member successfully updated, refresh page to see");
                sc.save();
            }
        });
    }

    public void setDeleteButton(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.removeStaffMember(position);
                toastMessage("Member sucessfully deleted.");
                sc.save();
                finish();
            }
        });
    }

    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }



}

