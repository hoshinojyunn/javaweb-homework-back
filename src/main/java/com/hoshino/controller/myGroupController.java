package com.hoshino.controller;


import com.hoshino.pojo.Group;
import com.hoshino.pojo.User;
import com.hoshino.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hoshino.service.groupService.groupServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
/**
 * Date: 2022/06/03
 * Description: <p>此为我的群组模块的Controller</p>
 * <p>{@code myFriendsController}提供了加入群组、获取群组、离开群组、发送群讯息、删除群事件的API</p>
 * <strong>加入群组API{@link #joinGroup(HttpServletRequest, String)}</strong>
 * <strong>获取群组API{@link #getGroups(HttpServletRequest)}</strong>
 * <strong>离开群组API{@link #sendGroupMessage(HttpSession, String, String, Date, Date, String)}</strong>
 * <strong>发送群讯息API{@link #sendGroupMessage(HttpSession, String, String, Date, Date, String)}</strong>
 * <strong>删除群组API{@link #deleteGroupEvent(int, int)}</strong>
 * @author mk
 * @version 1.4
 */
@RestController
public class myGroupController {
    @Autowired
    @Qualifier("groupServiceImpl")
    private groupServiceImpl groupService;

    /**
     * <p>此为加入群组的API</p>
     * @param request 网页请求
     * @param groupId 群组ID
     * @return True:加入群组成功  False:加入群组失败
     */
    @RequestMapping("/joinGroup")
    public String joinGroup(HttpServletRequest request,
                            @RequestParam("groupId")String groupId){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        boolean b = groupService.joinGroup(Integer.parseInt(groupId), userId);
        return JsonUtil.getJson(b);
    }
    /**
     * <p>此为获取群组的API</p>
     * @param request 网页请求
     * @return 返回所有的群列表
     */
    @RequestMapping("/getGroups")
    public String getGroups(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute(User.USER_SESSION);
        if(userId==null){
            return JsonUtil.getJson(null);
        }else{
            List<Group> userGroups = groupService.getUserGroups(userId);
            return JsonUtil.getJson(userGroups);
        }
    }
    /**
     * <p>此为离开群组的API</p>
     * @param session 用户session
     * @param groupId 群组ID
     * @return True：离开群组成功 False：离开群组失败
     */
    @RequestMapping("/leaveGroup")
    public String leaveGroup(HttpSession session,@RequestParam("groupId") String groupId){
        Integer userId = (Integer) session.getAttribute(User.USER_SESSION);
        boolean b = groupService.leaveGroup(Integer.parseInt(groupId), userId);
        return JsonUtil.getJson(b);
    }
    /**
     * <p>此为发送群讯息的API</p>
     * @param session 用户session
     * @param groupId 群组ID
     * @param eventName 事件名称
     * @param startTime 事件起始日期
     * @param endTime 事件截止日期
     * @param eventDescription 事件描述
     * @return True：发送成功  False：发送失败
     */
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

    /**
     * <p>此为删除群组事件的API</p>
     * @param groupId 群组ID
     * @param eventId 事件ID
     * @return True：删除成功  False：删除失败
     */
    @RequestMapping("/deleteGroupEvent")
    public String deleteGroupEvent(@RequestParam("groupId")int groupId,
                                   @RequestParam("eventId")int eventId){
        boolean b = groupService.deleteGroupEvent(groupId,eventId);
        return JsonUtil.getJson(b);
    }

    /**
     * <p>此为创建群组的API</p>
     * @param session 会话
     * @param groupName 群组名称
     * @return 创建成功并添加创建者返回字符串ture，失败返回字符串false
     */
    @RequestMapping("/createGroup")
    public String createGroup(HttpSession session,
                              @RequestParam("groupName")String groupName){
        boolean group = groupService.createGroup(groupName);
        boolean b = groupService.joinGroup(groupService.selectGroup(groupName).getGroupId(),
                (Integer) session.getAttribute(User.USER_SESSION));
        return JsonUtil.getJson(group&&b);
    }
}
