package com.michael.controller.home;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.michael.model.dto.ResultVo;
import com.michael.model.enums.EachPageCount;
import com.michael.pojo.Attachment;
import com.michael.pojo.Blog;
import com.michael.pojo.Comment;
import com.michael.pojo.User;
import com.michael.service.AttachmentService;
import com.michael.service.CommentService;
import com.michael.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HomeAboutMeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 进入关于个人信息界面
     * @return
     */
    @RequestMapping("/aboutme/info")
    public String index(){
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
            messageList = commentService.getByPage(user.getId(), m, eachPageCount);
            if(commentService.getCount(user.getId() % eachPageCount)>0){
                messagePageCount = commentService.getCount(user.getId()) / eachPageCount + 1;
            }else {
                messagePageCount = commentService.getCount(user.getId()) / eachPageCount ;
            }

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
        return "album_board";
    }

    /**
     * 保存修改的个人信息
     * @return
     */
    @RequestMapping("/aboutme/modify")
    public String modifyInfo(@RequestParam("description") String description, @RequestParam("nickname")String nickname,
                             @RequestParam("photo") MultipartFile file, HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        if(nickname!=null&&!nickname.equals("")) {
            user.setNickname(nickname);
        }
        if(description!=null&&!description.equals("")){
            user.setDescription(description);
        }

        final Map<String, Object> result = new HashMap<>(3);
        if (!file.isEmpty()) {
            try {
                final Map<String, Object> resultMap = attachmentService.attachUpload(file, request);
                if (resultMap == null || resultMap.isEmpty()) {
                    result.put("success", 0);
                    result.put("message", "upload-failed");
                }

                //保存在数据库
                Attachment attachment = new Attachment();
                attachment.setAttachName((String) resultMap.get("fileName"));
                attachment.setAttachPath((String) resultMap.get("filePath"));
                attachment.setAttachSmallPath((String) resultMap.get("smallPath"));
                attachment.setAttachType(file.getContentType());
                attachment.setAttachSuffix((String) resultMap.get("suffix"));
                attachment.setCreateTime(DateUtil.date());
                attachment.setAttachSize((String) resultMap.get("size"));
                attachment.setAttachWh((String) resultMap.get("wh"));
                attachment.setRawSize((Long) resultMap.get("rowSize"));

                user.setAvatar(attachment.getAttachSmallPath());

                attachmentService.insert(attachment);
                log.info("Upload file {} to {} successfully", resultMap.get("fileName"), resultMap.get("filePath"));
                result.put("success", 1);
                result.put("message", "upload-success");
                result.put("url", attachment.getAttachPath());
                result.put("filename", resultMap.get("filePath"));
            } catch (Exception e) {
                log.error("Upload file failed:{}", e.getMessage());
                result.put("success", 0);
                result.put("message", "upload-failed");
            }
        } else {
            log.error("File cannot be empty!");
        }



        userService.updateUser(user);
        session.setAttribute("user",user);
        return "personal";
    }

    /**
     * 注销用户
     * @param session
     * @return
     */
    @RequestMapping("/aboutme/logout")
    public String logout(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/tologin";
    }

    /**
     * 锁空间
     * @param session
     * @return
     */
    @RequestMapping("/aboutme/lock")
    public String lock(HttpSession session){
        User user=(User)session.getAttribute("user");
        user.setStatus(false);
        userService.updateUser(user);
        return "redirect:/index";
    }

    /**
     * 解锁空间
     */
    @RequestMapping("/aboutme/unlock")
    public String unlock(HttpSession session){
        User user=(User)session.getAttribute("user");
        user.setStatus(true);
        userService.updateUser(user);
        return "redirect:/index";
    }
}


