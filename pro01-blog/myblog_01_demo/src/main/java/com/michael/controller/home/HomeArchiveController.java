package com.michael.controller.home;

import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeArchiveController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;


    /**进入归档界面
     * 或是文章管理界面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value={"/archive","/archive/{pageIndex}","/archive/{typeId}","/archive/{typeId}/{pageIndex}",
            "/manage","/manage/{pageIndex}"})
    public String archives(Model model,
                           @PathVariable(value = "pageIndex",required = false)String pageIndex,
                           @PathVariable(value = "typeId",required = false) Integer typeId,
                           HttpSession session,
                           HttpServletRequest httpServletRequest){
        if(pageIndex==null||pageIndex.equals("")){
            pageIndex="1";
        }
        Integer p=Integer.parseInt(pageIndex);
        Integer pageCount=1;

        List<Blog> blogList=null;
        String returnStr="redirect:/index";

        User user=(User)session.getAttribute("user");
        if(user!=null&&user.getId()!=null) {
            Integer eachPageCount=null;
            String servletPath = httpServletRequest.getServletPath();
            if(servletPath.startsWith("/archive")){
                eachPageCount= EachPageCount.EACH_PAGE_COUNT_ARCHIVE;
                returnStr="/manage-page";
            }else{
                eachPageCount=EachPageCount.EACH_PAGE_COUNT_MANAGE;
                returnStr="/timeline";
            }

            if(typeId!=null){
                if(typeService.getTypeById(typeId)!=null){
                    blogList=blogService.getByPage(user.getId(), p,eachPageCount,false,typeId);
                    pageCount=blogService.getCountLimitByTypeId(user.getId(),typeId)/eachPageCount+1;
                }
            }else {
                blogList = blogService.getByPage(user.getId(), p, eachPageCount, false,null);
                pageCount = blogService.getCount(user.getId()) / eachPageCount + 1;
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
//        session.setAttribute("pageIndex",p);
//        session.setAttribute("pageCount",pageCount);
        model.addAttribute("blogList",blogList);
        model.addAttribute("pageIndex",p);
        model.addAttribute("pageCount",pageCount);

        return returnStr;
    }
}
