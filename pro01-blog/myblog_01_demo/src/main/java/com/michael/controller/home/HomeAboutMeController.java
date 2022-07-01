package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.User;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeAboutMeController {

    @Autowired
    private UserService userService;

    /**
     * 进入关于个人界面
     * @param session
     * @return
     */
    @RequestMapping("/aboutme")
    public String index(HttpSession session){
        return "aboutme";
    }

    /**
     * 保存修改的个人信息
     * @param user
     * @return
     */
    @RequestMapping("/aboutme/modify")
    public String modifyInfo(User user){
        userService.updateUser(user);
        return null;
    }
}


