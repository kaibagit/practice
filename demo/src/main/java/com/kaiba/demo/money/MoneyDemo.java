package com.kaiba.demo.money;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * MoneyDemo
 * Created by luliru on 2019-06-13.
 */
public class MoneyDemo {

    public static void main(String[] args){
        rmb();
    }

    public static void rmb(){
        Money money = Money.parse("CNY 23.87");
        int major = money.getAmountMajorInt();
        int minor = money.getAmountMinorInt();
        System.out.println("major:"+major);
        System.out.println("minor:"+minor);

        Money money1 = Money.of(CurrencyUnit.of("CNY"),new BigDecimal("23.87"));
        System.out.println(money1);
    }

    public static void dollar(){
        // create a monetary value
        Money money = Money.parse("USD 23.87");

        // add another amount with safe double conversion
        CurrencyUnit usd = CurrencyUnit.of("USD");
        money = money.plus(Money.of(usd, 12.43d));

        // subtracts an amount in dollars
        money = money.minusMajor(2);

        // multiplies by 3.5 with rounding
        money = money.multipliedBy(3.5d, RoundingMode.DOWN);

//        // compare two amounts
//        boolean bigAmount = money.isGreaterThan(dailyWage);
//
//        // convert to GBP using a supplied rate
//        BigDecimal conversionRate = ...;  // obtained from code outside Joda-Money
//        Money moneyGBP = money.convertedTo(CurrencyUnit.GBP, conversionRate, RoundingMode.HALF_UP);
//
//        // use a BigMoney for more complex calculations where scale matters
//        BigMoney moneyCalc = money.toBigMoney();
    }
}
