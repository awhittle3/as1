package ca.ualberta.awhittle.awhittle_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class DisplayActivity extends AppCompatActivity {
    public final static String EXTRA_ENTRY_INDEX = "ca.ualberta.awhittle.awhittle_fueltrack.entry_index";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button newEntryButton = (Button) findViewById(R.id.button_new);
        Button editEntryButton = (Button) findViewById(R.id.button_edit);
        final RadioGroup radioEntries = (RadioGroup) findViewById(R.id.radio_entries);

        newEntryButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                    Intent intent = new Intent(DisplayActivity.this, EditEntryActivity.class);
                    intent.putExtra(EXTRA_ENTRY_INDEX, -1);
                    startActivity(intent);
            }
        });

        editEntryButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditEntryActivity.class);
                // -1 is put if no button is checked. Behaviour creates a new entry.
                intent.putExtra(EXTRA_ENTRY_INDEX, radioEntries.getCheckedRadioButtonId());
                startActivity(intent);
            }
        });


    }

}
