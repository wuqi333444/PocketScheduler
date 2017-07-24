package com.example.wuqi.pocketscheduler.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.login.LoginActivity;

/**
 * Created by wuqi on 7/15/17.
 */

public class ReminderReceiver extends BroadcastReceiver {

    private NotificationManager manager;
    private static final int NOTIFICATION_ID_1 = 0x00113;
    private String title;
    private String content = "提醒的时间到啦，快看看你要做的事...";
    @Override
    public void onReceive(Context context, Intent intent) {
//此处接收闹钟时间发送过来的广播信息，为了方便设置提醒内容
        System.out.println("alarm received");
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content ");
        showNormal(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, ReminderService.class);
        context.startService(intent);  //回调Service,同一个Service只会启动一个，所以直接再次启动Service，会重置开启新的提醒，
    }    /**     * 发送通知     */
    private void showNormal(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);//这里是点击Notification 跳转的界面，可以自己选择
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setTicker(content)        //通知时在状态栏显示的通知内容
                .setContentInfo("便签提醒")        //内容信息
                .setContentTitle(title)        //设置通知标题。
                .setContentText(content)        //设置通知内容。
                .setAutoCancel(true)                //点击通知后通知消失
                .setDefaults(Notification.DEFAULT_VIBRATE)        //设置系统默认的通知音乐、振动、LED等。
                .setContentIntent(pi)
                .build();
        manager.notify(NOTIFICATION_ID_1, notification);
    }
}
