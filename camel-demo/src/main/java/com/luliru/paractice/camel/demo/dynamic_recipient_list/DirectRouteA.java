package com.luliru.paractice.camel.demo.dynamic_recipient_list;//package com.kaiba.demo.camel.dynamic_recipient_list;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by luliru on 2017/11/20.
 */


public class DirectRouteA extends RouteBuilder {

    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8282/dynamicCamel")
                .setExchangePattern(ExchangePattern.InOnly)
                .recipientList().jsonpath("$.data.routeName").delimiter(",")
                .end()
                .process(new OtherProcessor());
    }
}
