package com.luliru.practice.nacos.dubbo;

import org.springframework.stereotype.Component;

/**
 * Created by luliru on 2019/1/29.
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public String hello(String id) {
        return "hello:"+id;
    }
}
