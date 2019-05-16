package com.http;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StringBuilder包装器
 * @author devin
 * @date 2016.08
 */
public class ContentBuilder {
    
    /** 用StringBuilder存放内容 */
    private StringBuilder sb = new StringBuilder();
    
    /** 换行符，默认为 \r\n */
    private String cr = BaseConsts.CRLF;

    /** 日期格式化格式 */
    private String dateFmt = "yyyy-MM-dd HH:mm:ss";
    
    public ContentBuilder() {}
    
    public ContentBuilder(String s) {
        sb.append(s);
    }

    public ContentBuilder setDateFmt(String dateFmt) {
        this.dateFmt = dateFmt;
        return this;
    }

    // String
    public ContentBuilder add(String s) {
        sb.append(s);
        return this;
    }
    
    public ContentBuilder add(Object o) {
        return add(String.valueOf(o));
    }
    
    public ContentBuilder add(String format, Object... args) {
        return add(String.format(format, args));
    }
    
    // BigDecimal格式化为两位小数点
    public ContentBuilder add(BigDecimal num) {
        return add(String.format("%.2f", num));
    }
    
    // 日期格式化为yyyy-MM-dd hh:mm:ss
    public ContentBuilder add(Date date) {
        return add(BaseUtil.formatDate(date, dateFmt));
    }
    
    /** 换行 */
    public ContentBuilder cr() {
        return add(cr);
    }

    /**
     * 设置长度
     * @param newLength 新的长度
     */
    public void setLength(int newLength) {
        sb.setLength(newLength);
    }
    
    /**
     * 换多行
     * @param n 换行个数
     * @return
     */
    public ContentBuilder cr(int n) {
        for (int i = 0; i++ < n;) { 
            add(cr);
        }
        
        return this;
    }
    
    /** 清空内容 */
    public ContentBuilder clear() {
        sb.setLength(0);
        return this;
    }
    
    /** 设置自定义换行符 */
    public ContentBuilder setCr(String cr) {
        this.cr = cr;
        return this;
    }
    
    /**
     * 返回长度（字符个数)
     */
    public int length() {
        return sb.length();
    }
    
    @Override
    public String toString() {
        return sb.toString();
    }
}
