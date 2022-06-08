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


/**
 * Date: 2022/6/4
 * Description: <p>此为个人设置的Controller</p>
 * <p>{@code PersonalSettingController}提供了上传头像、重置密码的API</p>
 * <strong>上传头像{@link #uploadAvatar(HttpSession, MultipartFile)}</strong>
 * <strong>重置密码{@link #resetPassword(HttpSession, String)}</strong>
 * @author mk
 * @version 1.4
 */
@RestController
public class PersonalSettingController {
    /**
     * {@code personalSettingService}是个人设置的Service层{@link com.hoshino.service.personalSettingService.personalSettingService}
     * {@code userService}是用户的Service层{@link com.hoshino.service.userService.UserService}
     */
    @Autowired
    @Qualifier("personalSettingServiceImpl")
    private personalSettingService personalSettingService;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    /**
     * <p>此为上传头像的API</p>
     * @param session 用户session
     * @param avatar 用户头像
     * @return True：上传成功  False：上传失败
     */
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


    /**
     * <p>此为重置密码的API</p>
     * @param session 用session
     * @param password 密码
     * @return True:重置成功  False：重置失败
     */
    @RequestMapping("/resetPassword")
    public String resetPassword(HttpSession session,@RequestParam("password")String password){
        boolean userId = userService.resetPassword((Integer) session.getAttribute(User.USER_SESSION), password);
        session.removeAttribute(User.USER_SESSION);
        return JsonUtil.getJson(userId);
    }
}
