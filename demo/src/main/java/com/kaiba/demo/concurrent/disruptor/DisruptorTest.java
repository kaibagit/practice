package com.kaiba.demo.concurrent.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

/**
 * DisruptorTest
 * Created by luliru on 2019-05-31.
 */
public class DisruptorTest {

    private final static Logger logger = LoggerFactory.getLogger(DisruptorTest.class);

    /**
     * 消息事件类
     */
    public static class MessageEvent{
        /**
         * 原始消息
         */
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 消息事件工厂类
     */
    public static class MessageEventFactory implements EventFactory<MessageEvent> {
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * 消息转换类，负责将消息转换为事件
     */
    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String> {
        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * 消费者线程工厂类
     */
    public static class MessageThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }

    /**
     * 消息事件处理类，这里只打印消息
     */
    public static class MessageEventHandler implements EventHandler<MessageEvent> {
        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
            logger.info(messageEvent.getMessage());
        }
    }

    /**
     * 异常处理类
     */
    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent>{
        @Override
        public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
            ex.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            ex.printStackTrace();

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            ex.printStackTrace();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();

        EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
        while(true){
            ringBuffer.publishEvent(translator,"Hello:"+System.currentTimeMillis());
            Thread.sleep(1000L);
        }
    }
}