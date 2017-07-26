package com.example.wuqi.pocketscheduler.event;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by under on 5/24/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    private PocketDBHelper ra = new PocketDBHelper(getContext());
    private ArrayList<Event> arrayList;
    public EventAdapter(Activity context, ArrayList<Event> e){
        super(context,0,e);
        arrayList = e;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.deletable_list, parent, false);
        }
        final Event currentEvent = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.event_name);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.event_time);


        View mDeleteEvent = (View) listItemView.findViewById(R.id.delete_button);
        View mDoneEvent = (View) listItemView.findViewById(R.id.done_button);
        View mDelayEvent = (View) listItemView.findViewById(R.id.later_button);
        PocketDBHelper mDbHelper = new PocketDBHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(Contract.EventEntry.TABLE_NAME,null,null,null,null,null,null);
        mDeleteEvent.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view){
                SQLiteDatabase db = ra.getWritableDatabase();
                db.delete(Contract.EventEntry.TABLE_NAME,Contract.EventEntry._ID + "=" + currentEvent.getEvent_id() ,null);
                System.out.println("DELETE id = " + currentEvent.getEvent_id());
                Toast.makeText(getContext(),"Event with ID: " + currentEvent.getEvent_id() + "is removed",Toast.LENGTH_LONG).show();
                //Below is used to refresh the listview after an item is deleted.
                arrayList.clear();
                arrayList = addNew(arrayList);
                notifyDataSetChanged();
            }
        });
        nameTextView.setText(currentEvent.getEvent_name());
        if(currentEvent.getEvent_startTime()!=null){
            if(currentEvent.getEvent_endTime()!=null){
                timeTextView.setText(currentEvent.getEvent_startTime()+"-"+currentEvent.getEvent_endTime());
            }
            else{
                timeTextView.setText(currentEvent.getEvent_startTime());
            }
        }
        return listItemView;
    }
    private ArrayList<Event> addNew(ArrayList eventArrayList){
        PocketDBHelper mDbHelper = new PocketDBHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(Contract.EventEntry.TABLE_NAME,null,null,null,null,null,null);
        try{

            int idColumnIndex = cursor.getColumnIndex(Contract.EventEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_TITLE);
            int startColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_STARTTIME);
            int endColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_ENDTIME);
            while(cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(nameColumnIndex);
                long start_time = cursor.getLong(startColumnIndex);
                long end_time = cursor.getLong(endColumnIndex);
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                eventArrayList.add(new Event(currentTitle,formatter.format(start_time),formatter.format(end_time),currentId));
            }
        }
        finally{
            return eventArrayList;
        }
    }
}

