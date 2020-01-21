package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Map;


/**
 * @author suwen
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String root() {
        return "首页";
    }

    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map) {

        map.put("user", new User());

        return "登陆注册/登录页";
    }


    @PostMapping(value = "/login")
    public String login(User user, Map<String, Object> map, HttpServletRequest request) {

        System.out.println(user);

        if (user.getUserName().contains("@")) {
            user.setUserEmail(user.getUserNo());
            user.setUserNo(null);
        }
        User user1 = userService.getOneUser(user);

        System.out.println(user1);

        if (user1 != null) {

            map.put("user", user1);

            //获取session并将userName存入session对象
            HttpSession session = request.getSession();
            session.setAttribute("USER_SESSION_KEY", user1);

            System.out.println("登录成功！");
            return "redirect:/";
        }


        System.out.println("登录失败！");
        return "登陆注册/登录页";

    }

    @GetMapping("/mainController")
    public String main() {

        return "首页";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().setAttribute("USER_SESSION_KEY", null);

        return "redirect:/";

    }

}