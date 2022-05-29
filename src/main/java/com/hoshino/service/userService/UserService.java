package com.hoshino.service.userService;

import com.hoshino.pojo.User;

public interface UserService {

    public User selectUser(String username);

    public boolean resetPassword(int userId, String password);
}
