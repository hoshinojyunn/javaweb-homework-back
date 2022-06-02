package com.hoshino.controller;


import com.hoshino.pojo.Group;
import com.hoshino.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hoshino.service.groupService.groupServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class myGroupController {
    @Autowired
    @Qualifier("groupServiceImpl")
    private groupServiceImpl groupService;

    @RequestMapping("/joinGroup")
    public String joinGroup(HttpServletRequest request,
                            @RequestParam("groupId")String groupId){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        boolean b = groupService.joinGroup(Integer.parseInt(groupId), userId);
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/getGroups")
    public String getGroups(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId==null){
            return JsonUtil.getJson(null);
        }else{
            List<Group> userGroups = groupService.getUserGroups(userId);
            return JsonUtil.getJson(userGroups);
        }
    }
    @RequestMapping("/leaveGroup")
    public String leaveGroup(HttpSession session,@RequestParam("groupId") String groupId){
        Integer userId = (Integer) session.getAttribute("userId");
        boolean b = groupService.leaveGroup(Integer.parseInt(groupId), userId);
        return JsonUtil.getJson(b);
    }
    @RequestMapping("/sendGroupMessage")
    public String sendGroupMessage(HttpSession session,
                                   @RequestParam("groupId")String groupId,
                                   @RequestParam("eventName")String eventName,
                                   @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                   @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
                                   @RequestParam("eventDescription")String eventDescription){
        boolean b = groupService.sendGroupMessage(Integer.parseInt(groupId), startTime, endTime, eventName, eventDescription);
        return JsonUtil.getJson(b);
    }

    @RequestMapping("/deleteGroupEvent")
    public String deleteGroupEvent(@RequestParam("groupId")int groupId,
                                   @RequestParam("eventId")int eventId){
        boolean b = groupService.deleteGroupEvent(groupId,eventId);
        return JsonUtil.getJson(b);
    }
}
