package com.example.wuqi.pocketscheduler;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity{
    /*private MainTab02 mTab02;
    private MainTab01 mTab01;
    private MainTab03 mTab03;*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 初始化控件和声明事件
       /* mTabWeixin = (LinearLayout) findViewById(R.id.tab_bottom_weixin);
        mTabFriend = (LinearLayout) findViewById(R.id.tab_bottom_friend);
        mTabWeixin.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);

        // 设置默认的Fragment
        setDefaultFragment();*/
    }
}
