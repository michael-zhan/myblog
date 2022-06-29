package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.User;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.ref.ReferenceQueue;

@Controller
@RequestMapping("/friend")
public class HomeFriendController {

    @Autowired
    private UserService userService;

    /**
     * 显示好友模块首页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "friendIndex";
    }
    /**
     * 发送添加好友请求
     * @param friendId
     * @param session
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultVo makeFriend(String friendId, HttpSession session){
        User user=(User)session.getAttribute("user");

        User friend=userService.selectById(friendId);
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

}
