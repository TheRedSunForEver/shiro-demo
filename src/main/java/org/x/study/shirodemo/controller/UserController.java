package org.x.study.shirodemo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.x.study.shirodemo.model.ResultMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ResultMap resultMap;

    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/getMessage")
    public ResultMap getMessage() {
        return resultMap.success().message("拥有user权限，可以访问该url");
    }
}
