package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 访问用户输入界面
     */
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("user", new User());

        return "登陆注册/注册页";
    }

    /**
     * 创建新用户
     */
    @PostMapping(value = "/create")
    public String create(User user, HttpServletRequest request) {

        //默认用户的权限为普通用户
        user.setUserTab("2");
        System.out.println(user);
//        userService.addUser(user);

        //获取session并将userName存入session对象
        HttpSession session = request.getSession();
        session.setAttribute("USER_SESSION_KEY", user);

        return "首页";

    }

    @GetMapping("/info")
    public String userInfo(Map<String, Object> map, HttpServletRequest request) {

        map.put("user", request.getSession().getAttribute("USER_SESSION_KEY"));

        return "个人资料/普通用户-首页";
    }

    @GetMapping("/profile")
    public String userprofile(Map<String, Object> map, HttpServletRequest request) {

        map.put("user", request.getSession().getAttribute("USER_SESSION_KEY"));

        return "个人资料/用户中心-个人资料";
    }

    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr) {

        int pageNo = 1;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if (pageNo < 1) {
            pageNo = 1;
        }

        /*
         * 第一个参数：第几页;
         * 第二个参数：每页获取的条数.
         */
        PageHelper.startPage(pageNo, 4);
        List<User> userList = userService.loadAll();

        PageInfo<User> page = new PageInfo<>(userList);

        map.put("page", page);

        return "user/list_user";
    }

    @GetMapping(value = "/remove/{userNo}")
    public String remove(@PathVariable("userNo") Integer userNo) {

        userService.removeUser(userNo);
        return "redirect:/user/list";
    }

    @GetMapping(value = "/preUpdate/{userNo}")
    public String preUpdate(@PathVariable("userNo") String userNo, Map<String, Object> map) {
        System.out.println(userService.get(userNo));
        map.put("user", userService.get(userNo));

        return "user/update_user";
    }

    @RequestMapping(value = "/update")
    public String update(User user, HttpServletRequest request) {

        User oldUser = (User)request.getSession().getAttribute("USER_SESSION_KEY");
        oldUser.setUserName(user.getUserName());
        oldUser.setUserEmail(user.getUserEmail());
        oldUser.setUserBirth(user.getUserBirth());
        oldUser.setUserSign(user.getUserSign());
        oldUser.setUserTel(user.getUserTel());
        oldUser.setUserSex(user.getUserSex());

        userService.updateUser(oldUser);

        request.getSession().setAttribute("USER_SESSION_KEY",oldUser);

        return "redirect:/user/profile";
    }
}
