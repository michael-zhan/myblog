package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.Notice;
import com.michael.pojo.User;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.ref.ReferenceQueue;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class HomeFriendController {

    @Autowired
    private UserService userService;
    /**
     * 显示好友模块首页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");

        List<Notice> noticeList = userService.getNoticeList(user.getId());
        List<User> userList=userService.getFriendList(user.getId());

        model.addAttribute("userList",userList);
        model.addAttribute("noticeList",noticeList);

        return "friendIndex";

    }
    /**
     * 发送添加好友请求
     * @param friendId
     * @param session
     * @return
     */
    @RequestMapping(value = "/add/{friendId}")
    @ResponseBody
    public ResultVo makeFriend(@PathVariable String friendId, HttpSession session){
        User user=(User)session.getAttribute("user");

        User friend=userService.getById(friendId);
        if(friend==null){//说明所查询的用户不存在
            System.out.println("查询的用户不存在");
            return new ResultVo("查询的用户不存在");
        }else{
            Integer result = userService.sendMakeFriendRequest(user.getId(), friend.getId());
            if(result==1) {
                return new ResultVo("成功发送请求");
            }else if(result==0){
                return new ResultVo("你们已经是好友");
            }
        }
        return new ResultVo("没想好是什么情况");
    }

    /**
     * 处理好友请求
     * @param noticeId
     * @param sign
     * @return
     */
    @RequestMapping(value="/deal/{noticeId}/",method= RequestMethod.PUT)
    public ResultVo dealRequest(@PathVariable("noticeId") Integer noticeId,@RequestParam("sign") Integer sign){
        userService.dealWithFriendRequest(noticeId,sign);
        return new ResultVo("已处理");
    }

    /**
     * 处理进入好友空间
     * @param friendId
     * @param model
     * @return
     */
    @RequestMapping(value="/browseFriendRoom/{friendId}")
    public String browseFriendRoom(@PathVariable String friendId,Model model){
        User friend = userService.getById(friendId);
        model.addAttribute("friend",friend);
        return "friendHomeIndex";
    }

    @RequestMapping("/remove/{friendId}")
    public String removeFriend(@PathVariable String friendId,HttpSession session,Model model){

        userService.removeFriend(friendId);

        User user=(User)session.getAttribute("user");
        List<Notice> noticeList = userService.getNoticeList(user.getId());
        List<User> friendList = userService.getFriendList(user.getId());
        model.addAttribute("noticeList",noticeList);
        model.addAttribute("friendList",friendList);

        return "friendIndex";
    }



}
