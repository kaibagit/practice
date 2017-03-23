package com.kaiba.microserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by luliru on 2016/8/29.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Server.class).web(true).run(args);
    }

}
