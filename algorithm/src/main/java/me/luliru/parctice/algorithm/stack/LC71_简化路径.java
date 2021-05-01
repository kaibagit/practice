package me.luliru.parctice.algorithm.stack;

import java.util.LinkedList;

/**
 * LC71简化路径
 * Created by luliru on 2021/2/19.
 */
public class LC71_简化路径 {

    public static void main(String[] args) {
        new LC71_简化路径().simplifyPath("/home//foo/");
        new LC71_简化路径().simplifyPath("/home/");
        new LC71_简化路径().simplifyPath("/.");
        new LC71_简化路径().simplifyPath("/../");
    }

    /**
     * 栈，自己实现的，无法处理"/..."
     * @param path
     * @return
     */
    public String simplifyPath_error(String path) {
        StringBuilder deque = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            // 处理 '//'
            if (c == '/' & deque.length() > 0 && deque.charAt(deque.length() - 1) == '/') {
                continue;
            }
            if (c == '.') {
                if (i < path.length() - 1 && path.charAt(i + 1) == '.') {    // 处理 '/..'
                    deque.deleteCharAt(deque.length() - 1);    // 移除'/'
                    while (deque.length() > 0 && deque.charAt(deque.length() - 1) != '/') {
                        deque.deleteCharAt(deque.length() - 1);    // 依次移除父路径字符
                    }
                    if (deque.length() > 0) {   // 考虑"/../"的情况
                        deque.deleteCharAt(deque.length() - 1);    // 移除父路径的'/'
                    }
                    i++;    // 跳过后一个'.'
                } else {        // 处理 '/.'
                    deque.deleteCharAt(deque.length() - 1);    // 移除'/'
                }
                continue;
            }
            deque.append(c);
        }
        // 处理最后一个'/'
        if (deque.length() > 0 && deque.charAt(deque.length() - 1) == '/') {
            deque.deleteCharAt(deque.length() - 1);
        }

        // 如果没有路径，则默认"/"
        if (deque.length() == 0) {
            deque.append("/");
        }

        return deque.toString();
    }

    /**
     * deque
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        LinkedList<String> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i ++) {
            String str = arr[i];
            if (str.length() == 0) {
                continue;
            }
            if (str.equals(".")) {
                continue;
            }
            if (str.equals("..")) {
                if(deque.size() > 0){   // 注意"/../"的场景
                    deque.pollLast();
                }
                continue;
            }
            deque.addLast(str);
        }

        StringBuilder ans = new StringBuilder();
        while (!deque.isEmpty()) {
            ans.append("/");
            ans.append(deque.pollFirst());
        }

        if (ans.length() == 0) {
            ans.append("/");
        }

        return ans.toString();
    }
}
