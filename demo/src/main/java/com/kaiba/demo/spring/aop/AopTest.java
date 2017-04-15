package com.kaiba.demo.spring.aop;

import com.kaiba.demo.spring.aop.servcie.UserServcie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by luliru on 2017/4/12.
 */
@Component
public class AopTest {

    @Resource
    private UserServcie userServcie;

    public UserServcie getUserServcie() {
        return userServcie;
    }

    public void setUserServcie(UserServcie userServcie) {
        this.userServcie = userServcie;
    }

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/aop/spring.xml"});
        AopTest bean = ctx.getBean(AopTest.class);
        bean.toString();
    }
}
