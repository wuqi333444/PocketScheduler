package com.example.wuqi.pocketscheduler;

import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Project_part2 extends AppCompatActivity implements InfoFragment.OnFragmentInteractionListener,ScheduleFragment.OnFragmentInteractionListener,QuestFragment.OnFragmentInteractionListener{
    private Button mTabInfo;
    private Button mTabScheule;
    private Button mTabQuest;
    private InfoFragment mInfo;
    private ScheduleFragment mSchedule;
    private QuestFragment mQuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_part2);
        mTabInfo = (Button) findViewById(R.id.Button_info);
        mTabScheule = (Button) findViewById(R.id.Button_schedule);
        mTabQuest = (Button) findViewById(R.id.Button_quest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mTabInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (mInfo == null)
                {
                    mInfo = new InfoFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mInfo);
                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
            }

        });
        mTabScheule.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (mSchedule == null)
                {
                    mSchedule = new ScheduleFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mSchedule);

                transaction.addToBackStack(null);
                // 事务提交
                transaction.commit();
            }
        });
        mTabQuest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (mQuest == null)
                {
                    mQuest = new QuestFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mQuest);

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
        if (mSchedule == null)
        {
            mSchedule = new ScheduleFragment();
        }
        // 使用当前Fragment的布局替代id_content的控件
        transaction.replace(R.id.id_content, mSchedule);

        transaction.addToBackStack(null);
        // 事务提交
        transaction.commit();
    }
    public void onFragmentInteraction(Uri uri){

    }
}


