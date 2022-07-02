package com.michael.controller.home;

import com.github.pagehelper.PageInfo;
import com.michael.model.dto.ResultVo;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.CommentService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeAboutMeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    /**
     * 进入关于个人界面
     * @param session
     * @return
     */
    @RequestMapping("/aboutme")
    public String index(HttpSession session){
        User user=(User)session.getAttribute("user");
//        if(user!=null&&user.getId()!=null){
//
//
//        }
        return "personal";
    }

    /**
     * 保存修改的个人信息
     * @param user
     * @return
     */
    @RequestMapping("/aboutme/modify")
    public String modifyInfo(User user,HttpSession session){
        userService.updateUser(user);
        session.setAttribute("user",user);
        return "personal";
    }
}


