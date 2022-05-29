package com.hoshino.service.friendService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.User;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class friendServiceImpl implements friendService{
    @Override
    public boolean addFriend(int userId,int friendId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        int i = mapper.addFriend(userId, friendId);   //相互添加
        int i1 = mapper.addFriend(friendId, userId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1&&i1>=1;
    }
    @Override
    public List<User>getFriends(int userId){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        List<User> friends = mapper.getUserFriendsById(userId);
        Set<User> set = new HashSet<>(friends);
        sqlSession.close();
        return set.stream().toList();
    }

    @Override
    public boolean deleteFriend(int userId, int friendId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        int i = mapper.deleteFriend(userId, friendId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

}
