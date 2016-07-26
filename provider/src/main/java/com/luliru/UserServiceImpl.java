package com.luliru;

import com.kaiba.BizException;
import org.springframework.stereotype.Service;

/**
 * Created by kaiba on 2016/5/6.
 */
@Service
public class UserServiceImpl implements UserService {

    public String hello(String id) {
        if(true){
            throw new BizException("error");
        }
        return "hello "+id;
    }
}
