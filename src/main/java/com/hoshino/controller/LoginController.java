package com.hoshino.controller;



import com.hoshino.pojo.User;
import com.hoshino.service.loginService.LoginServiceImpl;
import com.hoshino.util.JsonUtil;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String loginCheck(HttpServletRequest request,
                             @RequestParam("username")String username,
                             @RequestParam("password")String password) {

        HttpSession session = request.getSession();
        LoginServiceImpl loginService = new LoginServiceImpl();
        // 检查登录信息
        User user = loginService.CheckLoginMessage(username, password);

        if(user!=null){
            session.setAttribute("userId",user.getId());
            System.out.println(session);
            return JsonUtil.getJson(user);
        }else{
            session.setAttribute("userId",null);
            return JsonUtil.getJson(null);
        }
    }
    @RequestMapping("/check")
    public String check(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId!=null){
            return JsonUtil.getJson(true);
        }else{
            return JsonUtil.getJson(false);
        }
    }
    @GetMapping("/getAvatar")
    public String getAvatar(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        LoginServiceImpl loginService = new LoginServiceImpl();
        String userAvatarUrl = loginService.getUserAvatarUrl(userId);
        return JsonUtil.getJson(userAvatarUrl);
    }
}
