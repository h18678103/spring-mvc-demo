package com.hqs.test.web.controller;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2019/1/30
 */
@Controller
@RequestMapping(value = "orderInfo")
public class CustomTestController {

    @ResponseBody
    @RequestMapping(value = "OrderRecordJson.do")
    public String OrderRecordJson(HttpServletRequest req, String orderNo) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((s, strings) -> System.out.println(s + "=" + strings[0]));
        Map<String, String> map = new HashMap(2);
        if (orderNo != null && orderNo.length() % 2 == 0) {
            map.put("code", "0");
            map.put("message", "报关信息上送成功！");
        } else {
            map.put("code", "201");
            map.put("message", "商户号或者密钥错误！");
        }
        return JSONObject.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "findOrderRecordInfo.do")
    public String findOrderRecordInfo(HttpServletRequest req, String orderNo) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((s, strings) -> System.out.println(s + "=" + strings[0]));
        Map<String, String> map = new HashMap(2);
        if (orderNo != null && orderNo.length() % 2 == 0) {
            map.put("code", "0");
            map.put("message", "报备信息查询成功！");
        } else {
            map.put("code", "201");
            map.put("message", "商户号或者密钥错误！");
        }
        return JSONObject.toJSONString(map);
    }


    public static void main(String[] args) {
        Map<String, String> map = new HashMap(2);
        map.put("code", "0");
        map.put("message", "报关信息上送成功！");
        System.out.println(JSONObject.toJSONString(map));
    }
}
