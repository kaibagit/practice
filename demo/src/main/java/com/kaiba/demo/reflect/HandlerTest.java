package com.kaiba.demo.reflect;

import java.lang.reflect.Proxy;

/**
 * Created by luliru on 2016/11/24.
 */
public class HandlerTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        MemberSercieImpl impl = new MemberSercieImpl();
        MemberSercie sercie = (MemberSercie) Proxy.newProxyInstance(MemberSercie.class.getClassLoader(),new Class[]{MemberSercie.class},new MyHandler());
        sercie.getById(1);
    }

}
