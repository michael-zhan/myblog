package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Comment;
import com.michael.pojo.Notice;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.CommentService;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    /**
     * 进入好友管理界面
     * @return
     */
    @RequestMapping(value={"/index","/index/{friendPageIndex}/{noticePageIndex}"})
    public String index(Model model,HttpSession session,
                        @PathVariable(value = "friendPageIndex",required = false) String friendPageIndex,
                        @PathVariable(value = "requestPageIndex",required = false) String noticePageIndex){
        User user=(User)session.getAttribute("user");
        if(friendPageIndex==null)friendPageIndex="1";
        if(noticePageIndex==null)noticePageIndex="1";

        List<Notice> noticeListAll =null;
        List<User> friendListAll=null;
        if(user!=null) {
            noticeListAll=userService.getNoticeList(user.getId());
            friendListAll=userService.getFriendList(user.getId());
        }

        Integer fCount=friendListAll.size()/ EachPageCount.EACH_PAGE_COUNT_FRIEND+1;
        Integer nCount=noticeListAll.size()/EachPageCount.EACH_PAGE_COUNT_NOTICE+1;
        Integer f=Integer.parseInt(friendPageIndex);
        Integer n=Integer.parseInt(noticePageIndex);

//        List<Notice> noticeList =null;
//        List<User> friendList=null;


        model.addAttribute("friendPageCount",fCount);
        model.addAttribute("noticePageCount",nCount);
        model.addAttribute("friendPageIndex",f);
        model.addAttribute("noticePageIndex",n);
        model.addAttribute("friendList",friendListAll);
        model.addAttribute("noticeList",noticeListAll);
        return "friends";
    }

    /**
     * 发送添加好友请求
     * @param friendId
     * @param session
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultVo makeFriend(@RequestParam("friendId") Integer friendId, HttpSession session){
        User user=(User)session.getAttribute("user");

        User friend=userService.getUserById(friendId);
        if(friend==null){//说明所查询的用户不存在
            System.out.println("查询的用户不存在");
            return new ResultVo("查询的用户不存在");
        }else{
            Boolean result = userService.sendMakeFriendRequest(user.getId(), friend.getId());
            if(result==true) {
                return new ResultVo("成功发送请求");
            }else if(result==false){
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
    @RequestMapping(value="/deal/{noticeId}/{sign}",method= RequestMethod.PUT)
    public String dealRequest(@PathVariable("noticeId") Integer noticeId,@PathVariable("sign") Integer sign){
        userService.dealWithFriendRequest(noticeId,sign);
        return "redirect:/friend/index";
    }

    /**
     * 进入好友空间
     * @param friendId
     * @return
     */
    @RequestMapping(value="/browseFriendRoom/{friendId}")
    public String browseFriendRoom(@PathVariable Integer friendId,HttpSession session) {
        User friend = userService.getUserById(friendId);
        if (friend != null) {
            session.setAttribute("friend",friend);
            return "redirect:/friendRoom";
        }
        return "redirect:/friend/index";
    }

    /**
     * 删除好友
     * @param friendId
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/remove/{friendId}")
    public String removeFriend(@PathVariable Integer friendId,HttpSession session,Model model) {
        userService.removeFriend(friendId);
        return "redirect:/friend/index";
    }

}
