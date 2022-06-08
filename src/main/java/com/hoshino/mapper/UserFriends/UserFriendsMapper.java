package com.hoshino.mapper.UserFriends;

import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Date:2022.6.5
 * description:<p>此为用户好友功能的Mapper</p>
 * <p>{@code GroupMapper}提供了用户好友相关服务的数据库操作的API</p>
 *  <p><strong>通过ID获取用户好友的API{@link #getUserFriendsById(int)}</strong></p>
 *  <p> <strong>通过好友ID添加好友的API{@link #addFriend(int, int)}</strong></p>
 * <p> <strong>获取好友事件的API{@link #getFriendEvent(int, int)}</strong></p>
 *  <p><strong>发送好友事件的API{@link #sendFriendEvent(int, int, String)}</strong></p>
 * <p><strong>删除好友的API{@link #deleteFriend(int, int)}</strong></p>
 *   @author GouHang
 *   @version 1.4
 */
@Repository
public interface UserFriendsMapper {

    public List<User> getUserFriendsById(@Param("uid") int id);

    public int addFriend(@Param("uid")int uid, @Param("friendId")int friendId);

    public List<FriendEvent> getFriendEvent(@Param("from")int from,@Param("to")int to);

    public int sendFriendEvent(@Param("from")int from,@Param("to")int to,@Param("message")String message);

    public int deleteFriend(@Param("uid")int userId,@Param("friendId")int friendId);
}
