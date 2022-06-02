package com.hoshino.service.userService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectUser(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public boolean resetPassword(int userId, String password) {
        return userMapper.resetPassword(userId,password)>=1;
    }
}
