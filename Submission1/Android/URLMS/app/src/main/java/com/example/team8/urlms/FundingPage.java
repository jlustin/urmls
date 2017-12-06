package com.example.team8.urlms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.StaffMember;

public class FundingPage extends AppCompatActivity {

    TextView toDisplay;

    Button backButton;
    Button viewFundingAccounts;
    Button addFundingAccount;
    Button mPayDayButton;

    EditText editType;
    EditText editAmount;

    FundingController fc = new FundingController();
    StaffController sc = new StaffController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_page);

        backButton = (Button) findViewById(R.id.backButton);
        viewFundingAccounts = (Button) findViewById(R.id.viewFundingAccountsButton);
        addFundingAccount = (Button) findViewById(R.id.addFundingAccountButton);
        mPayDayButton = (Button) findViewById(R.id.payDayButton);


        toDisplay = (TextView) findViewById(R.id.toDisplay);
        toDisplay.setText("Net Balance: " + String.valueOf(fc.viewNetBalance()));

        editType = (EditText) findViewById(R.id.editType);
        editAmount = (EditText) findViewById(R.id.editAmount);


        setBackButton();
        setViewFundingAccounts();
        setAddFundingAccount();
        setPayDayButton();
        }


    public void setPayDayButton() {
        mPayDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payDayAuthorization();
            }
        });

    }

    public void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.save();
                finish();
            }
        });
    }
    public void setAddFundingAccount(){
        addFundingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editType.getText().toString().equals("")&&!editAmount.getText().toString().equals("")){
                    fc.addFundingAccount(editType.getText().toString(), Double.parseDouble(editAmount.getText().toString()));
                toastMessage("Funding Account successfully added.");
                }
                else {
                    toastMessage("Please fill in all sections.");
                }
            }
        });
    }
    public void setViewFundingAccounts(){
        viewFundingAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //package the bundle========================================================
                List<FundingAccount> fa = fc.viewFundingAccounts();
                String[] fundingAccounts= new String[fa.size()];
                int i=0;
                for(FundingAccount funding : fa){
                    fundingAccounts[i]="ID: "+ funding.getType()+ " Balance: " + fc.viewFundingAccountBalance(i);
                    i++;
                }

                Bundle b = new Bundle();
                b.putStringArray("data", fundingAccounts);
                //==========================================================================

                Intent intent = new Intent(getApplicationContext(),FundingListPage.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        myToast.show();
    }
    public void payDayAuthorization(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Admin Access Required." +"\n" + "All staff members will be paid from staff funds account.")
                .setCancelable(false)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                        double amount = 0;
                        List<StaffMember> sm = sc.viewStaffList();
                        for(StaffMember  member: sm) {
                            amount+=member.getWeeklySalary();
                        }
                        fc.addTransaction(date, amount, "Payday", "Staff Funds");
                        toastMessage("All members paid, view transaction history in Staff Funds.");

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


