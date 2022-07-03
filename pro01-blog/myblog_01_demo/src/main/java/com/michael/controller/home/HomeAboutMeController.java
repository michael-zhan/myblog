package com.michael.controller.home;

import com.github.pagehelper.PageInfo;
import com.michael.model.dto.ResultVo;
import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Blog;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.CommentService;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeAboutMeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    /**
     * 进入关于个人信息界面
     * @param session
     * @return
     */
    @RequestMapping("/aboutme/info")
    public String index(HttpSession session){
//        User user=(User)session.getAttribute("user");
        return "personal";
    }

    /**
     * 进入留言板与相册的界面
     * 默认每页三条留言
     * @return
     */
    @RequestMapping(value = {"/aboutme/message/{photoPageIndex}/{messagePageIndex}","/aboutme/message",
            "/aboutfriend/message","/aboutfriend/message/{photoPageIndex}/{messagePageIndex}"})
    public String message(Model model,
                          @PathVariable(required = false) String photoPageIndex,
                          @PathVariable(required = false) String messagePageIndex,
                          HttpSession session,
                          HttpServletRequest httpServletRequest){
        if(photoPageIndex==null||photoPageIndex.equals("")){
            photoPageIndex="1";
        }
        if(messagePageIndex==null||messagePageIndex.equals("")){
            messagePageIndex="1";
        }

        Integer p=Integer.parseInt(photoPageIndex);
        Integer m=Integer.parseInt(messagePageIndex);
        Integer photoPageCount=1;
        Integer messagePageCount=1;

        User user=null;
        if(session.getAttribute("friend")!=null&&httpServletRequest.getServletPath().startsWith("/aboutfriend/message")){
            user=(User)session.getAttribute("friend");
        }else{
            user = (User)session.getAttribute("user");
        }

        Integer eachPageCount= EachPageCount.EACH_PAGE_COUNT_MESSAGE;
        List<Comment> messageList=null;
        if(user!=null&&user.getId()!=null) {
            messageList = commentService.getByPage(user.getId(), p, eachPageCount);
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
        model.addAttribute("photoPageIndex",p);
        model.addAttribute("messagePageIndex",m);
        model.addAttribute("photoPageCount",photoPageCount);
        model.addAttribute("messagePageCount",messagePageCount);
        return "personal";
    }

    /**
     * 保存修改的个人信息
     * @param user
     * @return
     */
    @RequestMapping("/aboutme/modify")
    public String modifyInfo(User user,HttpSession session){
        userService.updateUser(user);
        session.setAttribute("user",user);
        return "personal";
    }

}


