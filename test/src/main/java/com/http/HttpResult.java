package com.http;

import java.io.File;
import org.apache.http.Header;
import org.apache.http.HttpStatus;

/**
 * http请求结果
 * @author devin.xu
 * @date 2016.11
 */
public class HttpResult {
    
    /** 异常的返回码 */
    public static final int EXCEPTION = -1;
    
    /** 状态码,异常时=-1 */
    private int statusCode = EXCEPTION;

    /** 响应headers */
    private Header[] headers;
    
    /** 消息内容（异常时=e.toString() */
    private String content = "";

    /** 下载的文件（下载文件时使用） */
    private File file;

    /**
     * 按名字提取第一个header
     * @param name 要提取的header名字
     * @return header
     */
    public Header getFirstHeader(String name) {
        if (BaseUtil.notEmpty(headers)) {
            for (Header header : headers) {
                if (header.getName().equalsIgnoreCase(name)) {
                    return header;
                }
            }
        }

        return null;
    }

    /**
     * 按名字提取第一个header
     * @param name 要提取的header名字
     * @return header
     */
    public String getFirstHeaderValue(String name) {
        Header h = getFirstHeader(name);
        return h == null ? "" : h.getValue();
    }

    public boolean isSuccess() {
        return HttpStatus.SC_OK == this.statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "statusCode:" + statusCode
                + ", content:" + content
                + (file != null ? ", file:" + file : "");
    }
}