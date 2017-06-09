package com.luliru.dubbo.callbacks;

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
public class Customer {

    private static Logger logger = LoggerFactory.getLogger(Customer.class);

    public static void main(String[] args) throws TException, BizException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:customer_callback.xml");
        DemoService service = ctx.getBean(DemoService.class);
        System.out.println(service.get(1L));
        System.in.read();
    }
}
