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

/**
 * Date:2022/06/04
 * Description:<p>此为注册模块的Controller</p>
 * <p>{@code RegisterController}提供了检查用户名、注册的API</p>
 * <strong>检查用户名API{@link #checkUserName(String)}</strong>
 * <strong>注册API{@link #register(HttpSession, String, String)}</strong>
 * @author mk
 * @version 1.4
 */
@RestController
public class RegisterController {
    /**
     * {@code registerService}是注册的Service层{@link com.hoshino.service.registerService.RegisterService}
     * {@code personalSettingService}是个人设置Service层{@link com.hoshino.service.personalSettingService.personalSettingService}
     * {@code userService}是用户的Service层{@link com.hoshino.service.userService.UserService}
     */
    @Autowired
    @Qualifier("registerServiceImpl")
    private RegisterService registerService;
    @Autowired
    @Qualifier("personalSettingServiceImpl")
    private personalSettingServiceImpl personalSettingService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    /**
     * <p>此为检查用户名的API</p>
     * @param username 用户名
     * @return True：检查成功  False:检查失败
     */
    @RequestMapping("/checkUserName")
    public String checkUserName(String username){
        if(username.equals(""))
            return JsonUtil.getJson(false);
        boolean b = registerService.checkUserName(username);
        return JsonUtil.getJson(b);
    }


    /**
     * <p>此为注册的API</p>
     * @param session 用户session
     * @param username 用户名
     * @param password 密码
     * @return True：注册成功  False：注册失败
     */
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
