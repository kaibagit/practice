package com.kaiba.demo.asm.core.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by luliru on 2017/7/25.
 */
public class GeneratingMethods {

    public static void main(String[] args){
        //getF()
        MethodVisitor mv = null;
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "pkg/Bean", "f", "I");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();


        //checkAndSetF(int f)
        mv.visitCode();
        mv.visitVarInsn(ILOAD, 1);
        Label label = new Label();
        mv.visitJumpInsn(IFLT, label);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitFieldInsn(PUTFIELD, "pkg/Bean", "f", "I");
        Label end = new Label();
        mv.visitJumpInsn(GOTO, end);
        mv.visitLabel(label);
        mv.visitFrame(F_SAME, 0, null, 0, null);
        mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL,
                "java/lang/IllegalArgumentException", "<init>", "()V");
        mv.visitInsn(ATHROW);
        mv.visitLabel(end);
        mv.visitFrame(F_SAME, 0, null, 0, null);
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
    }
}
