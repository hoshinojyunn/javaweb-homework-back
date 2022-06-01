package com.hoshino.service.loginService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User CheckLoginMessage(String username,String password) {
        User user = userMapper.selectUser(username);
        if(user!=null&&password.equals(user.getPassword())){
            return user;
        }else{

            return null;
        }
    }

    @Override
    public String getUserAvatarUrl(int userId) {
        return userMapper.getUserAvatar(userId);
    }

    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LoginServiceImpl loginServiceImpl = context.getBean("loginServiceImpl", LoginServiceImpl.class);
        System.out.println(loginServiceImpl.getUserAvatarUrl(3));

    }
}
