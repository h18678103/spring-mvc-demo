package com.hqs.test.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Component
public class RedisConfig2 {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(RedisConfig2.class);

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
