package com.kaiba.demo.disconf;

import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by luliru on 2017/5/20.
 */
@Component
public class MyUpdatePipeline implements IDisconfUpdatePipeline {

    private static final Logger log = LoggerFactory.getLogger(MyUpdatePipeline.class);

    public void reloadDisconfFile(String key, String filePath) throws Exception {
        log.info(key + " : " + filePath);
    }

    public void reloadDisconfItem(String key, Object content) throws Exception {
        log.info(key + " : " + content);
    }
}
