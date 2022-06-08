package com.hoshino.service.eventService;

import com.hoshino.mapper.UserEvent.UserEventMapper;
import com.hoshino.pojo.UserEvent;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
/**
 * Description:<p>用户事件的Service层</p>
 *<p>{@code FriendEventServiceImpl}提供了获取用户事件和添加和删除用户事件的API</p>
 * <p><strong>获取用户事件{@link #getUserEvents(int)}</strong></p>
 * <p><strong>添加用户事件{@link #addUserEvent(UserEvent)} (int, int)}</strong></p>
 * <p><strong>删除用户事件{@link #deleteUserEvent(int)}</strong></p>
 * @author like
 * @version 1.4
 */
@Service
public class UserEventServiceImpl implements UserEventService{
    @Autowired
    private UserEventMapper userEventMapper;

    public void setUserEventMapper(UserEventMapper userEventMapper) {
        this.userEventMapper = userEventMapper;
    }

    /**
     * <p>此API用于获取用户事件</p>
     * @param userId 用户ID
     * @return 用户事件列表
     */
    @Override
    public List<UserEvent> getUserEvents(int userId) {
        return userEventMapper.getEventByUserId(userId);
    }

    /**
     * <p>此API用于添加用户事件</p>
     * @param userEvent 用户事件
     * @return 添加成功返回ture，失败返回false
     */
    @Override
    public boolean addUserEvent(UserEvent userEvent) {
        int i = userEventMapper.addUserEvent(userEvent);
        return i>=1;
    }

    /**
     * <p>此API用于删除用户事件</p>
     * @param eventId 事件ID
     * @return 删除成功返回ture，失败返回false
     */
    @Override
    public boolean deleteUserEvent(int eventId) {
        int i = userEventMapper.deleteUserEvent(eventId);
        return i>=1;
    }


}
