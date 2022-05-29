package com.hoshino.service.userService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService{
    @Override
    public User selectUser(String username) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser(username);
    }

    @Override
    public boolean resetPassword(int userId, String password) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        int i = sqlSession.getMapper(UserMapper.class).resetPassword(userId, password);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }
}
