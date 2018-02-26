package com.hqs.test.web.controller;

import com.hqs.test.dao.entity.Test;
import com.hqs.test.service.TestService;
import com.hqs.test.web.config.RedisConfig;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/2/24
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @Resource
    private RedisConfig redisConfig;

    @Resource
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "t1.do")
    public String t1(int id){
        System.out.println(redisConfig.getHost());
        System.out.println(redisConfig.getPort());
        Test n = testService.getTest(id);
        System.out.println(n);
        return "test1";
    }

}
