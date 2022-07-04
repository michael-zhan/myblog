package com.michael.controller.home;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.github.pagehelper.PageInfo;
import com.michael.model.dto.FileUploadResult;
import com.michael.pojo.Attachment;
import com.michael.pojo.User;
import com.michael.service.AttachmentService;
import com.michael.util.OssUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 相册管理
 */
@Slf4j
@Controller
public class ManageAlbumController {
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private OssUtil ossUtil;
    /**
     * 获取所有相册
     */
    @RequestMapping(value = "/manage-album")
    public String attachments(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                              Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("userId",user.getId());
        PageInfo<Attachment> attachmentPageInfo = attachmentService.pageAttachment(pageNum, pageSize, criteria);

        model.addAttribute("pageInfo", attachmentPageInfo);

        return "manage-album";
    }

    /**
     * 上传文件
     */
    @RequestMapping(value="/manage-album/upload")

    public String uploadFile(@RequestParam(value = "photo", required = true) MultipartFile file, HttpServletRequest request) {
        final Map<String, Object> result = new HashMap<>(3);
        if (!file.isEmpty()) {
            try {
                final Map<String, Object> resultMap = attachmentService.attachUpload(file, request);
                if (resultMap == null || resultMap.isEmpty()) {
                    log.error("File upload failed");
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

                User user = (User) request.getSession().getAttribute("user");
                attachment.setAttachOrigin(user.getId());

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

        return "redirect:/manage-album";
    }

    /**
     * 删除附件
     * @param attachId
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage-album/delete/{id}")
    public String removeAttachment(@PathVariable ("id") Integer attachId,
                                   HttpServletRequest request, HttpSession session){
        Attachment attachment = attachmentService.getAttachment(attachId);

        String delFileName = attachment.getAttachPath();
        String delSmallFileName = attachment.getAttachSmallPath();
        boolean flag = true;
        try {
            //删除数据库中的内容
            attachmentService.deleteAttachment(attachId);
            //删除文件
            FileUploadResult fileUploadResult1 = ossUtil.delete(delFileName);
            FileUploadResult fileUploadResult2 = ossUtil.delete(delSmallFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/manage-album";
    }

}
