package com.michael.controller.home;

import com.michael.pojo.Blog;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class HomeArticleController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/{id}")
    public String getBlog(@PathVariable("id") Integer id,Model model){
        Long i=id.longValue();
        Blog blog = blogService.selectById(i);
        model.addAttribute("blog",blog);
        return "blog";
    }
}
