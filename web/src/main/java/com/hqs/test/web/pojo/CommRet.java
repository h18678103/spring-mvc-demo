package com.hqs.test.web.pojo;

import java.util.Map;

/**
 * 定义多项目共用的公共返回信息<br/>
 * @author devin.xu
 * @date 2017.07
 */
public enum CommRet implements RetInfo {

    SUCCESS                 ("00",  "成功"),
    FAIL                    ("01",  "错误"),
    INVALID_PARA            ("02",  "参数错误"),
    DB_FAIL                 ("03",  "数据更新失败"),
    TRADE_FAIL              ("04",  "交易失败"),
    TRADE_PROCESSING        ("05",  "处理中"),
    // 如果不是跨项目的retCode，不要定义到这里。写到自己项目的XxxRet中
    SYS_BUSY                ("99",  "系统繁忙，请稍后再试"),
    SYS_ERR                 ("99",  "系统异常");

    /** 返回码 */
    private String retCode;
    
    /** 返回描述 */
    private String retMsg;

    private CommRet(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    public Map<String, Object> toMap() {
        return BaseUtil.toMap(retCode, retMsg);
    }

    public Map<String, Object> toMap(String detail) {
        return BaseUtil.toMap(retCode, BaseUtil.isBlank(detail) ? retMsg : retMsg + ":" + detail);
    }

    @Override
    public String getRetCode() {
        return retCode;
    }
    
    @Override
    public String getRetMsg() {
        return retMsg;
    }
}
