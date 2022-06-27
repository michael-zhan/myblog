package com.michael.controller.home;

import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeIndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/index")
    public String index(HttpSession session){
        System.out.println("index() is running");
        User user = (User)session.getAttribute("user");
        List<Blog> blogList = blogService.selectByAuthor(user.getId());
        user.setBlogList(blogList);
        session.setAttribute("blogList",blogList);
        return "index";
    }
}
