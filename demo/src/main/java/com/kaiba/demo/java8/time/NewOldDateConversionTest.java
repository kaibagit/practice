package com.kaiba.demo.java8.time;

import java.time.*;
import java.util.Date;

/**
 * Created by luliru on 2017/6/17.
 */
public class NewOldDateConversionTest {

    public static void main(String[] args){
        LocalDateToDate();
    }

    // 04. java.time.LocalDateTime --> java.util.Date
    public static void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }

    // java.time.LocalDate --> java.util.Date
    public static void LocalDateToDate(){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);
    }
}
