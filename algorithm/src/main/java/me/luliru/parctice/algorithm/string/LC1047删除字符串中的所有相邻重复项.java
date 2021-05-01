package me.luliru.parctice.algorithm.string;

import java.util.LinkedList;

/**
 * LC1047删除字符串中的所有相邻重复项
 * Created by luliru on 2021/3/9.
 */
public class LC1047删除字符串中的所有相邻重复项 {

    /**
     * 栈
     * @param S
     * @return
     */
    public String removeDuplicates_210308_V1(String S) {
        StringBuilder builder = new StringBuilder();
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            if (stack.isEmpty() || !stack.peek().equals(S.charAt(i))) {
                stack.push(S.charAt(i));
            } else {
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    /**
     * 栈，V1优化版本
     * @param S
     * @return
     */
    public String removeDuplicates_210308_V2(String S) {
        StringBuilder stack = new StringBuilder();
        int len = 0;
        for (int i = 0; i < S.length(); i++) {
            if (len == 0 || stack.charAt(len - 1) != S.charAt(i)) {
                stack.append(S.charAt(i));
                ++len;
            } else {
                stack.deleteCharAt(len - 1);
                --len;
            }
        }
        return stack.toString();
    }
}
