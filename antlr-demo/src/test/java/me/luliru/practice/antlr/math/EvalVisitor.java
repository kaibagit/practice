package me.luliru.practice.antlr.math;

import java.util.HashMap;

/**
 * EvalVisitor
 * Created by luliru on 2021/9/12.
 */
public class EvalVisitor extends MathBaseVisitor<Integer> {

    // 模拟计算器的内存，存放"变量名->值"的映射，即在赋值时候往这里写
    HashMap<String, Integer> memory = new HashMap<>();

    // 访问赋值语句：ID '=' expr NEWLINE
    @Override
    public Integer visitAssign(MathParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // 获取左值标识符
        int value = visit(ctx.expr());   // 对右值表达式访问求值
        memory.put(id, value);           // 存储赋值
        return value;
    }

    // 访问表达式语句：expr NEWLINE
    @Override
    public Integer visitPrintExpr(MathParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr()); // 对表达式访问求值
        System.out.println(value);         // 把值打印出来
        return 0;                          // 反正用不到这个返回值，这里返回假值
    }

    // 访问单个整数构成的表达式：INT
    @Override
    public Integer visitInt(MathParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText()); // 把这个数返回
    }

    // 访问单个标识符构成的表达式：ID
    @Override
    public Integer visitId(MathParser.IdContext ctx) {
        String id = ctx.ID().getText(); // 获取标识符名字
        if (memory.containsKey(id)) // 查表，找到就返回
            return memory.get(id);
        return 0; // 找不到返回0
    }

    // 访问乘除法表达式：expr op=('*'|'/') expr
    @Override
    public Integer visitMulDiv(MathParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));  // 被除数，或乘法因子1
        int right = visit(ctx.expr(1)); // 除数，或乘法因子2
        if (ctx.op.getType() == MathParser.MUL) // 检查操作符
            return left * right; // 乘法
        return left / right; // 除法
    }

    // 访问加减法表达式：expr op=('+'|'-') expr
    @Override
    public Integer visitAddSub(MathParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));  // 项1
        int right = visit(ctx.expr(1)); // 项2
        if (ctx.op.getType() == MathParser.ADD) // 检查操作符
            return left + right; // 加法
        return left - right; // 减法
    }

    // 访问表达式加括号：'(' expr ')'
    @Override
    public Integer visitParens(MathParser.ParensContext ctx) {
        return visit(ctx.expr()); // 其实就是把括号里表达式的值算出来返回
    }
}
