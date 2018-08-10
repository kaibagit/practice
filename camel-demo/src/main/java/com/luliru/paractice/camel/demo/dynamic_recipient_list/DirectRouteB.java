package com.luliru.paractice.camel.demo.dynamic_recipient_list;//package com.kaiba.demo.camel.dynamic_recipient_list;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by luliru on 2017/11/20.
 */
public class DirectRouteB extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // 第二个路由和第三个路由的代码都相似
        // 唯一不同的是类型
        from("direct:directRouteB")
                .to("log:DirectRouteB?showExchangeId=true");
    }
}
