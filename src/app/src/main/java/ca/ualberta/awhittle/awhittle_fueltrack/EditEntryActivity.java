package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

public class EditEntryActivity extends AppCompatActivity {

    private int targetIndex;

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

        // Get all the edit text views
        editDate = (EditText) findViewById(R.id.editTextDate);
        editStation = (EditText) findViewById(R.id.editTextStation);
        editOdo = (EditText) findViewById(R.id.editTextOdo);
        editGrade = (EditText) findViewById(R.id.editTextGrade);
        editAmount = (EditText) findViewById(R.id.editTextAmount);
        editUnitcost = (EditText) findViewById(R.id.editTextUnitcost);


        Button saveButton = (Button) findViewById(R.id.buttonCommit);
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        // Save the data and notify the adapter
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    // Create the new entry
                    String date = editDate.getText().toString();
                    String station = editStation.getText().toString();
                    double reading = Double.parseDouble(editOdo.getText().toString());
                    String grade = editGrade.getText().toString();
                    double amount = Double.parseDouble(editAmount.getText().toString());
                    double unitcost = Double.parseDouble(editUnitcost.getText().toString());

                    LogEntry newEntry = new LogEntry(date, station, reading, grade, amount, unitcost);

                    // Add to the logList
                    if(targetIndex == -1){
                        DisplayActivity.getLogList().add(newEntry);
                    } else {
                        DisplayActivity.getLogList().set(targetIndex, newEntry);
                    }

                    // Save
                    saveListToFile();

                    // Notify the adapter
                    DisplayActivity.getAdapter().notifyDataSetChanged();

                    // Return to the DisplayActivity
                    Intent intent = new Intent(EditEntryActivity.this, DisplayActivity.class);
                    startActivity(intent);
                } catch (NumberFormatException e){
                    // If the user did not enter numbers, inform them
                    Toast errorToast = Toast.makeText(EditEntryActivity.this, getString(R.string.error_numformat),
                            Toast.LENGTH_LONG);
                    errorToast.show();
                }
            }
        });

        // Return to DisplayActivity without doing anything with the data
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
            // Make the fields clean
            editDate.setText("");
            editStation.setText("");
            editOdo.setText("");
            editGrade.setText("");
            editAmount.setText("");
            editUnitcost.setText("");
        } else {
            // Populate the fields with the relevant data
            LogEntry myEntry = DisplayActivity.getLogList().get(targetIndex);

            editDate.setText(myEntry.getDate());
            editStation.setText(myEntry.getStation());
            editOdo.setText(df1.format(myEntry.getOdoReading()));
            editGrade.setText(myEntry.getFuelGrade());
            editAmount.setText(df3.format(myEntry.getFuelAmount()));
            editUnitcost.setText(df1.format(myEntry.getFuelUnitCost()));
        }
    }

    /**
     * Saves the data to a file
     * Uses data from DisplayActivity
     */
    public void saveListToFile(){
        try {
            FileOutputStream fos = openFileOutput(DisplayActivity.FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(DisplayActivity.getLogList().getList(), out);
            out.flush();
            fos.close();
        } catch(FileNotFoundException e) {
            // Fail if there is no file
            throw new RuntimeException();
        } catch (IOException e){
            // Fail if there is an IO error
            throw new RuntimeException();
        }
    }
}
