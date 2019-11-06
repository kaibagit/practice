package com.luliru.paractice.camel.demo.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

/**
 * Created by luliru on 2017/11/20.
 */
public class DynamicRecipientList {

    public static void main(String[] args) throws Exception {
        // 这是camel上下文对象，整个路由的驱动全靠它了。
        ModelCamelContext camelContext = new DefaultCamelContext();
        // 启动route
        camelContext.start();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new DirectRouteA());
        camelContext.addRoutes(new DirectRouteB());
        camelContext.addRoutes(new DirectRouteC());

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (DynamicRecipientList.class) {
            DynamicRecipientList.class.wait();
        }
    }

}

@Slf4j
class DirectRouteA extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8282/dynamicCamel")
                .setExchangePattern(ExchangePattern.InOnly)
                .recipientList().jsonpath("$.data.routeName").delimiter(",")
                .end()
                .process(new OtherProcessor());
    }

    class OtherProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            log.info(exchange.toString());
        }
    }
}

class DirectRouteB extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // 第二个路由和第三个路由的代码都相似
        // 唯一不同的是类型
        from("direct:directRouteB")
                .to("log:DirectRouteB?showExchangeId=true");
    }
}

class DirectRouteC extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // 第二个路由和第三个路由的代码都相似
        // 唯一不同的是类型
        from("direct:directRouteC")
                .to("log:DirectRouteC?showExchangeId=true");
    }
}
