package com.example.team8.urlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

import static ca.mcgill.ecse321.urlms.application.URLMSApplication.save;

public class InventoryPage extends AppCompatActivity {


    private URLMS urlms;
    private String fileName;
    Controller controller = new Controller();
    InventoryController ic = new InventoryController();


    Button backButton;
    Button viewInventoryItemListButton;
    Button addItemButton;

    EditText insertName;
    EditText insertCost;
    EditText insertQuantity;

    TextView toDisplay;
    TextView toDisplayStatus;

    TextView toDisplayQuantity;

    String itemType;
    final String type_supply = "supply";
    final String type_equipment = "equipment";

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_page);


        toDisplay = (TextView) findViewById(R.id.toDisplay);
        toDisplayStatus = (TextView) findViewById(R.id.toDisplayStatus);
        toDisplayQuantity = (TextView) findViewById(R.id.quantityText);

        backButton = (Button) findViewById(R.id.backButton);
        viewInventoryItemListButton = (Button) findViewById(R.id.viewInventoryListButton);
        addItemButton = (Button) findViewById(R.id.addNewItemButton);

        insertName = (EditText) findViewById(R.id.insertItemName);
        insertCost = (EditText) findViewById(R.id.insertCostItem);
        insertQuantity = (EditText) findViewById(R.id.insertQuantityItem);




        spinner = (Spinner) findViewById(R.id.spinnerItemType);

        setSpinner();
        setBackButton();
        setViewInventoryItemListButton();
        setAddItemButton();

        List<InventoryItem> ii = ic.viewInventoryList();
        int totalSupplyItem = 0;
        int totalEquipmentItem = 0;
        for(int i=0; i<ii.size();i++){
            if(ic.inventoryItemIsEquipment(i)){
                totalEquipmentItem++;
            }
            else{
                totalSupplyItem++;
            }
        }
        toDisplayStatus.setText("Number of Equipment Item(s): " +totalEquipmentItem + "\n" +
                "Number of Supply Item(s): " +totalSupplyItem);
    }

    private void setAddItemButton() {
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean added = false;
                if(!insertName.getText().toString().equals("")&& !insertCost.getText().toString().equals("")) {
                    if (itemType.equals(type_equipment)) {
                        ic.addEquipmentItem(insertName.getText().toString(), Double.parseDouble(insertCost.getText().toString()));
                        added = true;
                    }
                    if (itemType.equals(type_supply) && !insertQuantity.getText().toString().equals("")
                            ) {
                        ic.addSupplyItem(insertName.getText().toString(), Double.parseDouble(insertCost.getText().toString()),
                                Integer.parseInt(insertQuantity.getText().toString()));
                        added = true;
                    }
                    if (added) {
                        toastMessage("Item: " + insertName.getText().toString() + " successfully added.");
                    }
                }
                else{
                    toastMessage("Please fill the appropriate categories.");
                }
                ic.save();
                recreate();
            }
        });
    }
    private void setViewInventoryItemListButton(){
        viewInventoryItemListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //package the bundle========================================================
                List<InventoryItem> ii = ic.viewInventoryList();
                String[] inventoryItems= new String[ii.size()];

                for(int i =0; i<ii.size(); i++){
                    inventoryItems[i]= "Item: " + ic.viewInventoryItemName(i)+"\n" +
                                       "Cost: " +ic.viewInventoryItemCost(i)+"\n";
                    if(ic.inventoryItemIsEquipment(i)) {
                        inventoryItems[i]+= "Type: Equipment ";
                    }else {
                        inventoryItems[i]+="Type: Supply " +"\n" + "Quantity: " + ic.viewSupplyItemQuantity(i);
                    }
                }

                Bundle b = new Bundle();
                b.putStringArray("data", inventoryItems);
                //==========================================================================
                ic.save();
                Intent intent = new Intent(getApplicationContext(),InventoryListPage.class );
                intent.putExtras(b);
                startActivity(intent);

            }
        });

            }

    private void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                finish();
            }
        });

    }
    public void toastMessage(String message){
        Toast myToast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void setSpinner(){
        adapter = ArrayAdapter.createFromResource(this, R.array.item_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    itemType = type_equipment;
                    insertQuantity.setVisibility(View.INVISIBLE);
                    toDisplayQuantity.setVisibility(View.INVISIBLE);
                }
                if(position==1){
                    itemType = type_supply;
                    insertQuantity.setVisibility(View.VISIBLE);
                    toDisplayQuantity.setVisibility(View.VISIBLE);
                    toastMessage("Supply type selected, please specify the quantity.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
