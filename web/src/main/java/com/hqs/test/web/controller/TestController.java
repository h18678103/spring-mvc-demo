package com.hqs.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/2/24
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "test1")
    public String test1(){
        return "test1";
    }

}
