package com.hoshino.service.eventService;

import com.hoshino.mapper.UserEvent.UserEventMapper;
import com.hoshino.pojo.UserEvent;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserEventServiceImpl implements UserEventService{
    @Autowired
    private UserEventMapper userEventMapper;

    public void setUserEventMapper(UserEventMapper userEventMapper) {
        this.userEventMapper = userEventMapper;
    }

    @Override
    public List<UserEvent> getUserEvents(int userId) {
        return userEventMapper.getEventByUserId(userId);
    }

    @Override
    public boolean addUserEvent(UserEvent userEvent) {
        int i = userEventMapper.addUserEvent(userEvent);
        return i>=1;
    }

    @Override
    public boolean deleteUserEvent(int eventId) {
        int i = userEventMapper.deleteUserEvent(eventId);
        return i>=1;
    }


}
