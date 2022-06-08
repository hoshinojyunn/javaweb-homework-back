// TODO: 

package com.hoshino.controller;


import com.hoshino.pojo.User;
import com.hoshino.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
/**
 * Description: 登出的Controller
 * <strong>登出请求API{@link #logout(HttpServletRequest)}</strong>
 * @author hoshino
 * @version 1.4
 */
public class LogoutController {
    /**
     *
     * @param request 用户请求
     * @return ture: 登出成功 false: 登出失败
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(User.USER_SESSION)!=null){
            session.removeAttribute(User.USER_SESSION);
            return JsonUtil.getJson(true);
        }else{
            return JsonUtil.getJson(false);
        }
    }
}
