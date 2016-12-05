package com.kaiba.demo.joda;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * Created by luliru on 2016/12/5.
 */
public class PeriodTest {

    public static void main(String[] args){
        DateTime start = new DateTime();
        DateTime end = new DateTime().plusDays(1);
        Period p = new Period(start,end,PeriodType.days()); //end - start 时间差，并且舍弃小数点后，所以结果为0
        int day = p.getDays();
        System.out.println(day);
    }
}
