package org.x.study.shirodemo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.x.study.shirodemo.model.ResultMap;

import javax.security.auth.login.AccountException;

@RestControllerAdvice
public class ExceptionController {
    private final ResultMap resultMap;

    public ExceptionController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @ExceptionHandler(AccountException.class)
    public ResultMap handleShiroException(Exception ex) {
        return resultMap.fail().message(ex.getMessage());
    }
}
