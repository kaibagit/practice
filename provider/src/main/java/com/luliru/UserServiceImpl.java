package com.luliru;

import com.kaiba.BizException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by kaiba on 2016/5/6.
 */
@Service
public class UserServiceImpl implements UserService {

    public String hello(String id) {
//        if(true){
//            throw new BizException("error");
//        }
        return "hello "+id;
    }

    @Override
    public String unstableHello(String id,Boolean unstable) {
        if(!unstable){
            Random random = new Random();
            int i = random.nextInt(1000);
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello "+id;
    }

    @Override
    public List<String> fingByIds(Long... ids) {
        System.out.println("ids size = "+ids.length);
        return null;
    }

    @Override
    public User create(User user,int source) {
        user.setId(Long.valueOf(source));
        user.setBirthday(new Date());
        System.out.println(user.getUsername());
        return user;
    }
}
