package com.michael.controller.home;

import com.michael.pojo.Blog;
import com.michael.pojo.Type;
import com.michael.pojo.User;
import com.michael.service.BlogService;
import com.michael.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeTypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    /**
     * 进入分类页面
     * 显示全部分类
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/type")
    public String typeIndex(HttpSession session, Model model){
        User user=(User)session.getAttribute("user");
        List<Type> typeList=null;
        if(user!=null&&user.getId()!=null) {
            List<Integer> typeIdList = blogService.getTypeIdList(user.getId());
            typeList = typeService.getTypeList(typeIdList);

            if (typeList != null) {
                Type type1 = typeList.get(0);
                model.addAttribute("type1", type1);

                if (typeList.size() > 1) {
                    Type type2 = typeList.get(1);
                    model.addAttribute("type2", type2);
                }
                if (typeList.size() > 2) {
                    Type type3 = typeList.get(2);
                    model.addAttribute("type3", type3);
                }
                if (typeList.size() > 3) {
                    Type type4 = typeList.get(3);
                    model.addAttribute("type4", type4);
                }
                if (typeList.size() > 4) {
                    Type type5 = typeList.get(4);
                    model.addAttribute("type5", type5);
                }
                if (typeList.size() > 5) {
                    Type type6 = typeList.get(5);
                    model.addAttribute("type6", type6);
                }
                if (typeList.size() > 6) {
                    Type type7 = typeList.get(6);
                    model.addAttribute("type7", type7);
                }
                if (typeList.size() > 7) {
                    Type type8 = typeList.get(7);
                    model.addAttribute("type8", type8);
                }
                if (typeList.size() > 8) {
                    Type type9 = typeList.get(8);
                    model.addAttribute("type9", type9);
                }
            }
        }

        model.addAttribute("typeList",typeList);
        return "timeline";
    }

    /**
     * 查看某一类型的文章
     * @param typeId
     * @return
     */
    @RequestMapping("/type/{typeId}")
    public String typeIndex(@PathVariable Integer typeId){
        return "redirect:/archive/"+typeId.toString();
    }

}
