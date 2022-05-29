package com.hoshino.service.groupService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.Group;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class groupServiceImpl implements groupService{
    @Override
    public boolean joinGroup(int groupId, int userId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        int i = mapper.groupAddUser(groupId, userId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Override
    public List<Group> getUserGroups(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        List<Group> userGroups = mapper.getUserGroups(id);
        sqlSession.close();
        return userGroups;
    }

    @Override
    public boolean leaveGroup(int groupId, int userId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        int i = mapper.groupDeleteUser(groupId, userId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Override
    public boolean sendGroupMessage(int groupId, Date startTime, Date endTime, String eventName, String eventDescription) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        int i = mapper.sendGroupMessage(groupId, startTime, endTime, eventName,eventDescription);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

}
