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
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.util.ArrayList;

/**
 * Created by under on 5/24/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    PocketDBHelper ra = new PocketDBHelper(getContext());
    public EventAdapter(Activity context, ArrayList<Event> e){
        super(context,0,e);
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
            }
        });
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentEvent.getEvent_name());
        if(currentEvent.getEvent_startTime()!=null){
            if(currentEvent.getEvent_endTime()!=null){
                timeTextView.setText(currentEvent.getEvent_startTime()+"-"+currentEvent.getEvent_endTime());
            }
            else{
                timeTextView.setText(currentEvent.getEvent_startTime());
            }
        }

        //ImageView imgView = (ImageView) listItemView.findViewById(R.id.miwokImage);
        //currentWords.getImageId();
      //  if(currentWords.isImageProvided() ) {
    //        imgView.setImageResource(currentWords.getImageId());
//        }
//        else{
//            imgView.setVisibility(View.GONE);
//        }
//        LinearLayout pt = (LinearLayout) nameTextView.getParent();
        //This show how to get color from colorId
//        pt.setBackgroundColor(ContextCompat.getColor(getContext(),colorId));
        return listItemView;
    }
}

