package com.hoshino.service.eventService;

import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.User;

import java.util.List;

public interface FriendEventService {

    public boolean sendFriendEvent(int from,int to,String message);

    public List<FriendEvent> getFriendEvent(int from, int to);
}
