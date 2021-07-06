package com.luliru.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * MySyncTransactionListener
 * Created by luliru on 7/6/21.
 */
@Slf4j
@Component
public class MySyncTransactionListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hanldeOrderCreatedEvent(MyTransactionEvent event) {
        log.info("transactionEventListener start");
        log.info("event : " + event.getSource());
        log.info("transactionEventListener finish");
    }
}
