package com.michael.controller.home;

import com.michael.pojo.User;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeArchiveController {

    @Autowired
    private BlogService blogService;



    /**归档模块
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/archive")
    public String archives(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        model.addAttribute("archiveMap" ,blogService.archiveBlog(user.getId()));
        return "archive";
    }

}
