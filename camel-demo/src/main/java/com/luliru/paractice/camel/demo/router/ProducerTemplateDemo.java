package com.luliru.paractice.camel.demo.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

/**
 * ProducerTemplateDemo
 * Created by luliru on 2019-11-06.
 */
@Slf4j
public class ProducerTemplateDemo {

    public static void main(String[] args) throws Exception {
        ModelCamelContext ctx = new DefaultCamelContext();
        ctx.start();

        ctx.addRoutes(new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:start")
                        .process(new Processor(){

                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Thread.sleep(3000L);
                                throw new RuntimeException("ouch");
                            }
                        })
                        .to("log:DirectRouteA?showExchangeId=true");
            }
        });

        ProducerTemplate template = ctx.createProducerTemplate();
        template.sendBody("direct:start?timeout=100","Hello");
        log.info("sendBody success.");
    }
}
