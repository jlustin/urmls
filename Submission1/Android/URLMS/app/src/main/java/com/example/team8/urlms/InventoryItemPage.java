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
import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.URLMS;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class InventoryItemPage extends AppCompatActivity {

    private URLMS urlms;
    private String fileName;

    Controller controller = new Controller();
    InventoryController ic = new InventoryController();

    Button backButton;
    Button editButton;
    Button deleteButton;

    TextView currentName;
    TextView currentCost;
    TextView currentQuantity;
    TextView totalCostDisplay;

    EditText editName;
    EditText editCost;
    EditText editQuantity;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item_page);

        //retrieve bundle
        position = getIntent().getExtras().getInt("itemPosition");

        //load controller and model
        fileName = getFilesDir().getAbsolutePath() + "/urlms.xml";
        URLMSApplication.setFilename(fileName);
        urlms = URLMSApplication.getURLMS();


        backButton = (Button) findViewById(R.id.backButton);
        editButton = (Button) findViewById(R.id.editButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        editName = (EditText) findViewById(R.id.editName);
        editCost = (EditText) findViewById(R.id.editCost);
        editQuantity = (EditText) findViewById(R.id.editQuantity);

        currentName = (TextView) findViewById(R.id.itemName);
        currentCost = (TextView) findViewById(R.id.itemCost);
        currentQuantity = (TextView) findViewById(R.id.quantityText);
        totalCostDisplay = (TextView) findViewById(R.id.totalCostDisplay);

        currentName.setText(ic.viewInventoryItemName(position));
        currentCost.setText(ic.viewInventoryItemCost(position));
        editName.setText(ic.viewInventoryItemName(position));
        editCost.setText(ic.viewInventoryItemCost(position));


        if(ic.inventoryItemIsEquipment(position)){
            currentQuantity.setVisibility(View.INVISIBLE);
            editQuantity.setVisibility(View.INVISIBLE);
            editQuantity.setText("0");
            totalCostDisplay.setVisibility(View.INVISIBLE);
        }
        else {
            //TODO: change quantity edit text to text view and add addQuantityButton and incorporate fundingcontroller
            editQuantity.setText(ic.viewSupplyItemQuantity(position));
            currentQuantity.setText(ic.viewInventoryItemName(position));
            double costPerUnit = Double.parseDouble(ic.viewInventoryItemCost(position));
            int currentQuantity = Integer.parseInt(ic.viewSupplyItemQuantity(position));
            double totalCost = costPerUnit * currentQuantity;
            totalCostDisplay.setVisibility(View.VISIBLE);
            totalCostDisplay.setText("The total cost for this supply is " +totalCost + ".");
        }

        setEditButton();
        setBackButton();
        setDeleteButton();
    }

    public void setEditButton(){
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic.editInventoryItemDetails(position, editName.getText().toString(),
                        Double.parseDouble(editCost.getText().toString())
                        , Integer.parseInt(editQuantity.getText().toString()));
                ic.save();
                recreate();
                toastMessage("Sucessfully updated.");
            }
        });
    }

    public void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic.save();
                finish();
            }
        });
    }

    public void setDeleteButton(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic.removeInventoryItem(position);
                ic.save();
                finish();
            }
        });
    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }
}
