package com.hqs.test.web.pojo;

/**
 * 包含retCode、retMsg的返回值接口
 * @author xulin
 * <br/>2017年7月24日
 */
public interface RetInfo {

    /** 获取返回码 */
    String getRetCode();
    
    /** 获取返回描述 */
    String getRetMsg();
}