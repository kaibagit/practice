package com.luliru.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * MyTransactionListener
 * Created by luliru on 7/5/21.
 */
@Slf4j
@Component
public class MyAsyncTransactionListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hanldeOrderCreatedEvent(MyTransactionEvent event) {
        log.info("transactionEventListener start");
        log.info("event : " + event.getSource());
        log.info("transactionEventListener finish");
    }
}
