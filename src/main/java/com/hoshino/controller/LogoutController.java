package com.hoshino.controller;


import com.hoshino.util.JsonUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController

public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("userId")!=null){
            session.removeAttribute("userId");
            return JsonUtil.getJson(true);
        }else{
            return JsonUtil.getJson(false);
        }
    }
}
