package com.example.wuqi.pocketscheduler.project;

import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wuqi.pocketscheduler.R;

public class Project_part2 extends AppCompatActivity implements InfoFragment.OnFragmentInteractionListener,ScheduleFragment.OnFragmentInteractionListener,QuestFragment.OnFragmentInteractionListener {
    private InfoFragment mInfo;
    private ScheduleFragment mSchedule;
    private QuestFragment mQuest;
    private TabLayout tabProject2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_part2);
        tabProject2 = (TabLayout) findViewById(R.id.tab_project_2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setDefaultFragment();
        setupProject2Tab();
    }
    private void setDefaultFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        if (mInfo == null)
        {
            mInfo = new InfoFragment();
        }
        // 使用当前Fragment的布局替代id_content的控件
        transaction.replace(R.id.project_part2_content, mInfo);

        transaction.addToBackStack(null);
        // 事务提交
        transaction.commit();
    }
    private void setupProject2Tab(){
        tabProject2 = (TabLayout) findViewById(R.id.tab_project_2);
        tabProject2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

                if (mInfo == null)
                {
                    mInfo = new InfoFragment();
                }
                transaction.replace(R.id.project_part2_content, mInfo);
                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
            case 1 :

                if (mSchedule == null)
                {
                    mSchedule = new ScheduleFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.project_part2_content, mSchedule);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
            case 2:
                if (mQuest == null)
                {
                    mQuest = new QuestFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.project_part2_content, mQuest);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
                break;
        }
    }
    public void onFragmentInteraction(Uri uri){

    }
}


