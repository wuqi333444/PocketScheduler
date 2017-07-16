package com.example.wuqi.pocketscheduler.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wuqi on 7/15/17.
 */

public class ReminderService extends Service {
    private AlarmManager alarmManager;
    private PendingIntent pi;
    private Long time;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setAlarms();
        return super.onStartCommand(intent, flags, startId);
    }

    synchronized public void setAlarms(){
        String title = "new event";
        String content = "content";
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        setAlarm(2017,6,15,21,50,title,content);

    }

    /**
     *
     * @param year
     * @param month month start with 0
     * @param day
     * @param hour
     * @param minute
     */
    private void setAlarm(int year, int month, int day, int hour, int minute,String title, String content){
        Intent startNotificationIntent = new Intent(this,ReminderReceiver.class);
        startNotificationIntent.putExtra("title",title);
        startNotificationIntent.putExtra("content",content);
        //System.out.println("alarm time:" + time + "current time:" + System.currentTimeMillis());
        pi = PendingIntent.getBroadcast(this, 0, startNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);     //设置事件

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.YEAR,year);
        time = c.getTimeInMillis();
        long interval = (time - System.currentTimeMillis())/1000;
        System.out.println("alarm ring after:" + interval + "s");
        if (time != null) {
            System.out.println("alarm set");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pi);    //提交事件，发送给 广播接收器
        } else {
            //当提醒时间为空的时候，关闭服务，下次添加提醒时再开启
            stopService(new Intent(this, ReminderService.class));
        }
    }
}
