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
import java.util.Locale;

@Controller
@RequestMapping("/")
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    public String register(User user,String code,HttpSession session) throws NoSuchAlgorithmException {
       String expectedCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       if(isLeaglStr(user.getId(),user.getPassword(),user.getNickname(),code)
               &&code!=null&&expectedCode.toLowerCase().equals(code.toLowerCase())
               &&userService.getById(user.getId())==null) {
           userService.insert(user);
           return "login";
       }else{
           return "register";
       }
    }

    @RequestMapping("/login")
    public String login(String id, String password, String code,HttpSession session) throws NoSuchAlgorithmException {
        String expectedCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        User user;
        if(id!=null&&password!=null
                &&code!=null&&expectedCode.toLowerCase().equals(code.toLowerCase())) {
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

    public boolean isLeaglStr(String... args){
        for(String str:args){
            if(str==null||str.equals("")) {
                return false;
            }
        }
        return true;
    }
}
