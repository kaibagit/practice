package com.kaiba.microserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luliru on 2017/5/22.
 */
@RefreshScope
@RestController
class TestController {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @RequestMapping("/status")
    public String status() {
        return this.jdbcUrl;
    }
}
