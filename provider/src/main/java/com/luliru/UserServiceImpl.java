package com.luliru;

import com.luliru.practice.api.dto.User;
import com.luliru.practice.api.exception.ApiException;
import com.luliru.practice.api.exception.UnknownSubException;
import com.luliru.practice.api.provider.UserService;
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
    public User create(User user, int source) {
        user.setId(Long.valueOf(source));
        user.setBirthday(new Date());
        System.out.println(user.getUsername());
        return user;
    }

    @Override
    public User createDynamically(User user, int srouce) {
        if(srouce < 0){
            try {
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("客户端异常");
        }
        user.setId(Long.valueOf(srouce));
        user.setBirthday(new Date());
        System.out.println(user.getUsername());
        return user;
    }

    @Override
    public void throwInnerException(String id) {
        throw new InnerException();
    }

    @Override
    public void throwUnknownSubException() throws ApiException {
        throw new UnknownSubException();
    }
}
