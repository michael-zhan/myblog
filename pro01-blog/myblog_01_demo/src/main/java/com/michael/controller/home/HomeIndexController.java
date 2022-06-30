package com.michael.controller.home;

import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeIndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    /**
     * 显示首页
     * @param session
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping(value={"/index/{pageIndex}","/index"},method={RequestMethod.GET,RequestMethod.POST})
    public String index(HttpSession session, @PathVariable(required = false)String pageIndex, Model model){

        if(pageIndex==null||pageIndex.equals("")){
            pageIndex="1";
        }
        Integer p=Integer.parseInt(pageIndex);
        User user = (User)session.getAttribute("user");
        List<Blog> blogList = blogService.getByPage(user.getId(),p,3);

        Integer pageCount = blogService.getCount(user.getId())/3+1;


        Blog blog1=blogList.get(0);
//        session.setAttribute("blog1",blog1);
        model.addAttribute("blog1",blog1);
        if(blogList.size()>1) {
            Blog blog2 = blogList.get(1);
//            session.setAttribute("blog2",blog2);
            model.addAttribute("blog2",blog2);

        }
        if(blogList.size()>2) {
            Blog blog3 = blogList.get(2);
//            session.setAttribute("blog3",blog3);
            model.addAttribute("blog3",blog3);
        }

        session.setAttribute("blogList",blogList);
        session.setAttribute("pageIndex",p);
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
}
