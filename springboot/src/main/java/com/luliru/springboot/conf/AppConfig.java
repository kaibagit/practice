package com.luliru.springboot.conf;

import com.luliru.springboot.beans.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luliru on 2016/12/29.
 */
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        MyService service = new MyService();
        System.out.println("config:"+service);
        return service;
    }

}
