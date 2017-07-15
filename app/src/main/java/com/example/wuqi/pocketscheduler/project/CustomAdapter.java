package com.example.wuqi.pocketscheduler.project;

import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wuqi.pocketscheduler.R;

import java.util.ArrayList;

/**
 * Created by RapHaeL on 2017/5/24.
 */

public class CustomAdapter extends ArrayAdapter {
    private int backgroundColor;
    public CustomAdapter(Activity context, ArrayList<Creator> creator, int mbackgroundColor) {
        super(context, 0,creator);
        backgroundColor = mbackgroundColor;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.project_events, parent, false);
        }
        Creator ra1 = (Creator) getItem(position);
        TextView ra2 = (TextView)listItemView.findViewById(R.id.ra1);
        ra2.setText(ra1.getmSName());
        TextView ra3 = (TextView)listItemView.findViewById(R.id.ra6);
        ra3.setText(ra1.getmLName());
        TextView ra4 = (TextView) listItemView.findViewById(R.id.ra4);
        ra4.setText(ra1.getmDate());
        TextView ra5 = (TextView) listItemView.findViewById(R.id.ra2);
        ra5.setText(ra1.getmCreator());
        TextView ra6 = (TextView) listItemView.findViewById(R.id.ra3);
        ra6.setText(ra1.getmType());
        return listItemView;
    }
}
