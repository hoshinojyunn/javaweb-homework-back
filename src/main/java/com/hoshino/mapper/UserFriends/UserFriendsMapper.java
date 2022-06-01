package com.hoshino.mapper.UserFriends;

import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserFriendsMapper {

    public List<User> getUserFriendsById(@Param("uid") int id);

    public int addFriend(@Param("uid")int uid, @Param("friendId")int friendId);

    public List<FriendEvent> getFriendEvent(@Param("from")int from,@Param("to")int to);

    public int sendFriendEvent(@Param("from")int from,@Param("to")int to,@Param("message")String message);

    public int deleteFriend(@Param("uid")int userId,@Param("friendId")int friendId);
}
