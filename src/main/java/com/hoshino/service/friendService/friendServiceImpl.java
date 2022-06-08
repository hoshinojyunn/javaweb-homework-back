package com.hoshino.service.friendService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:<p>好友的Service层</p>
 *<p>{@code FriendEventServiceImpl}提供了添加和删除好友以及获取好友列表的API</p>
 * <p><strong>获取好友列表{@link #getFriends(int)}</strong></p>
 * <p><strong>添加好友{@link #addFriend(int, int)}</strong></p>
 * <p><strong>删除好友{@link #deleteFriend(int, int)}</strong></p>
 * @author like
 * @version 1.4
 */
@Service
public class friendServiceImpl implements friendService{
    @Autowired
    private UserFriendsMapper userFriendsMapper;

    public void setUserFriendsMapper(UserFriendsMapper userFriendsMapper) {
        this.userFriendsMapper = userFriendsMapper;
    }

    /**
     * <p>此API用于添加好友</p>
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return 添加成功返回ture，失败返回false
     */
    @Override
    public boolean addFriend(int userId,int friendId) {
        int i = userFriendsMapper.addFriend(userId, friendId);
        int i1 = userFriendsMapper.addFriend(friendId, userId);
        return i>=1&&i1>=1;
    }

    /**
     * <p>此API用于获取好友列表</p>
     * @param userId 用户ID
     * @return 好友列表
     */
    @Override
    public List<User>getFriends(int userId){
        List<User> friends = userFriendsMapper.getUserFriendsById(userId);
        Set<User> set = new HashSet<>(friends);
        return new ArrayList<>(set);
    }

    /**
     * <p>此API用于删除好友</p>
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return 删除成功返回ture，失败返回false
     */
    @Override
    public boolean deleteFriend(int userId, int friendId) {
        int i = userFriendsMapper.deleteFriend(userId, friendId);
        return i>=1;
    }
}
