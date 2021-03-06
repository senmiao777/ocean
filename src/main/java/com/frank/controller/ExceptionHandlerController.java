package com.frank.controller;

import com.frank.exception.ResubmitException;
import com.frank.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frank
 * @version 1.0
 * @date 2018/1/23 0023 下午 8:32
 * 统一异常处理
 */
@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ResubmitException.class)
    public JsonResult resubmit() {
        return JsonResult.error("请不要重复提交",null);
    }
}
