package com.hoshino.service.eventService;

import com.hoshino.pojo.GroupEvent;

import java.util.List;

public interface GroupEventService {
    public List<GroupEvent> getGroupEvents(int groupId);
}
