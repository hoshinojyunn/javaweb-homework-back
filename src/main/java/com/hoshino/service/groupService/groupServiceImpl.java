package com.hoshino.service.groupService;

import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.pojo.Group;
import com.hoshino.service.loginService.LoginServiceImpl;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class groupServiceImpl implements groupService{
    @Autowired
    private GroupMapper groupMapper;

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }


    @Override
    public boolean joinGroup(int groupId, int userId) {
        int i = groupMapper.groupAddUser(groupId, userId);
        return i>=1;
    }

    @Override
    public List<Group> getUserGroups(int id) {
        return groupMapper.getUserGroups(id);
    }

    @Override
    public boolean leaveGroup(int groupId, int userId) {
        int i = groupMapper.groupDeleteUser(groupId, userId);
        return i>=1;
    }

    @Override
    public boolean sendGroupMessage(int groupId, Date startTime, Date endTime, String eventName, String eventDescription) {
        int i = groupMapper.sendGroupMessage(groupId, startTime, endTime, eventName, eventDescription);
        return i>=1;
    }

    @Override
    public boolean deleteGroupEvent(int groupId,int eventId) {
        int i = groupMapper.deleteGroupEvent(groupId,eventId);
        return i>=1;
    }


}
