package com.hoshino.service.eventService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.GroupEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:<p>群组事件的Service层</p>
 *<p>{@code FriendEventServiceImpl}提供了获取群组信息的API</p>
 * <p><strong>获取群组信息{@link #getGroupEvents(int)}</strong></p>
 * @author like
 * @version 1.4
 */
@Service
public class GroupEventServiceImpl implements GroupEventService{
    @Autowired
    private GroupMapper groupMapper;

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    /**
     * <p>此API是用于获取群组信息</p>
     * @param groupId 群组ID
     * @return 群组信息列表
     */
    @Override
    public List<GroupEvent> getGroupEvents(int groupId) {
        return groupMapper.getGroupEvents(groupId);
    }
}
