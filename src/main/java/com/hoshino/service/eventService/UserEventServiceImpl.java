package com.hoshino.service.eventService;

import com.hoshino.mapper.UserEvent.UserEventMapper;
import com.hoshino.pojo.UserEvent;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserEventServiceImpl implements UserEventService{
    @Override
    public List<UserEvent> getUserEvents(int userId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserEventMapper mapper = sqlSession.getMapper(UserEventMapper.class);
        List<UserEvent> events = mapper.getEventByUserId(userId);
        sqlSession.close();
        return events;
    }

    @Override
    public boolean addUserEvent(UserEvent userEvent) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserEventMapper mapper = sqlSession.getMapper(UserEventMapper.class);
        int i = mapper.addUserEvent(userEvent);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Override
    public boolean deleteUserEvent(int eventId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserEventMapper mapper = sqlSession.getMapper(UserEventMapper.class);
        int i = mapper.deleteUserEvent(eventId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }
}
