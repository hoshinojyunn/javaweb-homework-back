package com.hoshino.controller;


import com.hoshino.pojo.FriendEvent;
import com.hoshino.pojo.GroupEvent;
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
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@RestController
public class myEventController {
    @Autowired
    @Qualifier("userEventServiceImpl")
    private UserEventService userEventService;
    @Autowired
    @Qualifier("groupEventServiceImpl")
    private GroupEventService groupEventService;
    @Autowired
    @Qualifier("friendEventServiceImpl")
    private FriendEventService friendEventService;

    @RequestMapping(value = "/myEvent",method = RequestMethod.GET)
    public String pushEvent(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session);
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
        startTime.setDate(startTime.getDate()+1);
        endTime.setDate(endTime.getDate()+1);

        Integer userId = (Integer)session.getAttribute("userId");
        boolean b = userEventService.addUserEvent(new UserEvent(userId, eventName, startTime, endTime,eventDescription));
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/deleteUserEvent")
    public String deleteUserEvent(@RequestParam("eventId")int eventId){
        boolean b = userEventService.deleteUserEvent(eventId);
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/getGroupMessage")
    public String getGroupMessage(@RequestParam("groupId")Integer groupId){
        List<GroupEvent> groupEvents = groupEventService.getGroupEvents(groupId);
        return JsonUtil.getJson(groupEvents);
    }
    @RequestMapping("/getFriendMessage")
    public String getFriendMessage(HttpSession session,
                                   @RequestParam("friendId")Integer friendId){
        Integer userId = (Integer)session.getAttribute("userId");
        List<FriendEvent> friendEvent = friendEventService.getFriendEvent(friendId, userId);
        return JsonUtil.getJson(friendEvent);
    }
}
