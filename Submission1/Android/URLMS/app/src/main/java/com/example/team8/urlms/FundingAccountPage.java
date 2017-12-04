package com.example.team8.urlms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class FundingAccountPage extends AppCompatActivity {

    private int position;
    private URLMS urlms;
    private String fileName;

    Controller controller = new Controller();
    InventoryController ic = new InventoryController();
    FundingController fc = new FundingController();

    Button backButton;
    Button viewTransactionsButton;
    Button addFundingButton;
    Button addExpenseButton;

    TextView currentAccount;
    TextView currentBalance;
    TextView toDisplayTransactions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_account_page);
        //retrieve bundle
        position = getIntent().getExtras().getInt("itemPosition");

        //load controller and model
        fileName = getFilesDir().getAbsolutePath() + "/urlms.xml";
        URLMSApplication.setFilename(fileName);
        urlms = URLMSApplication.getURLMS();

        backButton = (Button) findViewById(R.id.backButton);
        viewTransactionsButton = (Button) findViewById(R.id.viewTransactionsButton);
        addFundingButton = (Button) findViewById(R.id.addFundingButton);
        addExpenseButton = (Button) findViewById(R.id.addNewExpenseButton);

        currentAccount = (TextView) findViewById(R.id.currentAccountTypeText);
        currentBalance = (TextView) findViewById(R.id.currentBalanceText);
        toDisplayTransactions = (TextView) findViewById(R.id.transactionsText);


        currentAccount.setText(fc.viewFundingAccountType(position));
        currentBalance.setText(fc.viewFundingAccountBalance(position));
        toDisplayTransactions.setVisibility(View.INVISIBLE);

        setBackButton();
        setViewTransactionsButton();
        setAddFundingButton();
        setAddExpenseButton();

    }

    private void setAddFundingButton() {
        
    }
    public void setBackButton(){

    }
    public void setViewTransactionsButton(){

    }
    public void setAddExpenseButton(){

    }
}
