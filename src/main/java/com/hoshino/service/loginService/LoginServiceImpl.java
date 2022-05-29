package com.hoshino.service.loginService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class LoginServiceImpl implements LoginService{
    @Override
    public User CheckLoginMessage(String username,String password) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser(username);

        sqlSession.close();
        if(user!=null&&password.equals(user.getPassword())){
            return user;
        }else{

            return null;
        }
    }

    @Override
    public String getUserAvatarUrl(int userId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String userAvatar = mapper.getUserAvatar(userId);
        sqlSession.close();
        return userAvatar;
    }

    public User getUserById(int id){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User res = mapper.getUserById(id);
        sqlSession.close();
        return res;
    }

    @Test
    public void test(){
        LoginServiceImpl loginService = new LoginServiceImpl();

        String url = loginService.getUserAvatarUrl(3);
//        User user = loginService.getUserById(1);
        System.out.println(url);


    }
}
