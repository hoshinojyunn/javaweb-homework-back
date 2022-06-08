package com.hoshino.service.userService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Data:2022/06/04
 * Description:<p>此为用户服务的service</p>
 * <p>提供了选择用户，重置密码的API</p>
 * <strong>选择用户API{@link #selectUser(String)}</strong>
 * <strong>重置密码API{@link #resetPassword(int, String)}</strong>
 * @author xiesh
 * @version 1.4
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * <p>此API为项目的用户选择</p>
     * @param username 用户名
     * @return 返回该用户名的用户
     */
    @Override
    public User selectUser(String username) {
        return userMapper.selectUser(username);
    }

    /**
     * <p>此API为项目的重置密码</p>
     * @param userId 用户ID
     * @param password 用户密码
     * @return 重置成功返回true，失败返回false
     */
    @Override
    public boolean resetPassword(int userId, String password) {
        return userMapper.resetPassword(userId,password)>=1;
    }
}
