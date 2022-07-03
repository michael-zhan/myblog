package com.michael.controller.admin;

import com.michael.pojo.User;
import com.michael.service.UserService;
import com.michael.util.MD5Util;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@RequestMapping("/")
@Controller
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

    /**
     * 进入登录界面
     * @param model
     * @return
     */
    @RequestMapping("/tologin")
    public String index(Model model){
      return "login";
    }

    /**
     * 注册验证
     * @param user
     * @param code
     * @param session
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/register")
    public String register(User user,String code,HttpSession session) throws NoSuchAlgorithmException {
       String expectedCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       if(isLeaglStr(user.getUsername(),user.getPassword(),user.getEmail(),code)
               &&code!=null&&expectedCode.toLowerCase().equals(code.toLowerCase())
               &&userService.getUserByEmail(user.getEmail())==null) {
           user.setPassword(MD5Util.getMd5(user.getPassword()));
           userService.insertUser(user);
       }
        return "login";
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @param session
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) throws NoSuchAlgorithmException {
        String expectedCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        User user;
        if(username!=null&&password!=null) {
            user=userService.getUserByName(username);
            if (user != null&&user.getPassword().equals(MD5Util.getMd5(password))) {
                session.setAttribute("user",user);
                return "redirect:/index";
            }
        }
        return "login";
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
