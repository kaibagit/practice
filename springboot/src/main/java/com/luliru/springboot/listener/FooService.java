package com.luliru.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * FooService
 * Created by luliru on 7/5/21.
 */
@Slf4j
@Service
public class FooService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertFoo_1() {
        log.info("insert begin");
        jdbcTemplate.update("insert into account(mobile) values('15158170147')");
        log.info("insert finish");
        // Spring 4.2 之前
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        log.info("send email");
                    }
                });
        log.info("tx commit");
    }

    @Transactional
    public void insertFoo_2() {
        log.info("insert begin");
        jdbcTemplate.update("insert into account(mobile) values('15158170147')");
        log.info("insert finish");
        applicationContext.publishEvent(new MyTransactionEvent("insertFoo_2"));
        log.info("publish finish");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
