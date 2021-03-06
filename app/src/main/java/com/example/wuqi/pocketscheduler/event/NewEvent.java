package com.example.wuqi.pocketscheduler.event;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.TimeZoneFormat;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import com.example.wuqi.pocketscheduler.data.Contract.EventEntry;
import com.example.wuqi.pocketscheduler.reminder.ReminderService;
import com.example.wuqi.pocketscheduler.utils.TimeConvertUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.R.attr.format;

public class NewEvent extends AppCompatActivity {
    private EditText mEventName;
    private DatePicker mEventStartDate;
    private TimePicker mEventStartTime;
    private DatePicker mEventEndDate;
    private TimePicker mEventEndTime;
    private EditText mEventDescription;
    private EditText mEventLocation;
    private EditText mEventCreatorId;
    private Spinner mEventTypeSpinner;
    private Spinner mEventEditPrioritySpinner;

    private int mType = 1;
    private int mEditPriority = 1;

    private PocketDBHelper ra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEventName = (EditText) findViewById(R.id.edit_event_name);
        mEventStartTime = (TimePicker) findViewById(R.id.event_starttime);
        mEventStartDate = (DatePicker) findViewById(R.id.event_startdate);
        mEventEndDate = (DatePicker) findViewById(R.id.event_enddate);
        mEventEndTime = (TimePicker) findViewById(R.id.event_endtime);
        mEventDescription = (EditText) findViewById(R.id.edit_evedescription);
        mEventLocation = (EditText) findViewById(R.id.edit_evelocation);
        mEventCreatorId = (EditText) findViewById(R.id.edit_evecreator);
        mEventEditPrioritySpinner = (Spinner) findViewById(R.id.spinner_eveeditpriority);
        mEventTypeSpinner = (Spinner) findViewById(R.id.spinner_evetype);
        ra = new PocketDBHelper(this);
        setupEditPrioritySpinner();
        setupTypeSpinner();
    }

    private void setupTypeSpinner(){
        ArrayAdapter typeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_evetype_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mEventTypeSpinner.setAdapter(typeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mEventTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.evetype_entertaiment))) {
                        mType = Contract.EventEntry.TYPE_ENTERTAIMENT; // Only
                    } else if(selection.equals(getString(R.string.evetype_family))) {
                        mType = Contract.EventEntry.TYPE_FAMILY; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_date))) {
                        mType = Contract.EventEntry.TYPE_DATE; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_friend))) {
                        mType = Contract.EventEntry.TYPE_FRIEND; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_job))) {
                        mType = Contract.EventEntry.TYPE_JOB; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_sport))) {
                        mType = Contract.EventEntry.TYPE_SPORT; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_study))) {
                        mType = Contract.EventEntry.TYPE_STUDY; // All
                    }
                    else if(selection.equals(getString(R.string.evetype_trip))) {
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

    private void setupEditPrioritySpinner(){
        ArrayAdapter editPrioritySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_eveeditpriority_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        editPrioritySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mEventEditPrioritySpinner.setAdapter(editPrioritySpinnerAdapter);

        // Set the integer mSelected to the constant values
        mEventEditPrioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.eveeditpriority_only))) {
                        mEditPriority = Contract.EventEntry.EDITPRIORITY_CREATOR; // Only
                    } else if(selection.equals(getString(R.string.eveeditpriority_all))) {
                        mEditPriority = Contract.EventEntry.EDITPRIORITY_ALL; // All
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mEditPriority = 0; // Only
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
            String eventName = mEventName.getText().toString().trim();

            int eventStartTimeHour = mEventStartTime.getHour();
            int eventStartTimeMin = mEventStartTime.getMinute();

            int eventStartDateYear = mEventStartDate.getYear();
            int eventStartDateMonth = mEventStartDate.getMonth();
            int eventStartDateDay = mEventStartDate.getDayOfMonth();

            int eventEndTimeHour = mEventEndTime.getHour();
            int eventEndTimeMin = mEventEndTime.getMinute();

            int eventEndDateYear = mEventEndDate.getYear();
            int eventEndDateMonth = mEventEndDate.getMonth();
            int eventEndDateDay = mEventEndDate.getDayOfMonth();

            String newTimeStr1 = String.valueOf(TimeConvertUtils.dateToLongConverter(eventStartDateYear,eventStartDateMonth,eventStartDateDay,eventStartTimeHour,eventStartTimeMin));
            String newTimeStr2 = String.valueOf(TimeConvertUtils.dateToLongConverter(eventEndDateYear,eventEndDateMonth,eventEndDateDay,eventEndTimeHour,eventEndTimeMin));
            System.out.println("alarm new time string1 " + newTimeStr1);
            System.out.println("alarm new time string2 " + newTimeStr2);
            String eventDescription = mEventDescription.getText().toString().trim();

            String eventLocation = mEventLocation.getText().toString().trim();

            String eventCreator = mEventCreatorId.getText().toString().trim();

            String eventEditPriority = mEventEditPrioritySpinner.getSelectedItem().toString();

            String eventType = mEventTypeSpinner.getSelectedItem().toString();

            SQLiteDatabase db = ra.getWritableDatabase();
            ContentValues ra1 = new ContentValues();
            ra1.put(EventEntry.COLUMN_TITLE,eventName);
            ra1.put(EventEntry.COLUMN_STARTTIME, newTimeStr1);
            ra1.put(EventEntry.COLUMN_ENDTIME, newTimeStr2);
            ra1.put(EventEntry.COLUMN_DESCRIPTION,eventDescription);
            ra1.put(EventEntry.COLUMN_LOCATION,eventLocation);
            ra1.put(EventEntry.COLUMN_CREATORID,eventCreator);
            ra1.put(EventEntry.COLUMN_EDITPRIORITY,eventEditPriority);
            ra1.put(EventEntry.COLUMN_TYPE,eventType);
            long rowNewId = db.insert(EventEntry.TABLE_NAME,null,ra1);
            if(rowNewId == -1)
                Toast.makeText(getApplicationContext(),"Error with saving events",Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplicationContext(), "Event saved with row id:" + rowNewId, Toast.LENGTH_LONG).show();
                Intent reminderServiceIntent = new Intent(this, ReminderService.class);
                startService(reminderServiceIntent);
            }

        }
}
