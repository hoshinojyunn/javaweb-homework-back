package com.hoshino.mapper.Group;

import com.hoshino.pojo.Group;
import com.hoshino.pojo.GroupEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface GroupMapper {

    public int groupAddUser(@Param("groupId")int groupId, @Param("uid")int userId);

    public List<Group> getUserGroups(@Param("uid")int id);

    public int groupDeleteUser(@Param("groupId")int groupId, @Param("uid")int userId);

    public int sendGroupMessage(@Param("groupId")int groupId,
                                @Param("startTime") Date startTime,
                                @Param("endTime")Date endTime,
                                @Param("eventName")String eventName,
                                @Param("eventDescription")String description);

    public List<GroupEvent> getGroupEvents(@Param("groupId")int groupId);

    public int deleteGroupEvent(@Param("groupId")int groupId,@Param("eventId")int eventId);
}
