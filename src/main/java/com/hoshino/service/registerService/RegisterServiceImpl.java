package com.hoshino.service.registerService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;

import com.hoshino.util.JsonUtil;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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

    @Override
    public boolean checkUserName(String username){
        String userName = userMapper.getUserName(username);
        return userName == null;
    }


}
