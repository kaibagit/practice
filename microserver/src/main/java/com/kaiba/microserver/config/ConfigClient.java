package com.kaiba.microserver.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by luliru on 2017/5/22.
 */
@SpringBootApplication
public class ConfigClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClient.class).web(true).run(args);
    }
}
