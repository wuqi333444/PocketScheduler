package com.example.wuqi.pocketscheduler.main;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.wuqi.pocketscheduler.calender.CalendarFragment;
import com.example.wuqi.pocketscheduler.event.EventComingSoonFragment;
import com.example.wuqi.pocketscheduler.event.EventDraftboxFragment;
import com.example.wuqi.pocketscheduler.event.EventPreviousFragment;
import com.example.wuqi.pocketscheduler.event.EventsFragment;
import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.event.EventPendingboxFragment;
import com.example.wuqi.pocketscheduler.project.ProjectCreatorFragment;
import com.example.wuqi.pocketscheduler.project.ProjectFragment;
import com.example.wuqi.pocketscheduler.project.ProjectParticipatorFragment;


public class MainActivity extends AppCompatActivity implements CalendarFragment.OnFragmentInteractionListener, ProjectFragment.OnFragmentInteractionListener,EventsFragment.OnFragmentInteractionListener,ProjectCreatorFragment.OnFragmentInteractionListener,ProjectParticipatorFragment.OnFragmentInteractionListener,EventPreviousFragment.OnFragmentInteractionListener,EventDraftboxFragment.OnFragmentInteractionListener,EventPendingboxFragment.OnFragmentInteractionListener,EventComingSoonFragment.OnFragmentInteractionListener {
    private Button mTabCalendar;
    private Button mTabProject;
    private Button mTabEvents;
    private CalendarFragment mCalendar;
    private ProjectFragment mProject;
    private EventsFragment mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 初始化控件和声明事件
        mTabCalendar = (Button) findViewById(R.id.Button_calendar);
        mTabProject = (Button) findViewById(R.id.Button_project);
        mTabEvents = (Button) findViewById(R.id.Button_events);

        mTabCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                        if (mCalendar == null)
                        {
                            mCalendar = new CalendarFragment();
                        }
                        // 使用当前Fragment的布局替代id_content的控件
                        transaction.replace(R.id.id_content, mCalendar);
                 transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
            }

        });
        mTabProject.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (mProject == null)
                {
                    mProject = new ProjectFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mProject);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
            }
        });
        mTabEvents.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (mEvents == null)
                {
                    mEvents = new EventsFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mEvents);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
            }
        });
        // 设置默认的Fragment
        setDefaultFragment();
    }
    private void setDefaultFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        if (mCalendar == null)
        {
            mCalendar = new CalendarFragment();
        }
        // 使用当前Fragment的布局替代id_content的控件
        transaction.replace(R.id.id_content, mCalendar);

        transaction.addToBackStack(null);
        // 事务提交
        transaction.commit();
    }

    public void onFragmentInteraction(Uri uri){

    }

}
