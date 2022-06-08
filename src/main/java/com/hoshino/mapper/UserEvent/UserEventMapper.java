package com.hoshino.mapper.UserEvent;

import com.hoshino.pojo.UserEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Date:2022.6.5
 * description:<p>此为用户事件功能的Mapper</p>
 * <p>{@code GroupMapper}提供了用户事件相关服务的数据库操作的API</p>
 * <p><strong>通过事件ID获取事件的API{@link #getUserEventById(int)}</strong></p>
 * <p><strong>通过用户ID获取所有事件的API{@link #getUserEventById(int)}</strong></p>
 *  <p><strong>添加用户事件的API{@link #addUserEvent(UserEvent)}</strong></p>
 *  <p><strong>删除用户事件的API{@link #deleteUserEvent(int)}</strong></p>
 *   @author GouHang
 *   @version 1.4
 */
@Repository
public interface UserEventMapper {
    UserEvent getUserEventById(@Param("id") int id);

    List<UserEvent> getEventByUserId(@Param("userId") int userId);

    int addUserEvent(UserEvent event);

    int deleteUserEvent(@Param("eventId") int eventId);
}
