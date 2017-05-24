package com.example.wuqi.pocketscheduler;

import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import static com.example.wuqi.pocketscheduler.R.id.list_creator;
import static com.example.wuqi.pocketscheduler.R.id.ra;
import static com.example.wuqi.pocketscheduler.R.id.ra1;
import static com.example.wuqi.pocketscheduler.R.id.ra2;

/**
 * Created by RapHaeL on 2017/5/24.
 */

public class CustomAdapter extends ArrayAdapter {
    private int backgroundColor;
    public CustomAdapter(Activity context, ArrayList<Creator> creator,int mbackgroundColor) {
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
        return listItemView;
    }
}
