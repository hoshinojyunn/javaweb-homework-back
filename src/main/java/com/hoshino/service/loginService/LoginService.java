package com.hoshino.service.loginService;


import com.hoshino.pojo.User;

public interface LoginService {
    // 检查用户名密码
    public Integer CheckLoginMessage(User user);

    public String getUserAvatarUrl(int userId);
}
