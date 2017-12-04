package com.example.team8.urlms;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.model.Expense;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.URLMS;

import static android.os.Build.VERSION_CODES.O;

public class FundingAccountPage extends AppCompatActivity {

    private int position;
    private URLMS urlms;
    private String fileName;
    String date = "";

    Controller controller = new Controller();
    InventoryController ic = new InventoryController();
    FundingController fc = new FundingController();

    Button backButton;
    Button viewTransactionsButton;
    Button addFundingButton;
    Button addExpenseButton;
    Button removeButton;

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
        removeButton = (Button) findViewById(R.id.removeButton);

        currentAccount = (TextView) findViewById(R.id.currentAccountTypeText);
        currentBalance = (TextView) findViewById(R.id.currentBalanceText);
        toDisplayTransactions = (TextView) findViewById(R.id.transactionsText);
        toDisplayTransactions.setMovementMethod(new ScrollingMovementMethod());


        currentAccount.setText(fc.viewFundingAccountType(position));
        currentBalance.setText(fc.viewFundingAccountBalance(position));
        toDisplayTransactions.setVisibility(View.INVISIBLE);

        setBackButton();
        setViewTransactionsButton();
        setAddFundingButton();
        setAddExpenseButton();
        setRemoveButton();

    }

    public void setRemoveButton(){
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAuthorization();
            }
        });

    }
    private void setAddFundingButton() {
addFundingButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(FundingAccountPage.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_add_funding, null);
        final EditText mAmount = (EditText) mView.findViewById(R.id.editTextAmount);
        Button mConfirm = (Button) mView.findViewById(R.id.buttonConfirm);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mAmount.getText().toString().isEmpty()){
                    fc.addFunding(position, Double.parseDouble(mAmount.getText().toString()));
                    toastMessage("Funding Added");
                    dialog.dismiss();
                    recreate();
                }
                else{
                    toastMessage("Please fill any empty fields.");
                }
            }
        });
    }
});
    }
    public void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {fc.save();
                finish();
            }
        });
    }
    public void setViewTransactionsButton(){
        viewTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Expense> expenses = fc.viewFundingAccountExpenses(position);
                String expensesList = "";
                for(int i=0; i<expenses.size();i++){
                    expensesList += "Date: " +expenses.get(i).getDate()+"\n" +
                            "Description: " + expenses.get(i).getType() +"\n" + "Amount: "+
                            String.valueOf(expenses.get(i).getAmount())+"\n\n";
                }
                toDisplayTransactions.setText(expensesList);
                toDisplayTransactions.setVisibility(View.VISIBLE);
            }
        });

    }
    public void setAddExpenseButton(){
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(FundingAccountPage.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_expense, null);
                final EditText mType = (EditText) mView.findViewById(R.id.editExpenseDescription);
                final DatePicker mDatePicker = (DatePicker) mView.findViewById(R.id.datePicker);
                final EditText mAmount = (EditText) mView.findViewById(R.id.editTextAmount);
                Button mConfirm = (Button) mView.findViewById(R.id.buttonConfirm);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mDatePicker.init(2017, 11, 03, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = (monthOfYear+1)+ "-"+dayOfMonth+ "-"+year;
                    }
                });
                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!mType.getText().toString().isEmpty()&&!mAmount.getText().toString().isEmpty()){
                            fc.addTransaction(date,Double.parseDouble(mAmount.getText().toString()),
                                    mType.getText().toString(),fc.viewFundingAccountType(position), position);

                            toastMessage("Expense Added");
                            dialog.dismiss();
                            recreate();
                        }
                        else{
                            toastMessage("Please fill any empty fields.");
                        }
                    }
                });
            }
        });

    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }
    public void deleteAuthorization(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Admin Access Required.")
                .setCancelable(false)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(position>2) {
                            fc.removeFundingAccount(position);
                            toastMessage("Account sucessfully deleted.");
                            fc.save();
                            finish();
                        }else{
                            toastMessage("Cannot remove essential accounts");
                        }
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
