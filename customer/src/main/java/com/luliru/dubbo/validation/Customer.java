package com.luliru.dubbo.validation;

import com.luna.demo.service.BizException;
import com.luna.demo.service.Member;
import com.luna.demo.service.MemberService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2016/11/5.
 */
public class Customer {

    private static Logger logger = LoggerFactory.getLogger(Customer.class);

    public static void main(String[] args) throws TException, BizException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:validation_customer.xml");
        OrderService orderService = ctx.getBean(OrderService.class);
        orderService.save(new Parameter());
    }
}
