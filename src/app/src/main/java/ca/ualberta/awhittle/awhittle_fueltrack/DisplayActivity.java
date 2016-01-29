package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    public final static String EXTRA_ENTRY_INDEX = "ca.ualberta.awhittle.awhittle_fueltrack.entry_index";
    public static final String FILENAME = "ca.ualberta.awhittle.awhittle_fueltrack.logentries.txt";
    private static final String TAG = "DisplayActivity";

    private Button newEntryButton;
    private Button editEntryButton;
    private RadioGroup radioEntries;
    private ListView listView;

    private Gson gson;

    private static LogList logList;
    private static DisplayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        gson = new Gson();

        newEntryButton = (Button) findViewById(R.id.button_new);
        editEntryButton = (Button) findViewById(R.id.button_edit);
        radioEntries = (RadioGroup) findViewById(R.id.radio_entries);

        // Transition to the EditEntryActivity to create a new entry
        newEntryButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditEntryActivity.class);
                intent.putExtra(EXTRA_ENTRY_INDEX, -1);
                startActivity(intent);
            }
        });

        // Transition to the EditEntryActivity to edit an entry
        editEntryButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditEntryActivity.class);
                // -1 is put if no button is checked. Behaviour creates a new entry.
                intent.putExtra(EXTRA_ENTRY_INDEX, radioEntries.getCheckedRadioButtonId());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.listView);

        loadListFromFile();

        adapter = new DisplayListAdapter(DisplayActivity.this, logList.getList());
        listView.setAdapter(adapter);

        // TODO: Hook up total cost text

    }

    public static DisplayListAdapter getAdapter() {
        return adapter;
    }

    private void loadListFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<List<LogEntry>>() {}.getType();
            List<LogEntry> list = gson.fromJson(in, listType);
            logList = new LogList(list);
        } catch (FileNotFoundException e) {
            // We want to create an empty LogList
            logList = new LogList();
        }
    }

    public static LogList getLogList() {
        return logList;
    }
}
