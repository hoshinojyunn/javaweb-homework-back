package com.hoshino.service.loginService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Data:2022/06/04
 * Description:<p>此为登录模块的service</p>
 * <p>{@code LoginServiceImpl}提供了登录检查，获取用户头像的API</p>
 * <p><strong>登录检查API{@link #CheckLoginMessage(User)}</strong></p>
 * <p><strong>获取用户头像API{@link #getUserAvatarUrl(int)}</strong></p>
 * @author xiesh
 * @version 1.4
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * <P>此API为项目的登录检查</P>
     * @param user 用户 Type:{@code User} {@link com.hoshino.pojo.User}
     * @return 如果检查正确则返回用户ID，否则返回null
     */
    @Override
    public Integer CheckLoginMessage(User user) {
        User temp = userMapper.selectUser(user.getUsername());
        if(temp!=null&&temp.getPassword().equals(user.getPassword())){
            return temp.getId();
        }else{
            return null;
        }
    }

    /**
     * <p>此API为项目的获取用户头像</p>
     * @param userId 用户ID
     * @return 返回用户的头像
     */
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
