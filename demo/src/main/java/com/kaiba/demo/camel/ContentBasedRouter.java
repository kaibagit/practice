package com.kaiba.demo.camel;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.language.JsonPathExpression;

/**
 * 使用条件选择进行路由编排
 * @author yinwenjie
 */
public class ContentBasedRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // 这是一个JsonPath表达式，用于从http携带的json信息中，提取orgId属性的值
        JsonPathExpression jsonPathExpression = new JsonPathExpression("$.data.orgId");
        jsonPathExpression.setResultType(String.class);

        // 通用使用http协议接受消息
        from("jetty:http://0.0.0.0:8282/choiceCamel")
                // 首先送入HttpProcessor，
                // 负责将exchange in Message Body之中的stream转成字符串
                // 当然，不转的话，下面主要的choice操作也可以运行
                .process(new HttpProcessor())
                // 将orgId属性的值存储 exchange in Message的header中，以便后续进行判断
                .setHeader("orgId", jsonPathExpression)
                .choice()
                // 当orgId == yuanbao，执行OtherProcessor
                // 当orgId == yinwenjie，执行OtherProcessor2
                // 其它情况执行OtherProcessor3
                .when(header("orgId").isEqualTo("yuanbao"))
                .process(new OtherProcessor())
                .when(header("orgId").isEqualTo("yinwenjie"))
                .process(new OtherProcessor2())
                .otherwise()
                .process(new OtherProcessor3())
                // 结束
                .endChoice();
    }

    public class HttpProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {

        }
    }

    public class OtherProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            Message message = exchange.getIn();
            String body = message.getBody().toString();

            // 存入到exchange的out区域
            if(exchange.getPattern() == ExchangePattern.InOut) {
                Message outMessage = exchange.getOut();
                outMessage.setBody(body + " || 被OtherProcessor处理");
            }
        }
    }

    public class OtherProcessor2 implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            Message message = exchange.getIn();
            String body = message.getBody().toString();

            // 存入到exchange的out区域
            if(exchange.getPattern() == ExchangePattern.InOut) {
                Message outMessage = exchange.getOut();
                outMessage.setBody(body + " || 被OtherProcessor2处理");
            }
        }
    }

    public class OtherProcessor3 implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            Message message = exchange.getIn();
            String body = message.getBody().toString();

            // 存入到exchange的out区域
            if(exchange.getPattern() == ExchangePattern.InOut) {
                Message outMessage = exchange.getOut();
                outMessage.setBody(body + " || 被OtherProcessor3处理");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 这是camel上下文对象，整个路由的驱动全靠它了。
        ModelCamelContext camelContext = new DefaultCamelContext();
        // 启动route
        camelContext.start();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new ContentBasedRouter());

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (ContentBasedRouter.class) {
            ContentBasedRouter.class.wait();
        }
    }
}
