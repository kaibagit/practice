package com.luliru.springboot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luliru.springboot.beans.MyService;

/**
 * Created by luliru on 2016/12/29.
 */
@Configuration
public class AppConfig2 {

    @Bean
    public MyService myService() {
        MyService service = new MyService();
        System.out.println("config2:"+service);
        return service;
    }

}
