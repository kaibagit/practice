package com.kaiba.demo.asm.core.method;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/8/7.
 */
public class MethodVisitSeq extends MethodVisitor {


    public MethodVisitSeq(int i) {
        super(i);
    }

    public MethodVisitSeq(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    public void visitLineNumber(int i, Label label) {
        System.out.println("visitLineNumber:"+i);
        super.visitLineNumber(i, label);
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {
        System.out.println("visitMethodInsn:"+name+desc);
        super.visitMethodInsn(opcode,owner,name,desc,isInterface);
    }

    public void visitInvokeDynamicInsn(String name, String desc, Handle handle, Object... objects) {
        System.out.println("visitInvokeDynamicInsn:"+name+desc);
        super.visitInvokeDynamicInsn(name,desc,handle,objects);
    }

    public void visitCode() {
        System.out.println("visitCode");
        super.visitCode();
    }

    public void visitTryCatchBlock(Label start, Label end, Label handler,
                                   String type) {
        System.out.println("visitTryCatchBlock");
        super.visitTryCatchBlock(start,end,handler,type);
    }

    public void visitLabel(Label label) {
        System.out.println("visitLabel:"+label);
        super.visitLabel(label);
    }

    public void visitFrame(int type, int nLocal, Object[] local, int nStack,
                           Object[] stack) {
        System.out.println("visitFrame");
        super.visitFrame(type,nLocal,local,nStack,stack);
    }

    public void visitInsn(int opcode) {
        System.out.println("visitInsn");
        super.visitInsn(opcode);
    }

    public void visitIntInsn(int opcode, int operand) {
        System.out.println("visitIntInsn");
        super.visitIntInsn(opcode,operand);
    }

    public void visitLocalVariable(String name, String desc, String signature,
                                   Label start, Label end, int index) {
        System.out.println("visitLocalVariable");
        super.visitLocalVariable(name,desc,signature,start,end,index);
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        System.out.println("visitMaxs");
        super.visitMaxs(maxStack,maxLocals);
    }

    public static void main(String[] args) throws IOException {
        ClassVisitor cv = new ClassVisitor(ASM5){
            public MethodVisitor visitMethod(int access, String name, String desc,
                                             String signature, String[] exceptions) {
                return new MethodVisitSeq(ASM5);
            }
        };
        ClassReader cr = new ClassReader("com.kaiba.demo.asm.core.method.Bean");
        cr.accept(cv,0);
    }

}
