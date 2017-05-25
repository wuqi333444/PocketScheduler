package com.example.wuqi.pocketscheduler.main;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
    private TabLayout allTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setupBottomTab();
        setDefaultFragment();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
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
    private void setupBottomTab(){
        allTabs = (TabLayout) findViewById(R.id.tab_bottom);
        allTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (tabPosition)
        {
            case 0 :

                        if (mCalendar == null)
                        {
                            mCalendar = new CalendarFragment();
                        }
                        // 使用当前Fragment的布局替代id_content的控件
                        transaction.replace(R.id.id_content, mCalendar);
                 transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
            case 1 :

                if (mProject == null)
                {
                    mProject = new ProjectFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mProject);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
            case 2:
                if (mEvents == null)
                {
                    mEvents = new EventsFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mEvents);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
        }
    }

    public void onFragmentInteraction(Uri uri){

    }

}
