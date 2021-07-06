package com.luliru.springboot.listener;

import org.springframework.context.ApplicationEvent;

/**
 * MyTransactionEvent
 * Created by luliru on 7/5/21.
 */
public class MyTransactionEvent extends ApplicationEvent {

    public MyTransactionEvent(String source) {
        super(source);
    }
}
