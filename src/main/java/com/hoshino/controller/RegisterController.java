package com.hoshino.controller;



import com.hoshino.pojo.User;
import com.hoshino.service.personalSettingService.personalSettingServiceImpl;
import com.hoshino.service.registerService.RegisterService;

import com.hoshino.service.userService.UserService;

import com.hoshino.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;

@RestController
public class RegisterController {
    @Autowired
    @Qualifier("registerServiceImpl")
    private RegisterService registerService;
    @Autowired
    @Qualifier("personalSettingServiceImpl")
    private personalSettingServiceImpl personalSettingService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/checkUserName")
    public String checkUserName(String username){
        if(username.equals(""))
            return JsonUtil.getJson(false);
        boolean b = registerService.checkUserName(username);
        return JsonUtil.getJson(b);
    }
    @RequestMapping( "/register")
    public String register(HttpSession session, @RequestParam("username")String username,
                           @RequestParam("password")String password) {
        // 注册
        boolean b = registerService.registerUser(new User(username, password));

        User user = userService.selectUser(username);
        // 初始化用户设置
        boolean b1 = personalSettingService.defaultSettings(user.getId());
        return JsonUtil.getJson(b&&b1);
    }

}
