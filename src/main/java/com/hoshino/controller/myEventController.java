package com.hoshino.controller;


import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.GroupEvent;
import com.hoshino.pojo.UserEvent;
import com.hoshino.service.eventService.FriendEventServiceImpl;
import com.hoshino.service.eventService.GroupEventServiceImpl;
import com.hoshino.service.eventService.UserEventServiceImpl;
import com.hoshino.util.JsonUtil;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@RestController
public class myEventController {
    @RequestMapping(value = "/myEvent",method = RequestMethod.GET)
    public String pushEvent(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session);
        UserEventServiceImpl userEventService = new UserEventServiceImpl();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            System.out.println(userId);
            return JsonUtil.getJson(null);
        }else {

            List<UserEvent> events = userEventService.getUserEvents(userId);

            return JsonUtil.getJson(events);
        }
    }
    @RequestMapping("/addMyEvent")
    public String addMyEvent(HttpSession session,
                             @RequestParam("eventName")String eventName,
                             @RequestParam("startTime")@DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,
                             @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime,
                             @RequestParam("eventDescription")String eventDescription){
        Integer userId = (Integer)session.getAttribute("userId");
        UserEventServiceImpl userEventService = new UserEventServiceImpl();
        boolean b = userEventService.addUserEvent(new UserEvent(userId, eventName, startTime, endTime,eventDescription));
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/deleteUserEvent")
    public String deleteUserEvent(@RequestParam("eventId")int eventId){
        UserEventServiceImpl userEventService = new UserEventServiceImpl();
        boolean b = userEventService.deleteUserEvent(eventId);
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/getGroupMessage")
    public String getGroupMessage(@RequestParam("groupId")Integer groupId){
        GroupEventServiceImpl groupEventService = new GroupEventServiceImpl();
        List<GroupEvent> groupEvents = groupEventService.getGroupEvents(groupId);
        return JsonUtil.getJson(groupEvents);
    }
    @RequestMapping("/getFriendMessage")
    public String getFriendMessage(HttpSession session,
                                   @RequestParam("friendId")Integer friendId){
        Integer userId = (Integer)session.getAttribute("userId");
        FriendEventServiceImpl friendEventService = new FriendEventServiceImpl();
        List<FriendEvent> friendEvent = friendEventService.getFriendEvent(friendId, userId);
        return JsonUtil.getJson(friendEvent);
    }
}
