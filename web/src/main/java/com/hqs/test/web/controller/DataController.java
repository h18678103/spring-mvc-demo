package com.hqs.test.web.controller;

import com.hqs.test.web.pojo.ServiceRet;
import java.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huqinsong
 * @date 2019/6/25
 */
@Controller
@RequestMapping(value = "data")
public class DataController {

    //http://localhost:8080/web/data/t1.do?dataType=10&data=1234

    @ResponseBody
    @RequestMapping(value = "t1.do")
    public String t1(String dataType, String data) {
        try {
            File writeName = new File("D:\\output.txt");
            try (FileWriter writer = new FileWriter(writeName, true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("dataType:"+dataType+", data="+data+"\r\n");
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServiceRet serviceRet = new ServiceRet();
        return serviceRet.toJson();
    }
}
