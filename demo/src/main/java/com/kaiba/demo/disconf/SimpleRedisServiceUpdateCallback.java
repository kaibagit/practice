package com.kaiba.demo.disconf;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luliru on 2017/5/20.
 */
@Service
@Scope("singleton")
@DisconfUpdateService(classes = {JedisConfig.class}, itemKeys = {"discountRate"})
public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {

    private static final Logger log = LoggerFactory.getLogger(SimpleRedisServiceUpdateCallback.class);

    @Resource
    private JedisConfig jedisConfig;

    /**
     *
     */
    public void reload() throws Exception {
        log.info("reload=>"+jedisConfig);
    }

}
