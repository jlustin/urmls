package com.example.team8.urlms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.team8.urlms.R.id.toDisplay;

public class MainActivity extends AppCompatActivity {

    Button backButton;
    Button viewStaffButton;
    TextView toDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backButton = (Button) findViewById(R.id.backButton);
        viewStaffButton = (Button) findViewById(R.id.viewStaff);
        toDisplay = (TextView) findViewById(R.id.toDisplay);

        //initiate scrolling
        toDisplay.setMovementMethod(new ScrollingMovementMethod());

        viewStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDisplay.setText(R.string.longText);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toDisplay.setText(null);

            }
        });

    }
}
