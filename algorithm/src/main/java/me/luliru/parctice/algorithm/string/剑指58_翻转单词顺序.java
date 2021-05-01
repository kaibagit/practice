package me.luliru.parctice.algorithm.string;

import java.util.LinkedList;

/**
 * 剑指58翻转单词顺序
 * Created by luliru on 2021/2/19.
 */
public class 剑指58_翻转单词顺序 {

    public static void main(String[] args) {
        new 剑指58_翻转单词顺序().reverseWords("the sky is blue");
    }

    /**
     * 正序+双指针+栈
     * @param s
     * @return
     */
    public String reverseWords_v1(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int left = -1;  // 有效字符串范围：(left,right]
        for (int right = 0; right < s.length(); right++) {      // 临界条件：right = s.length
            if (s.charAt(right) != ' ') {
                continue;
            }
            if (left < right - 1) {     // 左右指针之间存在有效字符
                stack.push(s.substring(left + 1,right));    // 目标是(left,right - 1],substring是[left，right)，所以需要转换
            }
            left = right;
        }
        // 如果最后还存在有效字符
        if (left < s.length() - 1) {
            stack.push(s.substring(left + 1,s.length()));
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            if (ans.length() != 0) {
                ans.append(' ');
            }
            ans.append(stack.pop());
        }

        return ans.toString();
    }

    /**
     * 逆序+双指针
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int left = s.length() - 1, right = s.length();  // 字符串范围为[left,right)
        for (; left >= 0; left--) {
            if (s.charAt(left) != ' ') {
                continue;
            }
            if (left + 1 < right) {
                ans.append(s.substring(left + 1, right)).append(' ');
            }
            right = left;
        }

        // 处理首位单词
        if (right > 0) {    // 这里隐含了s[0] != ' '
            ans.append(s.substring(0, right)).append(' ');
        }

        // 移除多加的一个' '
        if (ans.length() > 0) {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
