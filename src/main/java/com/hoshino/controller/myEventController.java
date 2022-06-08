package com.hoshino.controller;


import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.GroupEvent;
import com.hoshino.pojo.User;
import com.hoshino.pojo.UserEvent;
import com.hoshino.service.eventService.*;
import com.hoshino.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

/**
 * Date: 2022/06/03
 * Description: <p>此为个人事件模块的Controller</p>
 * <p>{@code myEventController}提供了推送事件、新增事件、删除事件、获取群消息、获取好友消息的API</p>
 * <strong>推送事件API{@link #pushEvent(HttpServletRequest)}</strong>
 * <strong>新增事件API{@link #addMyEvent(HttpSession, String, Date, Date, String)}</strong>
 * <strong>删除事件API{@link #deleteUserEvent(int)}</strong>
 * <strong>获取群消息API{@link #getGroupMessage(Integer)}</strong>
 * <strong>获取好友消息API{@link #getFriendMessage(HttpSession, Integer)}</strong>
 * @author mk
 * @version 1.4
 */
@RestController
public class myEventController {
    /**
     * {@code userEventService}是用户事件的Service层 {@link com.hoshino.service.eventService.UserEventService}
     * {@code groupEventService}是群组事件的Service层 {@link com.hoshino.service.eventService.GroupEventService}
     * {@code friendEventService}是好友事件的Service层 {@link com.hoshino.service.eventService.FriendEventService}
     */
    @Autowired
    @Qualifier("userEventServiceImpl")
    private UserEventService userEventService;
    @Autowired
    @Qualifier("groupEventServiceImpl")
    private GroupEventService groupEventService;
    @Autowired
    @Qualifier("friendEventServiceImpl")
    private FriendEventService friendEventService;


    /**
     * <p>此为推送事件的API</p>
     * @param request 网页请求
     * @return 如果检查正确则返回当前事件，否则返回null
     */
    @RequestMapping(value = "/myEvent",method = RequestMethod.GET)
    public String pushEvent(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session);
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        if(userId==null){
            System.out.println(userId);
            return JsonUtil.getJson(null);
        }else {

            List<UserEvent> events = userEventService.getUserEvents(userId);

            return JsonUtil.getJson(events);
        }
    }


     /**
     * <p>此为新增事件的API</p>
     * @param session 网页请求
     * @param eventName 事件名称 Type:{@code String} 
     * @param startTime 事件开始时间 Type:{@code Date} 
     * @param endTime 事件结束时间 Type:{@code Date} 
     * @param eventDescription 事件的描述 Type:{@code String} 
     * @return 如果成功新增事件，则返回true，否则返回false
     */
    @RequestMapping("/addMyEvent")
    public String addMyEvent(HttpSession session,
                             @RequestParam("eventName")String eventName,
                             @RequestParam("startTime")@DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,
                             @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime,
                             @RequestParam("eventDescription")String eventDescription){
        startTime.setDate(startTime.getDate()+1);
        endTime.setDate(endTime.getDate()+1);

        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        boolean b = userEventService.addUserEvent(new UserEvent(userId, eventName, startTime, endTime,eventDescription));
        return JsonUtil.getJson(b);
    }


    /**
     * <p>此为删除用户事件的API</p>
     * @param eventId 事件编号 Type:{@code int}
     * @return 事件的编号
     */
    @RequestMapping("/deleteUserEvent")
    public String deleteUserEvent(@RequestParam("eventId")int eventId){
        boolean b = userEventService.deleteUserEvent(eventId);
        return JsonUtil.getJson(b);
    }


    /**
     * <p>此为接收群组信息的API</p>
     * @param groupId 群组号 Type:{@code int} 
     * @return 群组事件
     */
    @RequestMapping("/getGroupMessage")
    public String getGroupMessage(@RequestParam("groupId")Integer groupId){
        List<GroupEvent> groupEvents = groupEventService.getGroupEvents(groupId);
        return JsonUtil.getJson(groupEvents);
    }


    /**
     * <p>此为接收好友信息的API</p>
     * @param session 网页请求
     * @param friendId 好友ID Type:{@code int} 
     * @return 好友的事件
     */
    @RequestMapping("/getFriendMessage")
    public String getFriendMessage(HttpSession session,
                                   @RequestParam("friendId")Integer friendId){
        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        List<FriendEvent> friendEvent = friendEventService.getFriendEvent(friendId, userId);
        return JsonUtil.getJson(friendEvent);
    }
}
