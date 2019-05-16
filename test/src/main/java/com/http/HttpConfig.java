package com.http;

import java.net.InetAddress;
import java.util.Map;

/**
 * http配置信息
 * @author xulin
 * 2016年11月29日
 */
public class HttpConfig implements Cloneable{

    /** 超时时间 **/
    private static final int SOCKET_TIMEOUT   = 10 * BaseConsts.SECOND;
    private static final int CONNECT_TIMEOUT = 10 * BaseConsts.SECOND;
    private static final int READ_TIMEOUT    = 30 * BaseConsts.SECOND;
    
    private int socketTimeout = SOCKET_TIMEOUT; // socket超时
    private int connTimeout = CONNECT_TIMEOUT;  // 连接超时
    private int readTimeout = READ_TIMEOUT;     // 读取超时（下载）
    
    private InetAddress bindIp;                 // 绑定的ip


    /** 自定义请求header */
    private Map<String, String> headers;


    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public InetAddress getBindIp() {
        return bindIp;
    }

    public void setBindIp(InetAddress bindIp) {
        this.bindIp = bindIp;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public HttpConfig clone() {
        try {
            return (HttpConfig)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
