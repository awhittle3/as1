package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class EditEntryActivity extends AppCompatActivity {

    private int targetIndex;
    private LogList myData;
    private Gson gson;

    private EditText editDate;
    private EditText editStation ;
    private EditText editOdo;
    private EditText editGrade;
    private EditText editAmount;
    private EditText editUnitcost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        gson = new Gson();

        // Get all the edit text views
        editDate = (EditText) findViewById(R.id.editTextDate);
        editStation = (EditText) findViewById(R.id.editTextStation);
        editOdo = (EditText) findViewById(R.id.editTextOdo);
        editGrade = (EditText) findViewById(R.id.editTextGrade);
        editAmount = (EditText) findViewById(R.id.editTextAmount);
        editUnitcost = (EditText) findViewById(R.id.editTextUnitcost);


        Button saveButton = (Button) findViewById(R.id.buttonCommit);
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                DisplayActivity.getAdapter().notifyDataSetChanged();

                Intent intent = new Intent(EditEntryActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(EditEntryActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get target index to edit. -1 if new entry
        Intent intent = getIntent();
        targetIndex = intent.getIntExtra(DisplayActivity.EXTRA_ENTRY_INDEX, -1);

        DecimalFormat df1 = new DecimalFormat("#.0");
        DecimalFormat df3 = new DecimalFormat("#.000");

        if(targetIndex == -1) {
            editDate.setText("");
            editStation.setText("");
            editOdo.setText("");
            editGrade.setText("");
            editAmount.setText("");
            editUnitcost.setText("");
        } else {
            editDate.setText(myData.get(targetIndex).getDate());
            editStation.setText(myData.get(targetIndex).getStation());
            editOdo.setText(df1.format(myData.get(targetIndex).getOdoReading()));
            editGrade.setText(myData.get(targetIndex).getFuelGrade());
            editAmount.setText(df3.format(myData.get(targetIndex).getFuelAmount()));
            editUnitcost.setText(df1.format(myData.get(targetIndex).getFuelUnitCost()));
        }
    }
}
