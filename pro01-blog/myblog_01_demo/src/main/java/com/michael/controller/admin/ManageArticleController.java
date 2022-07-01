package com.michael.controller.admin;

import com.michael.pojo.User;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/manage")
public class ManageArticleController {

    @Autowired
    private BlogService blogService;


    /**
     * 进入文章管理界面
     * @param session
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession session){
        User user=(User)session.getAttribute("user");
        return null;
    }
}
