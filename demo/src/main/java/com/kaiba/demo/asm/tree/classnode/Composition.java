package com.kaiba.demo.asm.tree.classnode;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/7/25.
 */
public class Composition {

    public static void main(String[] args){
        ClassNode cn = new ClassNode(ASM5);
        ClassReader cr = null;
        cr.accept(cn, 0);

        // here transform cn as you want

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] b = cw.toByteArray();
    }
}
