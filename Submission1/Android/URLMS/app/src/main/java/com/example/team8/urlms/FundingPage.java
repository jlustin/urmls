package com.example.team8.urlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.StaffMember;

public class FundingPage extends AppCompatActivity {

    TextView toDisplay;

    Button backButton;
    Button viewFundingAccounts;
    Button addFundingAccount;

    EditText editType;
    EditText editAmount;

    FundingController fc = new FundingController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_page);

        backButton = (Button) findViewById(R.id.backButton);
        viewFundingAccounts = (Button) findViewById(R.id.viewFundingAccountsButton);
        addFundingAccount = (Button) findViewById(R.id.addFundingAccountButton);



        toDisplay = (TextView) findViewById(R.id.toDisplay);
        toDisplay.setText("Net Balance: " + String.valueOf(fc.viewNetBalance()));

        editType = (EditText) findViewById(R.id.editType);
        editAmount = (EditText) findViewById(R.id.editAmount);


        setBackButton();
        setViewFundingAccounts();
        setAddFundingAccount();

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
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }
}
