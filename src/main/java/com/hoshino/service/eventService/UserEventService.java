package com.hoshino.service.eventService;

import com.hoshino.pojo.UserEvent;

import java.util.List;

public interface UserEventService {
    public List<UserEvent> getUserEvents(int userId);

    public boolean addUserEvent(UserEvent userEvent);

    public boolean deleteUserEvent(int eventId);
}
