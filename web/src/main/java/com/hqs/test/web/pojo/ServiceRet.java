package com.hqs.test.web.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务返回结果
 * @author xulin
 * 2016年9月2日
 */
public class ServiceRet<T> implements RetInfo, Serializable {
    
    private static final long serialVersionUID = 1L;

    private String retCode;
    
    private String retMsg;
    
    private T content;

    /**
     * 对于form表单请求，需要告知客户端错误发生的字段和错误信息
     */
    private Map<String, String> errors = null;

    /** 默认为成功 */
    public ServiceRet() {
        success();
    }
    
    public ServiceRet(RetInfo ret) {
        setRet(ret);
    }
    
    public ServiceRet(RetInfo ret, String detail) {
        setRet(ret, detail);
    }
    
    public ServiceRet(RetInfo ret, Throwable t) {
        setRet(ret, t);
    }
    
    /** 设置为成功 */
    public ServiceRet<T> success() {
        return setRet(CommRet.SUCCESS);
    }
    
    /** 设置为失败并添加详情 */
    public ServiceRet<T> fail(String detail) {
        setRet(CommRet.FAIL, detail);
        return this;
    }
    
    public ServiceRet<T> fail() {
        return fail("");
    }
    
    /** 设置为系统异常 */
    public ServiceRet<T> syserr(String detail) {
        setRet(CommRet.SYS_ERR, detail);
        return this;
    }
    
    public ServiceRet<T> syserr(Throwable t) {
        setRet(CommRet.SYS_ERR, t);
        return this;
    }
    
    public ServiceRet<T> syserr() {
        return syserr("");
    }
    
    /** 设置为数据库操作失败 */
    public ServiceRet<T> dbfail() {
        return setRet(CommRet.DB_FAIL);
    }
    
    /**
     * 比较retCode是否一致
     * @param ret
     * @return
     */
    private boolean match(CommRet ret) {
        return ret.getRetCode().equals(this.retCode);
    }
    
    /** 是否成功 */
    public boolean isSuccess() {
        return match(CommRet.SUCCESS);
    }
    
    /** 是否交易失败 */
    public boolean isTradeFail() {
        return match(CommRet.TRADE_FAIL);
    }
    
    /** 是否交易处理中 */
    public boolean isTradeProcessing() {
        return match(CommRet.TRADE_PROCESSING);
    }

    /** 比较retCode */
    public boolean match(RetInfo ret) {
        return this.retCode.equals(ret.getRetCode());
    }

    private ServiceRet<T> setRet(String retCode, String retMsg, String detail) {
        this.retCode = retCode;
        this.retMsg = BaseUtil.isBlank(detail) ? retMsg : retMsg + ":" + detail;
        return this;
    }
    
    /** 设置返回结果和详细信息 */
    public ServiceRet<T> setRet(RetInfo ret, String detail) {
        return setRet(ret.getRetCode(), ret.getRetMsg(), detail);
    }
    
    public ServiceRet<T> setRet(RetInfo ret) {
        return setRet(ret.getRetCode(), ret.getRetMsg(), "");
    }

    public ServiceRet<T> setRet(RetInfo ret, Throwable t) {
        setRet(ret, !BaseUtil.isBlank(t.getMessage()) ? t.getMessage() : t.toString());
        return this;
    }
    
    /**
     * 转换map，并自动添加content和errors节点(非空时)
     * @param
     * @return
     */
    public Map<String, Object> toObjMap() {
        Map<String, Object> map = BaseUtil.toMap(retCode, retMsg);
        if(null != content){
            map.put("content", content);
        }

        if(!BaseUtil.isEmpty(errors)){
            map.put("errors", errors);
        }

        return map;
    }

    /**
     * 转换map，不添加content和errors节点
     * @return
     */
    public Map<String, Object> toObjMap0() {
        return BaseUtil.toMap(retCode, retMsg);
    }

    /** 转成json，并添加content节点 */
    public String toJson() {
        return BaseUtil.toJson(toObjMap());
    }

    /** 转成json，不添加content节点 */
    public String toJson0() {
        return BaseUtil.toJson(toObjMap0());
    }

    @Override
    public String getRetCode() {
        return retCode;
    }

    public ServiceRet<T> setRetCode(String retCode) {
        this.retCode = retCode;
        return this;
    }

    @Override
    public String getRetMsg() {
        return retMsg;
    }

    public ServiceRet<T> setRetMsg(String retMsg) {
        this.retMsg = retMsg;
        return this;
    }

    public T getContent() {
        return content;
    }

    public ServiceRet<T> setContent(T content) {
        this.content = content;
        return this;
    }
    
    @Override
    public String toString() {
        return "retCode: " + retCode + ", retMsg:" + retMsg;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public ServiceRet<T> addError(String key, String errorInfo) {
        if (null == errors) {   // lazy init
            errors = new HashMap<>();
        }
        
        errors.put(key, errorInfo);
        return this;
    }

    public ServiceRet<T> setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }
}
