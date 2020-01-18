package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author suwen
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String root(HttpServletRequest request) {
        //获取session并将userName存入session对象
        User user = (User) request.getSession().getAttribute("USER_SESSION_KEY");
        System.out.println(user);
        //如果session中没有user，表示没登陆
        if (user == null) {
            user = new User("-1","游客","-1","-1","-1");
            HttpSession session = request.getSession();
            session.setAttribute("USER_SESSION_KEY", user);
            System.out.println(user);
        }
        return "首页";
    }

    @GetMapping("/index")
    public String index() {
        return "首页";
    }

}
