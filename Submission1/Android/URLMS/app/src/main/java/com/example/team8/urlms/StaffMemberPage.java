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

        memberName = (TextView) findViewById(R.id.memberName);
        memberName.setText(sc.viewStaffMemberName(position));

        memberID = (TextView) findViewById(R.id.memberID);
        memberID.setText(sc.viewStaffMemberID(position));

        editName = (EditText) findViewById(R.id.editName);
        editId = (EditText) findViewById(R.id.editId);

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

    }
    //// TODO: 2017-11-29 Add date input also
    private void setAddProgressButton(){
        addProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StaffMemberPage.this);
                builder.setTitle("Write Progress");

// Set up the input
                final EditText input = new EditText(StaffMemberPage.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_ProgressUpdateText = input.getText().toString();
                        sc.addProgress("What day is today?", m_ProgressUpdateText, position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
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
//// TODO: 2017-11-29 add special cases: user enters string in id, or user doesn't enter anything...
    public void setEditButton(){
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().equals("")){
                    toastMessage("you didn't enter a string");
                }
else sc.editStaffmemberRecord(position,Integer.parseInt(editId.getText().toString()), editName.getText().toString(), researchAssistantBox.isChecked(), researchAssociateBox.isChecked());
                sc.save();
            }
        });
    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }
}
