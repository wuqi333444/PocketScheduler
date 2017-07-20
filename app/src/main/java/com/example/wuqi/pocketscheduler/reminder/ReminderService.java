package com.example.wuqi.pocketscheduler.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wuqi on 7/15/17.
 */

public class ReminderService extends Service {
    private AlarmManager alarmManager;
    private PendingIntent pi;
    private Long time;

    private PocketDBHelper dbHelper;
    private SQLiteDatabase mDb;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbHelper = new PocketDBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        setAlarms(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    public void setAlarms(Intent intent){
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Cursor result = queryAlarmOrderByTime();
        if (result == null || result.getCount() == 0){//no alarm currently
            stopService(intent);
        } else {
            result.moveToFirst();
            Calendar c = Calendar.getInstance();
            String title = result.getString(result.getColumnIndex(Contract.EventEntry.COLUMN_TITLE));
            String content = result.getString(result.getColumnIndex(Contract.EventEntry.COLUMN_DESCRIPTION));
            c.setTimeInMillis(result.getLong(result.getColumnIndex(Contract.EventEntry.COLUMN_STARTTIME)));
            setAlarm(c, title, content);
        }

    }
    private Cursor queryAlarmOrderByTime(){
        String[] args = new String[3];
        args[0] = Contract.EventEntry.COLUMN_DESCRIPTION;
        args[1] = Contract.EventEntry.COLUMN_STARTTIME;
        args[2] = Contract.EventEntry.COLUMN_TITLE;
        String[] whereCondition = new String[1];
        whereCondition[0] = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Cursor cursor = mDb.query(Contract.EventEntry.TABLE_NAME,args,Contract.EventEntry.COLUMN_STARTTIME +"> ?",whereCondition,null,null,Contract.EventEntry.COLUMN_STARTTIME + " desc");
        System.out.println("cursor size:" + cursor.getCount());
        return cursor;
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
    private void setAlarm(Calendar c,String title, String content){
        Intent startNotificationIntent = new Intent(this,ReminderReceiver.class);
        startNotificationIntent.putExtra("title",title);
        startNotificationIntent.putExtra("content",content);
        //System.out.println("alarm time:" + time + "current time:" + System.currentTimeMillis());
        pi = PendingIntent.getBroadcast(this, 0, startNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);     //设置事件
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
