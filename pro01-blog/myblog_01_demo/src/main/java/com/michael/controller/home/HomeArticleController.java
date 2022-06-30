package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.Blog;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/article")
public class HomeArticleController {

    @Autowired
    private BlogService blogService;

    /**
     * 浏览blog
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping(value="/{blogId}", method=RequestMethod.GET)
    public String browse(@PathVariable("blogId") Integer blogId,Model model){
        Blog blog = blogService.viewBlog(blogId);
        model.addAttribute("blog",blog);
        return "blog";
    }

    /**
     * 添加文章
     * @param blog
     * @return
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ResponseBody
    public ResultVo add(Blog blog){
        blogService.addBlog(blog);
        return new ResultVo("success to add article");
    }

    /**
     * 给文章点赞
     * @param blogId
     * @return
     */
    @RequestMapping("/like/{blogId}")
    public ResultVo like(@PathVariable Integer blogId){

        blogService.getById(blogId);
        blogService.likeBlog(blogId);
        return new ResultVo("点赞成功");
    }

    /**
     * 评论文章
     */

}
