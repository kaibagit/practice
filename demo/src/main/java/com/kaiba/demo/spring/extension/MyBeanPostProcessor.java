package com.kaiba.demo.spring.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by luliru on 2016/10/14.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
    }

    //实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
    @Override
    public Object postProcessBeforeInitialization(Object bean, String arg1)
            throws BeansException {
        System.out.println("bean处理器：bean创建之前..");

        return bean;
    }

    //实例化、依赖注入、初始化完毕时执行
    @Override
    public Object postProcessAfterInitialization(Object bean, String arg1)
            throws BeansException {
        System.out.println("bean处理器：bean创建之后..");
        return bean;
    }


}
