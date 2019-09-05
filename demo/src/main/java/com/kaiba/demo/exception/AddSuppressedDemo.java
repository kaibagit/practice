package com.kaiba.demo.exception;

/**
 * AddSuppressedDemo
 * Created by luliru on 2019-09-05.
 */
public class AddSuppressedDemo {

    public static void main(String[] args){
        now();
    }

    private static void inThePast(){
        try {
            Integer.parseInt("Hello");
        } catch (NumberFormatException e1) {
            throw e1;
        } finally {
            try {
                int result = 2 / 0;
            } catch (ArithmeticException e2) {
                throw e2;
            }
        }
    }

    private static void now(){
        Exception e = null;
        try {
            Integer.parseInt("Hello");
        } catch (NumberFormatException e1) {
            e = e1;
        } finally {
            try {
                int result = 2 / 0;
            } catch (ArithmeticException e2) {
                if(e != null){
                    e2.addSuppressed(e);
                }
                throw e2;
            }
        }
    }

}
