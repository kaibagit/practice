package com.kaiba.demo.asm.tools;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by luliru on 2017/8/4.
 */
public class TraceClassTest {

    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cv, 0);
    }
}
