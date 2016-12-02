package com.kaiba.demo.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by luliru on 2016/8/16.
 */
public class MySimpleJob extends QuartzJobBean {    //该对象会被持久化到db

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("----- do something -----");
    }
}
