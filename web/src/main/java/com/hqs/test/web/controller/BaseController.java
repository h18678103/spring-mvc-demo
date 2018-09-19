package com.hqs.test.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/8/23
 */
@ControllerAdvice
public class BaseController {

    //处理自定义的异常
    @ExceptionHandler()
    @ResponseBody
    public Object customHandler(SystemException e){
//        e.printStackTrace();
        return "SystemException 你好！";
    }

    //其他未处理的异常
    @ExceptionHandler()
    @ResponseBody
    public Object exceptionHandler(Exception e){
//        e.printStackTrace();
        return "Exception 你好！";
    }

}
