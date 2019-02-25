package org.x.study.shirodemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.x.study.shirodemo.model.ResultMap;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ResultMap resultMap;

    public AdminController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/getMessage")
    public ResultMap getMessage() {
        return resultMap.success().message("拥有admin权限，可以访问该url");
    }
}
