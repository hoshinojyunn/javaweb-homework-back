package com.hoshino.mapper.Group;

import com.hoshino.pojo.Group;
import com.hoshino.pojo.GroupEvent;
import com.hoshino.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Date:2022.6.6
 * description:<p>此为群组相关功能的Mapper</p>
 * <p>{@code GroupMapper}提供了群组相关服务的数据库操作的API</p>
 * <p> <strong>通过用户ID获取群组列表的API{@link #getUserGroups(int)}}</strong></p>
 *  <p> <strong>添加群组成员的API{@link #groupAddUser(int, int)}}</strong></p>
 *  <p> <strong>删除群组成员的API{@link #groupDeleteUser(int, int)} }</strong></p>
 *  <p> <strong>发送群组信息的API{@link #sendGroupMessage(int, Date, Date, String, String)}</strong></p>
 *  <p> <strong>获取群组事件列表的API{@link #getGroupEvents(int)}</strong></p>
 *  <p> <strong> 删除群组事件的API{@link #deleteGroupEvent(int, int)}</strong></p>
 *  <p> <strong>选择群组的API{@link #selectGroup(String)}</strong></p>
 *  <p> <strong>创建群组的API{@link #createGroup(String)}</strong></p>
 *   @author gouhang
 *   @version 1.4
 */
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

    public Group selectGroup(@Param("groupName")String groupName);

    public int createGroup(@Param("groupName")String groupName);
}
