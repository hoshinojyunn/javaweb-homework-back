package com.hoshino.service.personalSettingService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class personalSettingServiceImpl implements personalSettingService{

    @Override
    public boolean setAvatar(int userId,String avatarUrl) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.setUserAvatar(userId, avatarUrl);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Override
    public boolean defaultSettings(int userId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.defaultPersonalSettings(userId);
        sqlSession.commit();
        sqlSession.close();
        return i>=1;
    }

    @Test
    public void test(){
        personalSettingServiceImpl personalSettingService = new personalSettingServiceImpl();
        System.out.println(personalSettingService.setAvatar(3, "4946116"));
    }
}
