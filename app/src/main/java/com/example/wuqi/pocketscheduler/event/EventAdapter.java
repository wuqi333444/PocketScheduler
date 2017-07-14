package com.example.wuqi.pocketscheduler.event;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wuqi.pocketscheduler.R;

import java.util.ArrayList;

/**
 * Created by under on 5/24/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Activity context, ArrayList<Event> e){
        super(context,0,e);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.event_list, parent, false);
        }
        final Event currentEvent = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.event_name);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.event_time);
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

