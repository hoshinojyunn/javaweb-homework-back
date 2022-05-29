package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupEvent {
    private int groupEventId;
    private int groupId;
    private String groupName;
    private String eventName;
    private Date startTime;
    private Date endTime;
    private String eventDescription;
}
