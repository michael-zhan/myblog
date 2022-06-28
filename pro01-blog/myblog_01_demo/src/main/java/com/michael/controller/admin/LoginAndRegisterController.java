package com.michael.controller.admin;

import com.michael.pojo.User;
import com.michael.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    public String register(User user) throws NoSuchAlgorithmException {
       String id=user.getId();
       if(userService.selectById(id)==null) {
           userService.insert(user);
           return "login";
       }else{
           return "register";
       }
    }

    @RequestMapping("/login")
    public String login(String id, String password, HttpSession session) throws NoSuchAlgorithmException {
        User user;
        if(id!=null&&password!=null) {
            user=userService.selectByIdAndPassword(id,password);
            if (user != null) {
                session.setAttribute("user",user);
                return "redirect:/index";
            }
        }
        return "login";
    }

    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("toregister")
    public String toRegister(){
        return "register";
    }
}
