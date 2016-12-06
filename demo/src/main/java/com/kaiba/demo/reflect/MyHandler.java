package com.kaiba.demo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by luliru on 2016/11/24.
 */
public class MyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class[] arr = new Class[args.length];
        for(int i=0; i< args.length; i++){
            arr[0] = args[i].getClass();
        }
        MemberSercie impl = new MemberSercieImpl();
        impl.getClass().getMethod(method.getName(),arr);
        return null;
    }
}
