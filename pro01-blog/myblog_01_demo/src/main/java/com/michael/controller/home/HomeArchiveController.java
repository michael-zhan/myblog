package com.michael.controller.home;

import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeArchiveController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;


    /**进入归档界面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value={"/archive/{pageIndex}","/archive","/archive/{typeId}"})
    public String archives(Model model, @PathVariable(required = false)String pageIndex, @PathVariable(required = false) Integer typeId,HttpSession session){
        if(pageIndex==null||pageIndex.equals("")){
            pageIndex="1";
        }
        Integer p=Integer.parseInt(pageIndex);
        Integer pageCount=1;

        List<Blog> blogList=null;
        User user=(User)session.getAttribute("user");
        if(user!=null&&user.getId()!=null) {
            if(typeId!=null){
                if(typeService.getTypeById(typeId)!=null){
                    blogList=blogService.getByPage(user.getId(), p,5,false,typeId);
                    pageCount=blogService.getCountLimitByTypeId(user.getId(),typeId)/5+1;
                }
            }else {
                blogList = blogService.getByPage(user.getId(), p, 5, false,null);
                pageCount = blogService.getCount(user.getId()) / 5 + 1;
            }


            if (blogList != null) {
                Blog blog1 = blogList.get(0);
                model.addAttribute("blog1", blog1);
                if (blogList.size() > 1) {
                    Blog blog2 = blogList.get(1);
                    model.addAttribute("blog2", blog2);

                }
                if (blogList.size() > 2) {
                    Blog blog3 = blogList.get(2);
                    model.addAttribute("blog3", blog3);
                }
                if (blogList.size() > 3) {
                    Blog blog4 = blogList.get(3);
                    model.addAttribute("blog4", blog4);
                }
                if (blogList.size() > 4) {
                    Blog blog5 = blogList.get(4);
                    model.addAttribute("blog5", blog5);
                }
            }
        }
        session.setAttribute("pageIndex",p);
        session.setAttribute("pageCount",pageCount);
        return "timeline";
    }
}
