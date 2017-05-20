package com.kaiba.demo.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by luliru on 2017/5/20.
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
public class JedisConfig {

    private static final Logger log = LoggerFactory.getLogger(JedisConfig.class);

    // 代表连接地址
    private String host;

    // 代表连接port
    private int port;

    /**
     * 地址
     *
     * @return
     */
    @DisconfFileItem(name = "redis.host", associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
        log.info("setHost="+host);
    }

    /**
     * 端口
     *
     * @return
     */
    @DisconfFileItem(name = "redis.port", associateField = "port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        log.info("setPort="+port);
    }

    @Override
    public String toString() {
        return "JedisConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
