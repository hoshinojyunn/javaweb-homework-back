package com.hoshino.controller;


import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.service.personalSettingService.personalSettingServiceImpl;
import com.hoshino.service.registerService.RegisterServiceImpl;
import com.hoshino.service.userService.UserServiceImpl;
import com.hoshino.util.JsonUtil;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class RegisterController {
    @RequestMapping("/checkUserName")
    public String checkUserName(String username){
        if(username.equals(""))
            return JsonUtil.getJson(false);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String userName = mapper.getUserName(username);
        if(userName == null){
            return JsonUtil.getJson(true);
        }else{
            return JsonUtil.getJson(false);
        }
    }
    @RequestMapping( "/register")
    public String register(HttpSession session, @RequestParam("username")String username,
                           @RequestParam("password")String password) {
        RegisterServiceImpl registerService = new RegisterServiceImpl();
        // 注册
        boolean b = registerService.registerUser(new User(username, password));
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.selectUser(username);
        // 初始化用户设置
        personalSettingServiceImpl personalSettingService = new personalSettingServiceImpl();
        boolean b1 = personalSettingService.defaultSettings(user.getId());
        return JsonUtil.getJson(b&&b1);
    }

}
