package com.luliru.paractice.camel.demo.routebuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMessage;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * Created by luliru on 2017/11/15.
 */
public class HelloRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // 在本代码段之下随后的说明中，会详细说明这个构造的含义
        from("jetty:http://0.0.0.0:8282/doHelloWorld")
                .process(new HelloHttpProcessor())
                .to("log:helloworld?showExchangeId=true");
    }

    public static void main(String[] args) throws Exception {
        // 这是camel上下文对象，整个路由的驱动全靠它了。
        ModelCamelContext camelContext = new DefaultCamelContext();
        // 启动route
        camelContext.start();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new HelloRouteBuilder());

        /*
         * ==========================
         * 为什么我们先启动一个Camel服务
         * 再使用addRoutes添加编排好的路由呢？
         * 这是为了告诉各位读者，Apache Camel支持动态加载/卸载编排的路由
         * 这很重要，因为后续设计的Broker需要依赖这种能力
         * ==========================
         * */

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (HelloRouteBuilder.class) {
            HelloRouteBuilder.class.wait();
        }
    }
}

/**
 * 这个处理器用来完成输入的json格式的转换
 * @author yinwenjie
 */
class HelloHttpProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        HttpMessage message = (HttpMessage)exchange.getIn();
        InputStream bodyStream =  (InputStream)message.getBody();

        HttpServletRequest request = message.getRequest();

        String reply = request.getRemoteHost()+":"+request.getRemotePort();

        Message outMessage = exchange.getOut();
        outMessage.setBody(reply);
    }
}
