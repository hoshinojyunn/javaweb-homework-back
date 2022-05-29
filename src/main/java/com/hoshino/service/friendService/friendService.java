package com.hoshino.service.friendService;


import com.hoshino.pojo.User;

import java.util.List;

public interface friendService {
    public boolean addFriend(int userId,int friendId);

    public List<User> getFriends(int userId);

    public boolean deleteFriend(int userId,int friendId);
}
