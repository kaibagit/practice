package com.kaiba.springcloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by luliru on 2017/5/23.
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerWithEureka {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerWithEureka.class).web(true).run(args);
    }

}
