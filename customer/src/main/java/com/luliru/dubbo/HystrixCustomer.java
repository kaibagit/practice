package com.luliru.dubbo;

import com.luliru.practice.api.provider.UserService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luna.demo.service.BizException;

/**
 * Created by luliru on 2017/3/17.
 */
public class HystrixCustomer {

    private static Logger logger = LoggerFactory.getLogger(ThriftCustomer.class);

    public static void main(String[] args) throws TException, BizException, InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:hystrix_customer.xml");
        UserService userService = ctx.getBean(UserService.class);
        int successCount = 0;
        for(int i=0;i<20;i++){
            try{
                System.out.println(userService.unstableHello("",false));
                successCount ++;
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        try{
            System.out.println(userService.unstableHello("",false));
        }catch (Exception e){
            e.printStackTrace();
        }
        Thread.sleep(20000);
        System.out.println(userService.unstableHello("",true));

        logger.info("success count :"+successCount);
    }

}
