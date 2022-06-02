package com.hoshino.service.loginService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
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
    public Integer CheckLoginMessage(User user) {
        User temp = userMapper.selectUser(user.getUsername());
        if(temp!=null&&temp.getPassword().equals(user.getPassword())){
            return temp.getId();
        }else{
            return null;
        }
    }

    @Override
    public String getUserAvatarUrl(int userId) {
        return userMapper.getUserAvatar(userId);
    }



    @Deprecated
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
