package com.example.wuqi.pocketscheduler.event;

/**
 * Created by under on 5/24/2017.
 */

public class Event {
    private String event_name;
    private String event_startTime;
    private String event_endTime;
    private int event_id;

    /**
     *Three ways to construct object Event.Only with name, with name and start time or with name , start time and end time.
     */
    public Event(String eventName){
        event_name = eventName;
    }
    public Event(String eventName, int eventId){
        event_name = eventName;
        event_id = eventId;
    }
    public Event(String eventName,String startTime,String endTime){
        event_name = eventName;
        event_startTime = startTime;
        event_endTime = endTime;
    }
    public Event(String eventName,String startTime, String endTime, int eventId){
        event_name = eventName;
        event_startTime = startTime;
        event_endTime = endTime;
        event_id = eventId;
    }
    public Event(String eventName, String startTime){
        event_name = eventName;
        event_startTime = startTime;
    }
    public String getEvent_name(){return event_name;}
    public String getEvent_startTime(){
        if(event_startTime != null){
            return event_startTime;
        }
        return null;
    }
    public String getEvent_endTime(){
        if(event_endTime != null){
            return event_endTime;
        }
        return null;
    }

    public int getEvent_id(){
        return event_id;
    }

}
