package com.kaiba.microserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by luliru on 2016/8/29.
 */
@EnableEurekaServer
@SpringBootApplication
public class Registry {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Registry.class).web(true).run(args);
    }
}
