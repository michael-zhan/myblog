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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;

import static com.michael.util.MyUtils.code;

@RequestMapping("/")
@Controller
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

//    /**
//     * 进入登录界面
//     * @param model
//     * @return
//     */
//    @RequestMapping("/tologin")
//    public String index(Model model){
//      return "login";
//    }

    /**
     * 注册验证
     * @param user
     * @param code
     * @param session
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/register")
    public String register(User user, @RequestParam("code") String code, HttpSession session) throws NoSuchAlgorithmException {
       String expectedCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       if(isLeaglStr(user.getUsername(),user.getPassword(),user.getEmail(),code)
               &&code!=null&&expectedCode.toLowerCase().equals(code.toLowerCase())
               &&userService.getUserByEmail(user.getEmail())==null) {
           user.setPassword(MD5Util.getMd5(user.getPassword()));
           user.setAvatar("https://zyk-xxx.oss-cn-hangzhou.aliyuncs.com//images/2022/7/20220705_085033_561_small.jpeg\n");
           userService.insertUser(user);
       }
        return "login";
    }

    /**
     * 登录页面显示
     * @return
     */
    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request,HttpSession session, Map<String, Object> map) {
        String username = "";
        String password = "";
        //获取当前站点的所有Cookie
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
                if ("username".equals(cookies[i].getName())) {
                    username = cookies[i].getValue();
                } else if ("password".equals(cookies[i].getName())) {
                    password = cookies[i].getValue();
                }
            }
        }
        map.put("username", username);
        map.put("password", password);
        return "login";
    }

    /**
     * 登录验证
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/loginVerify")
    public String loginVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session)  {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");


        if(username == null || password == null) {
            return "login";
        }

        User user=userService.getUserByName(username);

        if(user==null){
            return "login";
        }

        if(!code(password).equals(user.getPassword())){
            return "login";
        }

        String contextPath = request.getContextPath();
        //添加session
        session.setAttribute("user", user);
        //添加cookie
        if(rememberme!=null) {
            //创建两个Cookie对象
            Cookie nameCookie = new Cookie("username", username);
            //设置Cookie的有效期为3天
            nameCookie.setMaxAge(60 * 60 * 24 * 3);
            nameCookie.setPath(contextPath + "/");
            Cookie pwdCookie = new Cookie("password", password);
            pwdCookie.setMaxAge(60 * 60 * 24 * 3);
            pwdCookie.setPath(contextPath + "/");
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }else{
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
                if ("username".equals(cookies[i].getName()) || "password".equals(cookies[i].getName())) {
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    cookie.setPath(contextPath + "/");
                    response.addCookie(cookie);
                }
            }
        }

        //登录成功
        return "redirect:/index";
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
