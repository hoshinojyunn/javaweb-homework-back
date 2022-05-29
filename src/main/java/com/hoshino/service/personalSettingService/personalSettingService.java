package com.hoshino.service.personalSettingService;

public interface personalSettingService {
    public boolean setAvatar(int userId,String avatarUrl);

    public boolean defaultSettings(int userId);
}
