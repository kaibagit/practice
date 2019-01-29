package com.luliru.practice.nacos.spring;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(NacosConfigApplication.class, args);
        while (true){
            Bean bean = ctx.getBean(Bean.class);
            System.out.println(bean.isUseLocalCache());
            Thread.sleep(5000L);
        }
    }
}
