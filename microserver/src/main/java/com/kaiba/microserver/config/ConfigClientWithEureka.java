package com.kaiba.microserver.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by luliru on 2017/5/23.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientWithEureka {

    public static void main(String[] args){
        new SpringApplicationBuilder(ConfigClientWithEureka.class).web(true).run(args);
    }
}
