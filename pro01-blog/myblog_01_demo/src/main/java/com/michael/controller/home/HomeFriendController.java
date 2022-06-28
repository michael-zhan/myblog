package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.User;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/friend")
public class HomeFriendController {

    @Autowired
    private UserService userService;


    @RequestMapping("/add")
    @ResponseBody
    public ResultVo addFriend(@RequestParam("friendId") String friendId, HttpSession session){
        User user=(User)session.getAttribute("user");

        User friend=userService.selectById(friendId);
        if(friend==null){//说明所查询的用户不存在
            System.out.println("查询的用户不存在");
            return new ResultVo("fail to add");
        }else{
            System.out.println("添加成功");
            return new ResultVo("success to add");
        }
    }

}
