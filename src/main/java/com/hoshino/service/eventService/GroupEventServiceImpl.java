package com.hoshino.service.eventService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.GroupEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupEventServiceImpl implements GroupEventService{
    @Autowired
    private GroupMapper groupMapper;

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<GroupEvent> getGroupEvents(int groupId) {
        return groupMapper.getGroupEvents(groupId);
    }
}
