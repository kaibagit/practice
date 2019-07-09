package com.luliru.test.hessian.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by luliru on 2018/3/14.
 */
public class TestObject implements Serializable {

    private int a;
    private String b;
    private boolean c;
    private float d;
    private long e;
    private List f;
    private Map g;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    public long getE() {
        return e;
    }

    public void setE(long e) {
        this.e = e;
    }

    public List getF() {
        return f;
    }

    public void setF(List f) {
        this.f = f;
    }

    public Map getG() {
        return g;
    }

    public void setG(Map g) {
        this.g = g;
    }
}
