package com.luliru.paractice.camel.demo.dynamic_recipient_list;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

/**
 * Created by luliru on 2017/11/20.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 这是camel上下文对象，整个路由的驱动全靠它了。
        ModelCamelContext camelContext = new DefaultCamelContext();
        // 启动route
        camelContext.start();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes((new DynamicCamel()).new DirectRouteA());
        camelContext.addRoutes((new DynamicCamel()).new DirectRouteB());
        camelContext.addRoutes((new DynamicCamel()).new DirectRouteC());

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (DynamicCamel.class) {
            DynamicCamel.class.wait();
        }
    }

}
