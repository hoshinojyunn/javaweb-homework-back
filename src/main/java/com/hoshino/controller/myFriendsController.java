package com.hoshino.controller;


import com.hoshino.pojo.User;
import com.hoshino.pojo.UserEvent;
import com.hoshino.service.eventService.FriendEventServiceImpl;
import com.hoshino.service.eventService.UserEventService;
import com.hoshino.service.eventService.UserEventServiceImpl;
import com.hoshino.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.hoshino.service.friendService.friendServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * Date: 2022/06/03
 * Description: <p>此为我的好友模块的Controller</p>
 * <p>{@code myFriendsController}提供了添加好友、获取好友、给好友发送讯息、删除好友的API</p>
 * <strong>添加好友API{@link #addFriend(HttpServletRequest, String)}</strong>
 * <strong>获取好友API{@link #getFriends(HttpServletRequest)}</strong>
 * <strong>给好友发送讯息API{@link #sendMessageToFriend(HttpSession, int, String)}</strong>
 * <strong>删除好友API{@link #deleteFriend(HttpSession, int)}</strong>
 * @author mk
 * @version 1.4
 */
@RestController
public class myFriendsController {
    /**
     * {@code friendService}是好友的Service层{@link com.hoshino.service.friendService.friendService}
     * {@code friendEventService}是好友事件的Service层{@link com.hoshino.service.eventService.FriendEventService}
     */
    @Autowired
    @Qualifier("friendServiceImpl")
    private friendServiceImpl friendService;
    @Autowired
    @Qualifier("friendEventServiceImpl")
    private FriendEventServiceImpl friendEventService;
    @Autowired
    @Qualifier("userEventServiceImpl")
    private UserEventService userEventService;


    /**
     * <p>此为添加好友的API</p>
     * @param request 网页请求
     * @param friendId 好友ID
     * @return True:添加成功 False:添加失败
     */
    @RequestMapping("/addFriend")
    public String addFriend(HttpServletRequest request,
                             @RequestParam("friendId")String friendId){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        // 排除自身
        if(userId.equals(Integer.valueOf(friendId)))
            return JsonUtil.getJson(false);
        boolean b = friendService.addFriend(userId, Integer.parseInt(friendId));
        return JsonUtil.getJson(b);
    }


    /**
     * <p>此为获取好友的API</p>
     * @param request 网页请求
     * @return 返回所有好友
     */
    @RequestMapping(value = "/getFriends")
    public String getFriends(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);

        List<User> friends = friendService.getFriends(userId);
        return JsonUtil.getJson(friends);
    }


     /**
     * <p>此为为好友发送讯息的API</p>
     * @param session 用户session
     * @param to 接收消息的人
     * @param message 消息的内容
     * @return True:发送成功 False:发送失败
     */   
    @RequestMapping("/sendMessageToFriend")
    public String sendMessageToFriend(HttpSession session,
                                      @RequestParam("friendId")int to,
                                      @RequestParam("message")String message){
        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        boolean b = friendEventService.sendFriendEvent(userId, to, message);
        return JsonUtil.getJson(b);
    }


    /**
     * <p>此为删除好友的API</p>
     * @param session 用户session
     * @param friendId 好友ID
     * @return true:互删成功 false:互删失败
     */
    @RequestMapping("/deleteFriend")
    public String deleteFriend(HttpSession session,@RequestParam("friendId")int friendId){
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        // 互删
        boolean b = friendService.deleteFriend(userId, friendId);
        boolean b1 = friendService.deleteFriend(friendId, userId);
        return JsonUtil.getJson(b&&b1);
    }


    @RequestMapping("/sendFriendEvent")
    public String sendFriendEvent(@RequestParam("eventName")String eventName,
                                  @RequestParam("startTime")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                  @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime,
                                  @RequestParam("friendId")int friendId,
                                  @RequestParam("eventDescription")String eventDescription){
        boolean b = userEventService.addUserEvent(new UserEvent(friendId,
                eventName,startTime,endTime,eventDescription));
        return JsonUtil.getJson(b);
    }
}
