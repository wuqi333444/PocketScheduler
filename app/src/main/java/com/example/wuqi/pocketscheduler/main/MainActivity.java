package com.example.wuqi.pocketscheduler.main;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


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
    private CalendarFragment mCalendar;
    private ProjectFragment mProject;
    private EventsFragment mEvents;
    private TabLayout allTabs;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setupBottomTab();
        setDefaultFragment();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mDrawerList = (LinearLayout) findViewById(R.id.id_left_drawer);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };
        myToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mDrawerLayout.openDrawer(mDrawerList);

            }
        });
        // Set the adapter for the list view
      //  mDrawerList.setAdapter(new ArrayAdapter<String>(this,
      //          R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
       // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
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
