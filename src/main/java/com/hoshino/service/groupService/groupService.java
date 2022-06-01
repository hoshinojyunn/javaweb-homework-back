package com.hoshino.service.groupService;

import com.hoshino.pojo.Group;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface groupService {
    public boolean joinGroup(int groupId,int userId);

    public List<Group> getUserGroups(int id);

    public boolean leaveGroup(int groupId,int userId);

    public boolean sendGroupMessage(int groupId, Date startTime, Date endTime, String eventName, String eventDescription);

    public boolean deleteGroupEvent(int groupId,int eventId);
}
