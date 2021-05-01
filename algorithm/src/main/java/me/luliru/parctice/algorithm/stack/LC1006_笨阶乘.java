package me.luliru.parctice.algorithm.stack;

import java.util.LinkedList;

/**
 * LC1006_笨阶乘
 * Created by luliru on 2021/4/1.
 */
public class LC1006_笨阶乘 {

    /**
     * 栈
     * @param N
     * @return
     */
    public int clumsy_210401(int N) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = N, op = 0; i > 0; i--, op++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (op % 4 == 1) {      // 乘
                int pre = stack.pop();
                stack.push(pre * i);
            } else if (op % 4 == 2) {   // 除
                int pre = stack.pop();
                stack.push(pre / i);
            } else if (op % 4 == 3) {   // 加
                stack.push(i);
            } else if (op % 4 == 0) {   // 减
                stack.push(-i);
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
