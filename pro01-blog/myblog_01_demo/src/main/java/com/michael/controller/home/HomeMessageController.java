package com.michael.controller.home;

import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/message")
public class HomeMessageController {

    @Autowired
    private CommentService commentService;


    /**
     * 进入管理留言界面
     * @param model
     * @param messagePageIndex
     * @param session
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = {"/index","/index/messagePageIndex"})
    public String message(Model model,
                          @PathVariable(required = false) String messagePageIndex,
                          HttpSession session,
                          HttpServletRequest httpServletRequest){
        if(messagePageIndex==null||messagePageIndex.equals("")){
            messagePageIndex="1";
        }

        Integer m=Integer.parseInt(messagePageIndex);
        Integer messagePageCount=1;

        User user = (User)session.getAttribute("user");

        Integer eachPageCount= EachPageCount.EACH_PAGE_COUNT_MESSAGE;
        List<Comment> messageList=null;
        if(user!=null&&user.getId()!=null) {
            messageList = commentService.getByPage(user.getId(), m, eachPageCount);
            messagePageCount = commentService.getCount(user.getId()) / eachPageCount + 1;

            if (messageList != null) {
                Comment message1 = messageList.get(0);
                model.addAttribute("message1", message1);
                if (messageList.size() > 1) {
                    Comment message2 = messageList.get(1);
                    model.addAttribute("message2", message2);

                }
                if (messageList.size() > 2) {
                    Comment message3 = messageList.get(2);
                    model.addAttribute("message3", message3);

                }
                if (messageList.size() > 3) {
                    Comment message4 = messageList.get(3);
                    model.addAttribute("message4", message4);

                }
                if (messageList.size() > 4) {
                    Comment message5 = messageList.get(4);
                    model.addAttribute("message5", message5);

                }
            }
        }

        model.addAttribute("messageList",messageList);
        model.addAttribute("messagePageIndex",m);
        model.addAttribute("messagePageCount",messagePageCount);
        return "manage-message";
    }

    /**
     * 写留言
     * @return
     */
    @RequestMapping("/save/{friendId}")
    public String save(@PathVariable Integer friendId , Comment comment, HttpSession session){
        if(comment!=null) {
            User user=(User)session.getAttribute("user");
            if(user!=null) {
                comment.setBlogId(friendId);
                comment.setNickname(user.getNickname());
                commentService.insertComment(comment);
            }
        }
        return "redirect:/friendRoom";
    }

    /**
     * 删除留言
     */
    @RequestMapping("/delete/{commentId}")
    public String delete(@PathVariable Integer commentId){
        Comment comment = commentService.getById(0, 0, 0, commentId);
        if(comment!=null){
            commentService.deleteComment(commentId);
        }
        return "redirect:/message/index";
    }
}
