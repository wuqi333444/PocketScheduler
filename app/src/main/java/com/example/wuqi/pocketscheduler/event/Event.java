package com.example.wuqi.pocketscheduler.event;

/**
 * Created by under on 5/24/2017.
 */

public class Event {
    private String event_name;
    public Event(String eventName){
        event_name = eventName;
    }
    public String getEvent_name(){return event_name;    }

}
