package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Description: 实体类 对应数据库group_event表
 */
public class GroupEvent {
    /**
     * groupEventId对应表的group_event_id
     * groupId对应表的group_id
     * groupName对应表的group_name
     * eventName对应表的event_name
     * startTime对应表的start_name
     * endTime对应表的end_time
     * eventDescription对应表的event_description
     */
    private int groupEventId;
    private int groupId;
    private String groupName;
    private String eventName;
    private Date startTime;
    private Date endTime;
    private String eventDescription;
}
