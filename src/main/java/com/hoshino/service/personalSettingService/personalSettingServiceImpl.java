package com.hoshino.service.personalSettingService;

import com.hoshino.mapper.User.UserMapper;

import com.hoshino.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Data:2022/06/04
 * Description:<p>此为个人信息设置的service</p>
 * <p>{@code personalSettingServiceImpl}提供了的设置头像，默认设置的API</p>
 * <strong>设置头像API{@link #setAvatar(int, String)}</strong>
 * <strong>默认设置API{@link #defaultSettings(int)}</strong>
 * @author xiesh
 * @version 1.4
 */
@Service
public class personalSettingServiceImpl implements personalSettingService{

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * <p>此API为项目的设置头像</p>
     * @param userId 用户ID
     * @param avatarUrl 用户头像
     * @return 设置成功返回true，失败返回false
     */
    @Override
    public boolean setAvatar(int userId,String avatarUrl) {
        int i = userMapper.setUserAvatar(userId, avatarUrl);
        return i>=1;
    }

    /**
     * <p>此API为项目的默认设置,设置用户ID</p>
     * @param userId 用户ID
     * @return 设置成功返回true，失败返回false
     */
    @Override
    public boolean defaultSettings(int userId) {
        int i = userMapper.defaultPersonalSettings(userId);
        return i>=1;
    }

}
