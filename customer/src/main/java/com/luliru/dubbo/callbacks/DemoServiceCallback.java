package com.luliru.dubbo.callbacks;

import com.luliru.User;
import org.springframework.stereotype.Service;

/**
 * Created by luliru on 2017/6/7.
 */
@Service
public class DemoServiceCallback {

    public void onreturn(User msg, Long id){
        System.out.println("onreturn:" + msg+"-"+id);
    };
    public void onthrow(Throwable ex, Long id){
        System.out.println("onthrow:" + ex+"-"+id);
    };
}
