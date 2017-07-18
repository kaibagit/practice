package com.luliru.test.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by luliru on 2017/7/18.
 */
public class BaseTypeTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream os = new FileOutputStream("hessian.txt");
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject("hello");
        ho.writeObject("world");

        FileInputStream is = new FileInputStream("hessian.txt");
        HessianInput hi = new HessianInput(is);
        Object o = hi.readObject();
        System.out.println(o);
        o = hi.readObject();
        System.out.println(o);
    }
}
