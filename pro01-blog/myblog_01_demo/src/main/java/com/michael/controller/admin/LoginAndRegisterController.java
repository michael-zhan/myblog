package com.michael.controller.admin;

import com.michael.pojo.User;
import com.michael.service.UserService;
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

@Controller
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login_index")
    public String index(Model model){
      return "login";
    }

    /**
     * 进入注册界面
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
           userService.insertUser(user);
       }
        return "login";
    }

    /**
     * 进入登录界面
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
            if (user != null&&user.getPassword().equals(password)) {
                session.setAttribute("user",user);
                return "redirect:/index";
            }
        }
        return "login";
    }

//    @RequestMapping("/tologin")
//    public String toLogin(){
//        return "login";
//    }

//    @RequestMapping("toregister")
//    public String toRegister(){
//        return "register";
//    }

    public boolean isLeaglStr(String... args){
        for(String str:args){
            if(str==null||str.equals("")) {
                return false;
            }
        }
        return true;
    }
}
