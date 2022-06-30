package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.pojo.Blog;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/article")
public class HomeArticleController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;


    /**
     * 查看文章详情
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping(value="/{blogId}", method=RequestMethod.GET)
    public String browse(@PathVariable("blogId") Integer blogId,Model model){
        Blog blog = blogService.viewBlog(blogId);
        if(blog!=null) {
            List<Comment> commentList = commentService.getCommentList(blogId);

            model.addAttribute("commentList",commentList);
            model.addAttribute("blog", blog);
        }
        return "blog";
    }

    /**
     * 创建文章
     * @return
     */
    @RequestMapping(value = "/toadd")
    public String toAdd(){
        return "add";
    }

    /**
     * 保存文章
     * @return
     */
    @RequestMapping("/save")
    public String add(Blog blog){
        if(blog!=null) {
            blogService.addBlog(blog);
        }
        return "index";
    }

    /**
     * 给文章点赞
     * @param blogId
     * @return
     */
    @RequestMapping("/like/{blogId}")
    public ResultVo like(@PathVariable Integer blogId,Model model){
        if(blogService.getById(blogId)!=null) {
            blogService.likeBlog(blogId);
            Blog blog = blogService.getById(blogId);
            model.addAttribute("blog",blog);
            return new ResultVo("点赞成功");
        }else {
            return new ResultVo("文章不存在");
        }
    }

    /**
     * 转发到编辑页面
     * @return
     */
    @RequestMapping("/toedit/{blogId}")
    public String toEdit(@PathVariable Integer blogId,HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        if(blogService.findAuthor(blogId).equals(user.getId())) {
            return "forward:/edit";
        }else {
            Blog blog = blogService.getById(blogId);
            model.addAttribute("blog",blog);
            return "forward:/article/edit/"+blogId.toString();
        }
    }

    /**
     * 编辑文章
     * @param blogId
     * @return
     */
    @RequestMapping("/edit/{blogId}")
    public String edit(@PathVariable Integer blogId,Model model){
        Blog blog = blogService.getById(blogId);
        model.addAttribute("blog",blog);
        return "edit";
    }

    /**
     * 发表评论
     * 
     */
    @RequestMapping("/comment")
    public ResultVo comment(Comment comment){
        if(comment!=null){
            commentService.addComment(comment);
            return new ResultVo("发表成功");
        }
        return new ResultVo("发表失败");
    }

}
