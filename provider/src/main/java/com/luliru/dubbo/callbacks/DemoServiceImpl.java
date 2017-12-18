package com.luliru.dubbo.callbacks;

import com.luliru.practice.api.dto.User;
import org.springframework.stereotype.Service;

/**
 * Created by luliru on 2017/6/7.
 */
@Service
public class DemoServiceImpl implements DemoService {

    int i = 0;

    @Override
    public User get(long id) {
        if(i%2 == 1){
            throw new RuntimeException("");
        }
        User user = new User();
        user.setId(id);
        i++;
        return user;
    }
}
