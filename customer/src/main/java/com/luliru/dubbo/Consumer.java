package com.luliru.dubbo;

import com.luliru.practice.api.provider.UserService;
import com.luna.demo.service.BizException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by luliru on 2016/11/5.
 */
public class Consumer {

    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws TException, BizException, IOException, InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:customer.xml");
        UserService userService = ctx.getBean(UserService.class);
        while (true){
            try{
                userService.throwUnknownSubException();
            }catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(5000L);
        }

//        System.out.println(userService.hello("dd"));
    }
}
