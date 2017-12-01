package com.example.team8.urlms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mcgill.ecse321.urlms.controller.FundingController;

public class FundingPage extends AppCompatActivity {

    TextView toDisplay;

    Button backButton;
    Button viewFundingAccounts;
    Button lol;

    FundingController fc = new FundingController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_page);

        backButton = (Button) findViewById(R.id.backButton);
        viewFundingAccounts = (Button) findViewById(R.id.viewFundingAccountsButton);

        toDisplay = (TextView) findViewById(R.id.toDisplay);

        setBackButton();

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

}
