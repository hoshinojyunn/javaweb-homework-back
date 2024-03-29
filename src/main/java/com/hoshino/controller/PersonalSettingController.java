package com.hoshino.controller;

import com.hoshino.pojo.User;
import com.hoshino.service.personalSettingService.personalSettingService;

import com.hoshino.service.userService.UserService;

import com.hoshino.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
public class PersonalSettingController {
    @Autowired
    @Qualifier("personalSettingServiceImpl")
    private personalSettingService personalSettingService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @PostMapping("/uploadAvatar")
    public String uploadAvatar(HttpSession session,
                               @RequestParam("avatar") MultipartFile avatar) {
        System.out.println(session);
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        System.out.println(userId);
        System.out.println(avatar.getOriginalFilename());
        // 文件储存包位置
        String path = session.getServletContext().getRealPath("avatar");
        System.out.println(path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        // 文件储存位置
        String finalPath = path + File.separator + userId+"-"+avatar.getOriginalFilename();
        try {
            avatar.transferTo(new File(finalPath));
            personalSettingService.setAvatar(userId,userId+"-"+avatar.getOriginalFilename());
            return JsonUtil.getJson(true);
        } catch (IOException e) {
            return JsonUtil.getJson(false);
        }
    }

    @RequestMapping("/resetPassword")
    public String resetPassword(HttpSession session,@RequestParam("password")String password){
        boolean userId = userService.resetPassword((Integer) session.getAttribute(User.USER_SESSION), password);
        return JsonUtil.getJson(userId);
    }
}
