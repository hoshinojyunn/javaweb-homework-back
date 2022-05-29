package com.hoshino.controller;


import com.hoshino.pojo.User;
import com.hoshino.service.eventService.FriendEventServiceImpl;
import com.hoshino.util.JsonUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.hoshino.service.friendService.friendServiceImpl;

import java.util.List;

@RestController
public class myFriendsController {
    @RequestMapping("/addFriend")
    public String addFriend(HttpServletRequest request,
                             @RequestParam("friendId")String friendId){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        // 排除自身
        if(userId.equals(Integer.valueOf(friendId)))
            return JsonUtil.getJson(false);
        friendServiceImpl friendService = new friendServiceImpl();
        boolean b = friendService.addFriend(userId, Integer.parseInt(friendId));
        return JsonUtil.getJson(b);
    }
    @RequestMapping(value = "/getFriends")
    public String getFriends(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        friendServiceImpl friendService = new friendServiceImpl();

        List<User> friends = friendService.getFriends(userId);
        return JsonUtil.getJson(friends);
    }
    @RequestMapping("/sendMessageToFriend")
    public String sendMessageToFriend(HttpSession session,
                                      @RequestParam("friendId")int to,
                                      @RequestParam("message")String message){
        Integer userId = (Integer)session.getAttribute("userId");
        FriendEventServiceImpl friendEventService = new FriendEventServiceImpl();
        boolean b = friendEventService.sendFriendEvent(userId, to, message);
        return JsonUtil.getJson(b);
    }

    @RequestMapping("/deleteFriend")
    public String deleteFriend(HttpSession session,@RequestParam("friendId")int friendId){
        Integer userId = (Integer) session.getAttribute("userId");
        friendServiceImpl friendService = new friendServiceImpl();
        // 互删
        boolean b = friendService.deleteFriend(userId, friendId);
        boolean b1 = friendService.deleteFriend(friendId, userId);
        return JsonUtil.getJson(b&&b1);
    }
}
