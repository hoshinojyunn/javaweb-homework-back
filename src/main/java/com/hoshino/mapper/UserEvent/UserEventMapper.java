package com.hoshino.mapper.UserEvent;

import com.hoshino.pojo.UserEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserEventMapper {
    UserEvent getUserEventById(@Param("id") int id);

    List<UserEvent> getEventByUserId(@Param("userId") int userId);

    int addUserEvent(UserEvent event);

    int deleteUserEvent(@Param("eventId") int eventId);
}
