package com.hqs.test.web.controller;

import com.hqs.test.dao.entity.Test;
import com.hqs.test.service.TestService;
import com.hqs.test.web.config.RedisConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2018/2/24
 */
@Controller
@RequestMapping(value = "test")
public class TestController implements DataExceptionSolver {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private RedisConfig redisConfig;

    @Resource
    private TestService testService1;

    @ResponseBody
    @RequestMapping(value = "t1.do")
    public String t1(int id, HttpServletResponse response) {
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
    public String t2(int id) {
        System.out.println("t2.do");
        throw new SystemException();
    }

    @ResponseBody
    @RequestMapping(value = "t3.do")
    public String t3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("lastAccessTime")) {
                    Long lastAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    sb.append("您上次访问的时间是：");
                    sb.append(date.toLocaleString());
                } else {
                    sb.append("这是您第一次访问本站！");
                }
            }
        }

        //用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");//创建一个cookie，cookie的名字是lastAccessTime
        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        response.addCookie(cookie);

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(value = "t4.do")
    public String t4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                logger.info("name={}, value={}, path={}, " +
                                "domain={}, maxAge={}, version={} " +
                                "comment={}, secure={}",
                        c.getName(), c.getValue(), c.getPath(),
                        c.getDomain(), c.getMaxAge(), c.getVersion(),
                        c.getComment(), c.getSecure());
            }
        }
        return sb.toString();
    }


    @ResponseBody
    @RequestMapping(value = "t5.do")
    public String t5(String name, String value, String domain, String path, int maxAge, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie(name, value);
        cookie.setVersion(1);
        cookie.setComment("comment1");
        if (domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxAge);
        if (path!=null){
            cookie.setPath(path);
        }
        response.addCookie(cookie);
        return "add ok!";
    }
}
