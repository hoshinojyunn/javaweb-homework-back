package com.hoshino.controller;



import com.hoshino.pojo.User;
import com.hoshino.service.loginService.LoginService;

import com.hoshino.util.JsonUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Date: 2022/06/02
 * Description: <p>此为登录模块的Controller</p>
 * <p>{@code LoginController}提供了登录检查，一般操作检查以及用户头像获取的API</p>
 * <strong>登录检查API{@link #loginCheck(HttpServletRequest, User)}</strong>
 * <strong>一般操作检查API{@link #check(HttpSession)}</strong>
 * <strong>用户头像获取API{@link #getAvatar(HttpSession)}</strong>
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


    /**
     * <p>此API为项目的登录检查</p>
     * @param request 网页请求
     * @param user Type:{@code User} {@link com.hoshino.pojo.User}
     * @return 如果检查正确则返回用户信息，否则返回null
     */
    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String loginCheck(HttpServletRequest request,
                             User user) {

        HttpSession session = request.getSession();
        // 检查登录信息
        Integer ID = loginService.CheckLoginMessage(user);

        if(ID!=null){
            session.setAttribute(User.USER_SESSION,ID);
            System.out.println(session);
            return JsonUtil.getJson(user);
        }else{
            session.setAttribute(User.USER_SESSION,null);
            return JsonUtil.getJson(null);
        }
    }



    /**
     * <p>此为一般操作检查的API，用户进行一般动作前都会请求此API，以检查用户是否有权限做这些动作</p>
     * @param session 用户session
     * @return true:如果检查通过 false: 不通过
     */
    @RequestMapping("/check")
    public String check(HttpSession session){
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        if(userId!=null){
            return JsonUtil.getJson(true);
        }else{
            return JsonUtil.getJson(false);
        }
    }



    /**
     * 此API返回用户头像在项目下的路径，<code>一般需要在项目路径下添加一个默认头像，否则新注册用户头像无法显示</code>
     * @param session 用户session
     * @return 用户头像路径
     */
    @GetMapping("/getAvatar")
    public String getAvatar(HttpSession session){
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        System.out.println(userId);
        String userAvatarUrl = loginService.getUserAvatarUrl(userId);
        return JsonUtil.getJson(userAvatarUrl);
    }
}
