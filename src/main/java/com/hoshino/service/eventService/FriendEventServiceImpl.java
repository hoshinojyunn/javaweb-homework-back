package com.hoshino.service.eventService;

import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.User;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendEventServiceImpl implements FriendEventService{

    @Override
    public boolean sendFriendEvent(int from, int to, String message) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        int i = mapper.sendFriendEvent(from, to, message);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Override
    public List<FriendEvent> getFriendEvent(int from, int to) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        List<FriendEvent> friendEvent = mapper.getFriendEvent(from, to);
        sqlSession.close();
        return friendEvent;
    }
}
