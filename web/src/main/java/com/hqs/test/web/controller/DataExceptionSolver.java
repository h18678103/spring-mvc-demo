package com.hqs.test.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/8/23
 */
public interface DataExceptionSolver {

    @ExceptionHandler
    @ResponseBody
    default Object exceptionHandler(Exception e){
        try {
            throw e;
        } catch (SystemException systemException) {
            systemException.printStackTrace();
            return "interface SystemException 你好！";
        } catch (Exception e1){
            e1.printStackTrace();
            return "interface 系统错误 你好！";
        }
    }
}
