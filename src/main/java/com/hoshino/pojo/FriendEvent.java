package com.hoshino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendEvent {
    private int friendEventId;
    private int from;
    private int to;
    private Date createDate;
    private String message;
}
