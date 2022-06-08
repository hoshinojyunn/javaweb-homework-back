package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Description: 实体类 对应数据库user_event表
 */
public class UserEvent {
    /**
     * eventId: 对应表的event_id
     * userId: 对应表的user_id
     * eventName: 对应表的event_name
     * startTime: 对应表的start_time
     * endTime: 对应表的end_time
     * eventDescriptionP: 对应表的event_description
     */
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
