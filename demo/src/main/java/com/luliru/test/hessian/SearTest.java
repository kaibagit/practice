package com.luliru.test.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.kaiba.demo.beans.Member;

import java.io.*;

/**
 * Created by kaiba on 2016/6/21.
 */
public class SearTest {

    public static void main(String[] args) throws IOException {
        Member m = new Member();
        m.setId(1L);
        m.setName("luliru");
        m.setSex(true);

        //序列化
        FileOutputStream os = new FileOutputStream("member.txt");
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(m);

        //反序列化
        FileInputStream is = new FileInputStream("member.txt");
        HessianInput hi = new HessianInput(is);
        Object o = hi.readObject();
        System.out.println(o);
    }

}
