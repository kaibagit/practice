package me.luliru.parctice.algorithm.string;

import java.util.LinkedList;

/**
 * LC224_基本计算器
 * Created by luliru on 2021/3/10.
 */
public class LC224_基本计算器 {

    public static void main(String[] args) {
//        new LC224_基本计算器().calculate("1 + 1");
        System.out.println(new LC224_基本计算器().calculate_210309_V2("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * 括号展开 + 栈
     * @param s
     * @return
     */
    public int calculate_210809_v1(String s) {
        LinkedList<Boolean> plusMinusStack = new LinkedList<>();    // 如果是+，则为true，用于处理括号--得正的情况
        plusMinusStack.push(true);

        int result = 0;
        int typingNum = 0;  // 像 -1 + 2的情况，类似于补0，即0 - 1 + 2，所以该值默认=0
        Character op = '+'; //当前要执行的运算符，+或-
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (c == '+' || c == '-') {
                result = cal(op, result, typingNum);
                //根据栈顶元素决定是否反转运算符
                op = convertOp(plusMinusStack, c);
                // 重置typingNum
                typingNum = 0;
            } else if (c == '(') {
                plusMinusStack.push(op == '+');
            } else if (c == ')') {
                plusMinusStack.pop();
            } else {
                // 输入的是数字
                typingNum = typingNum * 10 + c - '0';
            }
        }
        if (typingNum == 0) {
            return result;
        } else {
            // 整个字符串处理完，但是还有运算没处理，如1 + 1，因为运算只有在碰到下一个+-符才触发
            return cal(op, result, typingNum);
        }
    }

    char convertOp(LinkedList<Boolean> plusMinusStack, char c) {
        if (plusMinusStack.peek()) {
            return c;
        }
        // 前面转负数了
        if (c == '-') {
            return '+';
        } else {
            return '-';
        }
    }

    private int cal(char op, int num_1, int num_2) {
        switch (op) {
            case '+':
                return num_1 + num_2;
            case '-':
                return num_1 - num_2;
            default:
                return 0;
        }
    }


    /**
     * 栈+递归
     * @param s
     * @return
     */
    public int calculate_210309_V2(String s) {
        int[] resultAndNextIndex = recuse(s, 0);
        return resultAndNextIndex[0];
    }

    /**
     * 按照括号递归计算
     * @param s
     * @param begin 开始位置，包含
     * @return  二维数组，arr[0]表示计算的值，arr[1]表示')'下一个位置的索引
     */
    private int[] recuse(String s, int begin) {     // begin包含
        LinkedList<Integer> numStack = new LinkedList<>();
        int typingNum = 0;
        char op = '+';
        int index = begin;
        // 退出条件：index = s.length + 1
        // 一般情况下，这里应该是index < s.length，但是为了让末尾数字入栈，如1 + 2，相当于+1 +2，所以在末尾虚拟一个不存在的字符')'，而')'就代表这本层递归已结束，需要计算并返回
        while (index <= s.length()) {
            char c = ')';       // 末尾标识符
            if (index < s.length()) {
                c = s.charAt(index);
            }
            if (c == ' ') {
                ++index;
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c== '/' || c == ')') {
                if (op == '+') {
                    numStack.push(typingNum);
                } else if(op == '-'){
                    numStack.push(-typingNum);
                } else if (op == '*') {
                    numStack.push(numStack.pop() * typingNum);
                } else if (op == '/') {
                    numStack.push(numStack.pop() / typingNum);
                }
                typingNum = 0;
                op = c;
                ++index;
                // 到达当前递归末尾，需要退出循环并return
                if (c == ')') {
                    break;
                }
            } else if (c == '(') {
                // 开启下一层递归
                int[] resultAndNextIndex = recuse(s, index + 1);
                typingNum = resultAndNextIndex[0];
                index = resultAndNextIndex[1];
            } else {
                // 数字
                typingNum = c - '0' + typingNum * 10;
                ++index;
            }
        }

        int result = 0;
        while (!numStack.isEmpty()) {
            result = result + numStack.pop();
        }
        return new int[]{result, index};
    }







}
