package org.x.study.shirodemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

    @RequestMapping("/")
    public Object index() {
        return "index";
    }

    @RequestMapping("/notLogin")
    public Object notLogin() {
        return "没有登录";
    }

    @RequestMapping("/notRole")
    public Object notRole() {
        return "没有权限";
    }
}
