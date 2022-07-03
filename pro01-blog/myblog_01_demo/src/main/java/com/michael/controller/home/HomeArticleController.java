package com.michael.controller.home;

import com.michael.model.dto.ResultVo;
import com.michael.model.enums.BlogStatusEnum;
import com.michael.model.enums.PostTypeEnum;
import com.michael.pojo.Blog;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.CommentService;
//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
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



//    /**
//     * 进入文章管理界面
//     * @param session
//     * @return
//     */
//    @RequestMapping("/manage")
//    public String index(HttpSession session){
//        User user=(User)session.getAttribute("user");
//        return "redirect:/manage/1";
//    }

    /**
     * 进入文章详情界面
     * @param blogId
     * @param sign 是否增加浏览数
     * @param model
     * @return
     */
    @RequestMapping(value="/{blogId}/{sign}", method=RequestMethod.GET)
    public String browse(@PathVariable("blogId") Integer blogId,@PathVariable("sign") Integer sign,Model model){
//        Blog blog = blogService.getBlogByPublishedAndId(0,0,blogId);
//        Blog blog = blogService.getAndConvert(BlogStatusEnum.PUBLISHED.getCode(),PostTypeEnum.POST_TYPE_POST.getCode(),postId);
        Blog blog = blogService.getAndConvert(BlogStatusEnum.PUBLISHED.getCode(), PostTypeEnum.POST_TYPE_POST.getCode(),blogId);
        if(sign==1) {
            blogService.updateBlogView(blogId, blog.getViews() + 1);
        }
        if(blog!=null) {
//            List<Comment> commentList = commentService.getCommentList(blogId);
//
//            model.addAttribute("commentList",commentList);
            model.addAttribute("blog", blog);
        }
        return "blog";
    }

    /**
     * 进入创建文章界面
     * @return
     */
    @RequestMapping(value = "/toadd")
    public String toAdd(){
        return "write";
    }

    /**
     * 保存文章
     * @return
     */
    @RequestMapping("/save")
    public String add(Blog blog){
        if(blog!=null) {
            blogService.insertBlog(blog);
        }
        return "redirect:/index";
    }

    /**
     * 保存取消
     * @return
     */
    @RequestMapping("/cancel")
    public String cancel(){
        return "redirect:/index";
    }

    /**
     * 给文章点赞
     * @param blogId
     * @return
     */
    @RequestMapping("/like/{blogId}")
    public ResultVo like(@PathVariable Integer blogId,Model model){
        Blog blog;
        if((blog=blogService.getBlogByPublishedAndId(0,0,blogId))!=null) {
            blogService.incrBlogLikes(blogId);
            model.addAttribute("blog",blog);
            return new ResultVo("点赞成功");
        }else {
            return new ResultVo("文章不存在");
        }
    }

    /**
     * 编辑文章
     * @return
     */
    @RequestMapping("/edit/{blogId}")
    public String edit(@PathVariable Integer blogId,HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        if(blogService.findAuthor(blogId).equals(user.getId())) {
//            Blog blog= blogService.getBlogByPublishedAndId(0, 0, blogId);
            Blog blog=blogService.getAndConvert(0,0,blogId);
            model.addAttribute("blog",blog);
            return "write";
        }else {
            Blog blog = blogService.getBlogByPublishedAndId(0,0,blogId);
            return "redirect:/article/"+blog.getId().toString()+"/0";
        }
    }

    /**
     * 删除文章
     * @param blogId
     * @return
     */
    @RequestMapping("/remove/{blogId}")
    public String remove(@PathVariable Integer blogId){
        if(blogId!=null){
            blogService.deleteBlog(blogId);
        }
        return "redirect:/manage";
    }

    /**
     * 发表评论
     */
    @RequestMapping("/comment")
    public ResultVo comment(Comment comment){
        if(comment!=null){
            commentService.insertComment(comment);
            return new ResultVo("发表成功");
        }
        return new ResultVo("发表失败");
    }

    /**
     * 上一篇文章
     * @param blogId
     * @return
     */
    @RequestMapping("/prev/{blogId}")
    public String prev(@PathVariable Integer blogId){
        Blog blog=blogService.getPrevOrNext(blogId,false);
        if(blog!=null){
            return "redirect:/article/"+blog.getId().toString()+"/1";
        }else {
            System.out.println("没有上一篇");
            return "redirect:/article/"+blogId.toString()+"/0";
        }
    }

    /**
     * 下一篇文章
     * blogId为当前文章ID
     * @param blogId
     * @return
     */
    @RequestMapping("/next/{blogId}")
    public String next(@PathVariable Integer blogId){
        Blog blog=blogService.getPrevOrNext(blogId,true);
        if(blog!=null){
            return "redirect:/article/"+blog.getId().toString()+"/1";
        }else {
            System.out.println("没有下一篇");
            return "redirect:/article/"+blogId.toString()+"/0";
        }
    }

}
