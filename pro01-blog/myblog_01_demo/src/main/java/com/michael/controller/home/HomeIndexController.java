package com.michael.controller.home;

import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeIndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/index")
    public String index(HttpSession session){
        User user = (User)session.getAttribute("User");
        user.setBlogList(blogService.selectByAuthor(user.getId()));
        return "redirect:index";
    }
}
