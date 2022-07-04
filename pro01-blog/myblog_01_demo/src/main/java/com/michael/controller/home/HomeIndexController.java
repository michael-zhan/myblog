package com.michael.controller.home;

import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value={"/index/{pageIndex}","/index","/friendRoom/{pageIndex}","/friendRoom"},method={RequestMethod.GET,RequestMethod.POST})
    public String index(HttpSession session, @PathVariable(required = false)String pageIndex, Model model,HttpServletRequest httpServletRequest){
        if(pageIndex==null||pageIndex.equals("")){
            pageIndex="1";
        }
        Integer p=Integer.parseInt(pageIndex);
        Integer pageCount=1;

        User user=null;
        if(session.getAttribute("friend")!=null&&httpServletRequest.getServletPath().startsWith("/friendRoom")) {
            user = (User) session.getAttribute("friend");
        }else{
            user=(User)session.getAttribute("user");
        }

        List<Blog> blogList=null;
        if(user!=null&&user.getId()!=null) {
            blogList = blogService.getByPage(user.getId(), p, EachPageCount.EACH_PAGE_COUNT_INDEX, false,null);
            pageCount = blogService.getCount(user.getId()) / EachPageCount.EACH_PAGE_COUNT_INDEX + 1;

//            if (blogList != null) {
//                Blog blog1 = blogList.get(0);
//                model.addAttribute("blog1", blog1);
//                if (blogList.size() > 1) {
//                    Blog blog2 = blogList.get(1);
//                    model.addAttribute("blog2", blog2);
//                }
//                if (blogList.size() > 2) {
//                    Blog blog3 = blogList.get(2);
//                    model.addAttribute("blog3", blog3);
//                }
//            }
        }

//        session.setAttribute("pageIndex",p);
//        session.setAttribute("pageCount",pageCount);
        model.addAttribute("blogList", blogList);
        model.addAttribute("pageIndex",p);
        model.addAttribute("pageCount",pageCount);
        return "homepage";
    }
}
