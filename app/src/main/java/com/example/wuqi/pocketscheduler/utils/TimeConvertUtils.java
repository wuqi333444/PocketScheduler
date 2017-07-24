package com.example.wuqi.pocketscheduler.utils;

import java.util.Calendar;

/**
 * Created by wuqi on 7/24/17.
 */

public class TimeConvertUtils {
    public static long dateToLongConverter(int year, int month, int day, int hour, int minute){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.YEAR,year);
        return c.getTimeInMillis();
    }
    public static long dateToLongConverter(String year, String month, String day, String hour, String minute){
        int yearVal = Integer.valueOf(year);
        int monthVal = Integer.valueOf(month);
        int dayVal = Integer.valueOf(day);
        int hourVal = Integer.valueOf(hour);
        int minuteVal = Integer.valueOf(minute);
        return dateToLongConverter(yearVal,monthVal,dayVal,hourVal,minuteVal);
    }
}
