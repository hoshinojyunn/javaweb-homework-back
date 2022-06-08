package com.hoshino.service.registerService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Data:2022/06/04
 * Description:<p>此为注册模块的service</p>
 * <p>{@code RegisterServiceImpl}提供了用户注册，检查用户名的API</p>
 * <strong>用户注册API{@link #registerUser(User)}</strong>
 * <strong>检查用户名API{@link #checkUserName(String)}</strong>
 * @author xiesh
 * @version 1.4
 */
@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * <p>此API为项目的用户注册</p>
     * @param user 用户 Type:{@code User} {@link com.hoshino.pojo.User}
     * @return 注册成功返回true，失败返回false
     */
    @Override
    @Transactional
    public boolean registerUser(User user) {
        int i = 0;
        try {
            i = userMapper.addUser(user);
        } catch(Exception e) {
            System.out.println("注册错误");
            throw new RuntimeException();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return i >= 1;
    }

    /**
     * <p>此API为项目的用户名检查</p>
     * @param username 用户名
     * @return 如果用户名不为空且不重复返回true，否则返回false
     */
    @Override
    public boolean checkUserName(String username){
        String userName = userMapper.getUserName(username);
        return userName == null;
    }


}
