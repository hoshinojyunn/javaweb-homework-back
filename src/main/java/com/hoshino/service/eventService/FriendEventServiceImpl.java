package com.hoshino.service.eventService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.FriendEvent;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

public class FriendEventServiceImpl implements FriendEventService{
    @Autowired
    private UserFriendsMapper userFriendsMapper;

    public void setUserFriendsMapper(UserFriendsMapper userFriendsMapper) {
        this.userFriendsMapper = userFriendsMapper;
    }

    @Override
    public boolean sendFriendEvent(int from, int to, String message) {
        int i = userFriendsMapper.sendFriendEvent(from, to, message);
        return i>=1;
    }

    @Override
    public List<FriendEvent> getFriendEvent(int from, int to) {
        return userFriendsMapper.getFriendEvent(from, to);
    }

}
