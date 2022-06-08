package com.hoshino.service.eventService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.FriendEvent;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

/**
 * Description:<p>好友事件的Service层</p>
 *<p>{@code FriendEventServiceImpl}提供了发送好友信息和获取好友信息的API</p>
 * <p><strong>发送好友信息{@link #sendFriendEvent(int, int, String)}</strong></p>
 * <p><strong>获取好友信息{@link #getFriendEvent(int, int)}</strong></p>
 * @author like
 * @version 1.4
 */
public class FriendEventServiceImpl implements FriendEventService{
    /**
     * {@code UserFriendsMapper} 操作用户好友的Mapper
     * @see com.hoshino.mapper.UserFriends.UserFriendsMapper
     */
    @Autowired
    private UserFriendsMapper userFriendsMapper;

    public void setUserFriendsMapper(UserFriendsMapper userFriendsMapper) {
        this.userFriendsMapper = userFriendsMapper;
    }

    /**
     * <p>此API是发送好友信息的API</p>
     * @param from 发送人ID
     * @param to 获取人ID
     * @param message 存放信息
     * @return 如果发送成功返回true，错误返回false
     */

    @Override
    public boolean sendFriendEvent(int from, int to, String message) {
        int i = userFriendsMapper.sendFriendEvent(from, to, message);
        return i>=1;
    }

    /**
     * <p>此API用于获取好友信息</p>
     * @param from 发送者ID
     * @param to 获取者ID
     * @return 返回好友信息列表
     */
    @Override
    public List<FriendEvent> getFriendEvent(int from, int to) {
        return userFriendsMapper.getFriendEvent(from, to);
    }

}
