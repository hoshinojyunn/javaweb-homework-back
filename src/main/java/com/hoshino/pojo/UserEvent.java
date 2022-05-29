package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private int eventId;
    private int userId;
    private String eventName;
    private Date startTime;
    private Date endTime;
    private String eventDescription;

    public UserEvent(int userId, String eventName, Date startTime, Date endTime,String eventDescription){
        this.userId = userId;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDescription = eventDescription;
    }
}
