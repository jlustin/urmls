package com.example.team8.urlms;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.*;

import android.content.res.XmlResourceParser;
import android.widget.Toast;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.team8.urlms.R.string.addMember;
import static com.example.team8.urlms.R.string.deleteAll;
import static com.example.team8.urlms.R.string.refreshButton;


//import static com.example.team8.urlms.MainActivity.load;

public class MainActivity extends AppCompatActivity {


    private URLMS urlms;
    private String fileName;


    Button staff;
    Button funding;
    Button inventory;
    TextView toDisplay;
    TextView appTitle;
    EditText editName;
    EditText editID;

    Controller c = new Controller();
    StaffController sc = new StaffController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //load controller and model
        fileName = getFilesDir().getAbsolutePath() + "/urlms2.xml";
        URLMSApplication.setFilename(fileName);
        urlms = URLMSApplication.getURLMS();

        //buttons
        staff = (Button) findViewById(R.id.staffButton);
        funding = (Button) findViewById(R.id.fundingButton);
        inventory = (Button) findViewById(R.id.inventoryButton);

        //textviews
        toDisplay = (TextView) findViewById(R.id.toDisplay);
        appTitle = (TextView) findViewById(R.id.appTitle);
        toDisplay.setMovementMethod(new ScrollingMovementMethod());

        /*
         *initiate all buttons
         */
        openFunding();openInventory();openStaff();


    }

    //toast
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }

    //button methods
    public void openStaff(){
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(StaffPage.class);
            }
        });
    }    public void openFunding(){
        funding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            toastMessage("NEXT EXPANSION");
                startActivity(FundingPage.class);
            }
        });
    }    public void openInventory(){
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            toastMessage("Subscribe monthly to get the latest features");
                startActivity(InventoryPage.class);

            }
        });
    }

    public void startActivity(Class<?> cls){
        sc.save();
        Intent intent = new Intent(this,cls );
        startActivity(intent);
    }






    public void deleteAllAuthorization(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Admin Access Required.")
                .setCancelable(false)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toDisplay.setText("");
                toastMessage("All members deleted");

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            AlertDialog display = alert.create();
            alert.setTitle("Admin");
            alert.show();
    }

}
