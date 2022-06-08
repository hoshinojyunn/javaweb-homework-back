package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Description: 实体类 对应数据库friend_event表
 */
public class FriendEvent {
    /**
     * friendEventId: 对应表的friend_event_id
     * form: 对应表的form
     * to: 对应表的to
     * createDate: 对应表的create_date
     * message: 对应表的message
     */
    private int friendEventId;
    private int from;
    private int to;
    private Date createDate;
    private String message;
}
