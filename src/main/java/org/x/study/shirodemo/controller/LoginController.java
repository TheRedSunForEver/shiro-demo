package org.x.study.shirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.x.study.shirodemo.mapper.UserMapper;
import org.x.study.shirodemo.model.ResultMap;


@RestController
public class LoginController {
    private final ResultMap resultMap;
    private final UserMapper userMapper;

    public LoginController(ResultMap resultMap, UserMapper userMapper) {
        this.resultMap = resultMap;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/logout")
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return resultMap.success().message("注销成功");
    }

    @RequestMapping(value = "/login")
    public ResultMap login(String username, String password) {
        Subject subject  = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        String role = userMapper.getRole(username);
        if ("user".equalsIgnoreCase(role)) {
            return resultMap.success().message("欢迎登录");
        }

        if ("admin".equalsIgnoreCase(role)) {
            return resultMap.success().message("欢迎管理员");
        }

        return resultMap.fail().message("登录错误");
    }
}
