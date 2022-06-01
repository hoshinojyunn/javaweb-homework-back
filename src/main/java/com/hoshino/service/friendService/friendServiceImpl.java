package com.hoshino.service.friendService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.User;
import com.hoshino.service.loginService.LoginServiceImpl;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class friendServiceImpl implements friendService{
    @Autowired
    private UserFriendsMapper userFriendsMapper;

    public void setUserFriendsMapper(UserFriendsMapper userFriendsMapper) {
        this.userFriendsMapper = userFriendsMapper;
    }

    @Override
    public boolean addFriend(int userId,int friendId) {
        int i = userFriendsMapper.addFriend(userId, friendId);
        int i1 = userFriendsMapper.addFriend(friendId, userId);
        return i>=1&&i1>=1;
    }
    @Override
    public List<User>getFriends(int userId){
        List<User> friends = userFriendsMapper.getUserFriendsById(userId);
        Set<User> set = new HashSet<>(friends);
        return set.stream().toList();
    }

    @Override
    public boolean deleteFriend(int userId, int friendId) {
        int i = userFriendsMapper.deleteFriend(userId, friendId);

        return i>=1;
    }
}
