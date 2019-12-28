package com.cqjtu.rrtf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String root() {
        return "首页";
    }

    @GetMapping("/index")
    public String index() {
        return "首页";
    }

}
