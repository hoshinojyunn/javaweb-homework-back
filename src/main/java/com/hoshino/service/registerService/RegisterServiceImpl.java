package com.hoshino.service.registerService;

import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.service.personalSettingService.personalSettingServiceImpl;
import com.hoshino.util.MD5Utils;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class RegisterServiceImpl implements RegisterService{
    @Override
    public boolean registerUser(User user) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        if(mapper.addUser(user)>=1){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
            return false;
        }
        return true;
    }
}
