package com.luliru.springboot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * TransactionalEventListenerTest
 * Created by luliru on 7/5/21.
 */
@EnableAsync
@EnableAutoConfiguration
@SpringBootApplication
public class TransactionalEventListenerTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(TransactionalEventListenerTest.class, args);
        FooService service = applicationContext.getBean(FooService.class);
        service.insertFoo_1();
        service.insertFoo_2();
    }
}
