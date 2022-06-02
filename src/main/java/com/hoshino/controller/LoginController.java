package com.hoshino.controller;



import com.hoshino.pojo.User;
import com.hoshino.service.loginService.LoginService;
import com.hoshino.service.loginService.LoginServiceImpl;
import com.hoshino.util.JsonUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>此为登录模块的Controller</p>
 * <p>{@code LoginController}提供了登录检查，一般操作检查以及用户头像获取的API</>
 * @author hoshino
 * @version 1.4
 */
@RestController
public class LoginController {
    /**
     * {@code loginService}是登录模块的Service层
     * @see com.hoshino.service.loginService.LoginService
     */
    @Autowired
    @Qualifier("loginServiceImpl")
    private LoginService loginService;

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String loginCheck(HttpServletRequest request,
                             @RequestParam("username")String username,
                             @RequestParam("password")String password) {

        HttpSession session = request.getSession();
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
        String userAvatarUrl = loginService.getUserAvatarUrl(userId);
        return JsonUtil.getJson(userAvatarUrl);
    }
}
