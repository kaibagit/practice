package com.kaiba.microserver.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by luliru on 2016/8/29.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Provider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Provider.class).web(true).run(args);
    }

}
