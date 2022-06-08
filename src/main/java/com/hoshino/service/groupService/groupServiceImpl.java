package com.hoshino.service.groupService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.Group;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:<p>群组的Service层</p>
 *<p>{@code FriendEventServiceImpl}提供了添加和删除好友以及获取好友列表的API</p>
 * <p><strong>此API用于加入群组{@link #joinGroup(int, int)}</strong></p>
 * <p><strong>此API用于获取群组列表{@link #getUserGroups(int)}</strong></p>
 * <p><strong>此API用于删除群组事件{@link #deleteGroupEvent(int, int)}</strong></p>
 * <p><strong>此API用于退出群组{@link #leaveGroup(int, int)}</strong></p>
 * <p><strong>此API用于发送群组信息{@link #sendGroupMessage(int, Date, Date, String, String)}</strong></p>
 * <p><strong>此API用于通过名字选择群组{@link #selectGroup(String)}</strong></p>
 * <p><strong>此API用于创建群组{@link #createGroup(String)}</strong></p>
 * @author like
 * @version 1.4
 */
@Service
public class groupServiceImpl implements groupService{
    @Autowired
    private GroupMapper groupMapper;

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    /**
     * <p>此API用于加入群组</p>
     * @param groupId 群组ID
     * @param userId 用户ID
     * @return 加入成功返回ture，失败返回false
     */
    @Override
    public boolean joinGroup(int groupId, int userId) {
        int i = groupMapper.groupAddUser(groupId, userId);
        return i>=1;
    }

    /**
     * <P>此API用于获取群组列表</P>
     * @param id 用户ID
     * @return 返回群组列表
     */
    @Override
    public List<Group> getUserGroups(int id) {
        return groupMapper.getUserGroups(id);
    }

    /**
     * <p>此API用于退出群组</p>
     * @param groupId 群组ID
     * @param userId 用户ID
     * @return 退出成功返回ture，失败返回false
     */
    @Override
    public boolean leaveGroup(int groupId, int userId) {
        int i = groupMapper.groupDeleteUser(groupId, userId);
        return i>=1;
    }

    /**
     * <p>此API用于发送群组信息</p>
     * @param groupId 群组ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param eventName  事件名称
     * @param eventDescription 事件描述
     * @return 发送成功返回ture，失败返回false
     */
    @Override
    public boolean sendGroupMessage(int groupId, Date startTime, Date endTime, String eventName, String eventDescription) {
        int i = groupMapper.sendGroupMessage(groupId, startTime, endTime, eventName, eventDescription);
        return i>=1;
    }

    /**
     * <p>此API用于删除群组事件</p>
     * @param groupId 群组ID
     * @param eventId 事件ID
     * @return 删除成功返回ture，失败返回false
     */
    @Override
    public boolean deleteGroupEvent(int groupId,int eventId) {
        int i = groupMapper.deleteGroupEvent(groupId,eventId);
        return i>=1;
    }

    /**
     * <p>此API用于通过名字选择群组</p>
     * @param groupName 群组名称
     * @return 群组
     */
    @Override
    public Group selectGroup(String groupName) {
        return groupMapper.selectGroup(groupName);
    }

    /**
     * <p>此API用于创建群组</p>
     * @param groupName 群组名称
     * @return 创建成功返回ture，失败返回false
     */
    @Override
    public boolean createGroup(String groupName) {
        int i = groupMapper.createGroup(groupName);
        return i>=1;
    }
}
