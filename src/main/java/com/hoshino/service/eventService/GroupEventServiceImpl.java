package com.hoshino.service.eventService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.GroupEvent;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GroupEventServiceImpl implements GroupEventService{
    @Override
    public List<GroupEvent> getGroupEvents(int groupId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        List<GroupEvent> groupEvents = mapper.getGroupEvents(groupId);
        sqlSession.close();
        return groupEvents;
    }
}
