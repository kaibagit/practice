package com.kaiba.demo.asm.tree.classnode;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/7/25.
 */
public class MyClassNodeAdapter extends ClassNode {

    public MyClassNodeAdapter(ClassVisitor cv) {
        super(ASM5);
        this.cv = cv;
    }
    @Override public void visitEnd() {
        // put your transformation code here
        accept(cv);
    }

    public static void main(String[] args){
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor ca = new MyClassNodeAdapter(cw);
        ClassReader cr = null;
        cr.accept(ca, 0);
        byte[] b = cw.toByteArray();

    }
}
