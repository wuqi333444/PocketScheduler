package com.example.wuqi.pocketscheduler.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.util.ArrayList;

import static com.example.wuqi.pocketscheduler.R.id.project_part2_content;
import static com.example.wuqi.pocketscheduler.R.id.ra;

/**
 * Created by RapHaeL on 2017/5/24.
 */

public class CustomAdapter extends ArrayAdapter {
    PocketDBHelper ra = new PocketDBHelper(getContext());
    private int backgroundColor;
    public CustomAdapter(Activity context, ArrayList<Creator> creator, int mbackgroundColor) {
        super(context, 0,creator);
        backgroundColor = mbackgroundColor;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.project_events, parent, false);
        }
        final Creator currentProject = (Creator) getItem(position);
        View mDeleteEvent = (View) listItemView.findViewById(R.id.delete_button);
        View mDoneEvent = (View) listItemView.findViewById(R.id.done_button);
        View mDelayEvent = (View) listItemView.findViewById(R.id.later_button);
        View mClickEvent = (View) listItemView.findViewById(R.id.project_clickpart2);
        mClickEvent.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent info = new Intent(getContext(),Project_part2.class);
                info.putExtra("cursorId",position);
                getContext().startActivity(info);
            }
        });


        mDeleteEvent.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view){
                SQLiteDatabase db = ra.getWritableDatabase();
                db.delete(Contract.ProjectEntry.TABLE_NAME,Contract.ProjectEntry._ID + "=" + currentProject.getProjectId() ,null);
                System.out.println("DELETE id = " + currentProject.getProjectId());
                Toast.makeText(getContext(),"Project with ID: " + currentProject.getProjectId() + "is removed",Toast.LENGTH_LONG).show();
            }
        });
        Creator ra1 = (Creator) getItem(position);
        TextView ra2 = (TextView)listItemView.findViewById(R.id.ra1);
        ra2.setText(ra1.getmSName());
        TextView ra3 = (TextView)listItemView.findViewById(R.id.ra6);
        ra3.setText(ra1.getmLName());
        TextView ra4 = (TextView) listItemView.findViewById(R.id.ra4);
        ra4.setText(ra1.getmDate2().toString());
        TextView ra5 = (TextView) listItemView.findViewById(R.id.ra2);
        ra5.setText(ra1.getmCreator());
        TextView ra6 = (TextView) listItemView.findViewById(R.id.ra3);
        ra6.setText(ra1.getmType());
        return listItemView;
    }
}
