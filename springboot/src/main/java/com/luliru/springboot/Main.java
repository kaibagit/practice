package com.luliru.springboot;

import com.luliru.springboot.beans.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by luliru on 2016/12/29.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.luliru")
@SpringBootApplication
public class Main {

    @Bean
    public MyService myService() {
        MyService service = new MyService();
        System.out.println("in:"+service);
        return service;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        MyService service = applicationContext.getBean(MyService.class);
        service.hello();
    }

}
