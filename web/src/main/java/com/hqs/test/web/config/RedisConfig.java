package com.hqs.test.web.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
@DisconfUpdateService(classes = {RedisConfig.class})
public class RedisConfig implements IDisconfUpdate {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(RedisConfig.class);

    // 代表连接地址
    private String host;

    // 代表连接port
    private int port;

    /**
     * 地址, 分布式文件配置
     *
     * @return
     */
    @DisconfFileItem(name = "redis.host", associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 端口, 分布式文件配置
     *
     * @return
     */
    @DisconfFileItem(name = "redis.port", associateField = "port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 每次更新分布式配置都会调reload方法
     *
     * @throws Exception
     */
    @Override
    public void reload() throws Exception {
        log.info(">>>>>>>>>host: " + host);
    }

}
