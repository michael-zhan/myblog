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
     * 查看blog
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String getBlog(@PathVariable("id") Integer id,Model model){
        Long i=id.longValue();
        Blog blog = blogService.viewBlog(id.longValue());
        model.addAttribute("blog",blog);
        return "blog";
    }


    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ResponseBody
    public ResultVo addBlog(Blog blog){
        blogService.addBlog(blog);
        return new ResultVo("success to add article");
    }
}
