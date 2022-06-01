package com.hoshino.service.personalSettingService;

import com.hoshino.mapper.User.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class personalSettingServiceImpl implements personalSettingService{

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean setAvatar(int userId,String avatarUrl) {
        int i = userMapper.setUserAvatar(userId, avatarUrl);
        return i>=1;
    }

    @Override
    public boolean defaultSettings(int userId) {
        int i = userMapper.defaultPersonalSettings(userId);
        return i>=1;
    }

}
