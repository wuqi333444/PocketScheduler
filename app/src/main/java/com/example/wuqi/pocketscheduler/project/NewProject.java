package com.example.wuqi.pocketscheduler.project;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NewProject extends AppCompatActivity {

    private EditText mProjectCreator;
    private DatePicker mProjectStartDate;
    private TimePicker mProjectStartTime;
    private EditText mProjectName;
    private EditText mProjectDescription;
    private Spinner mProjectTypeSpinner;

    private int mType = 1;
    private PocketDBHelper ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProjectCreator = (EditText) findViewById(R.id.edit_procreator);
        mProjectStartTime = (TimePicker) findViewById(R.id.project_starttime);
        mProjectStartDate = (DatePicker) findViewById(R.id.project_startdate);
        mProjectName = (EditText) findViewById(R.id.edit_project_name);
        mProjectDescription = (EditText) findViewById(R.id.edit_prodescription);
        mProjectTypeSpinner = (Spinner) findViewById(R.id.spinner_protype);
        ra = new PocketDBHelper(this);
        setupTypeSpinner();
    }

    private void setupTypeSpinner(){
        ArrayAdapter typeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_protype_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mProjectTypeSpinner.setAdapter(typeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mProjectTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.protype_entertaiment))) {
                        mType = Contract.EventEntry.TYPE_ENTERTAIMENT; // Only
                    } else if(selection.equals(getString(R.string.protype_family))) {
                        mType = Contract.EventEntry.TYPE_FAMILY; // All
                    }
                    else if(selection.equals(getString(R.string.protype_date))) {
                        mType = Contract.EventEntry.TYPE_DATE; // All
                    }
                    else if(selection.equals(getString(R.string.protype_friend))) {
                        mType = Contract.EventEntry.TYPE_FRIEND; // All
                    }
                    else if(selection.equals(getString(R.string.protype_job))) {
                        mType = Contract.EventEntry.TYPE_JOB; // All
                    }
                    else if(selection.equals(getString(R.string.protype_sport))) {
                        mType = Contract.EventEntry.TYPE_SPORT; // All
                    }
                    else if(selection.equals(getString(R.string.protype_study))) {
                        mType = Contract.EventEntry.TYPE_STUDY; // All
                    }
                    else if(selection.equals(getString(R.string.protype_trip))) {
                        mType = Contract.EventEntry.TYPE_TRIP; // All
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mType = 0; // Only
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_newevent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveEvent();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void saveEvent(){
        String projectName = mProjectName.getText().toString().trim();

        int projectStartDateYear = mProjectStartDate.getYear();
        int projectStartDateMonth = mProjectStartDate.getMonth();
        int projectStartDateDay = mProjectStartDate.getDayOfMonth();
        int projectStartTimeHour = mProjectStartTime.getHour();
        int projectStartTimeMin = mProjectStartTime.getMinute();

        String timeStr = String.valueOf(projectStartDateMonth).toString() + "-" + String.valueOf(projectStartDateDay).toString() + "-" + String.valueOf(projectStartDateYear).toString() + "  " + String.valueOf(projectStartTimeHour).toString() + ":" + String.valueOf(projectStartTimeMin).toString();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        format.setTimeZone(TimeZone.getDefault());
        Date dateTime = null;
        try{
            dateTime = (Date) format.parse(timeStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        long newTimeStr = dateTime.getTime();


        String projectDescription = mProjectDescription.getText().toString().trim();

        String projectCreator = mProjectCreator.getText().toString().trim();

        String projectType = mProjectTypeSpinner.getSelectedItem().toString();

        SQLiteDatabase db = ra.getWritableDatabase();
        ContentValues ra1 = new ContentValues();
        ra1.put(Contract.ProjectEntry.COLUMN_TITLE,projectName);
        ra1.put(Contract.ProjectEntry.COLUMN_BEGINTIME, newTimeStr);
        ra1.put(Contract.ProjectEntry.COLUMN_DESCRIPTION,projectDescription);
        ra1.put(Contract.ProjectEntry.COLUMN_CREATOR,projectCreator);
        ra1.put(Contract.ProjectEntry.COLUMN_TYPE,projectType);
        long rowNewId = db.insert(Contract.ProjectEntry.TABLE_NAME,null,ra1);
        if(rowNewId == -1)
            Toast.makeText(getApplicationContext(),"Error with saving events",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Project saved with row id:" + rowNewId,Toast.LENGTH_LONG).show();
    }
}
