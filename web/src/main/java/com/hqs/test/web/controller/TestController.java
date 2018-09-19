package com.hqs.test.web.controller;

import com.hqs.test.dao.entity.Test;
import com.hqs.test.service.TestService;
import com.hqs.test.web.config.RedisConfig;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/2/24
 */
@Controller
@RequestMapping(value = "test")
public class TestController implements DataExceptionSolver{

    @Resource
    private RedisConfig redisConfig;

    @Resource
    private TestService testService1;

    @ResponseBody
    @RequestMapping(value = "t1.do")
    public String t1(int id, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("t1.do");
        System.out.println(redisConfig.getHost());
        System.out.println(redisConfig.getPort());
//        Test n = testService1.getTest(id);
//        System.out.println(n);
        return "test1 你好！";
    }

    @ResponseBody
    @RequestMapping(value = "t2.do")
    public String t2(int id){
        System.out.println("t2.do");
        throw new SystemException();
    }

}
