package com.kaiba.demo.disconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luliru on 2017/5/20.
 */
@Service
public class SimpleRedisService implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(SimpleRedisService.class);

    /**
     * 分布式配置
     */
    @Autowired
    private JedisConfig jedisConfig;

    /**
     * 关闭
     */
    public void destroy() throws Exception {
        log.info("destroy=>"+jedisConfig);
    }

    /**
     * 进行连接
     */
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet=>"+jedisConfig);
    }
}
