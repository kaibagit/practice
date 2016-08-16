package com.kaiba.demo.quartz;

import org.quartz.JobExecutionContext;

/**
 * Created by luliru on 2016/8/15.
 */
public class DailyBillJob {

    public void execute(JobExecutionContext context){
        System.out.println("===== do something =====");
    }
}
