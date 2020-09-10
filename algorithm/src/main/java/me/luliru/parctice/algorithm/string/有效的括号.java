package me.luliru.parctice.algorithm.string;

import java.util.Stack;

/**
 * 有效的括号

 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。

 注意空字符串可被认为是有效字符串。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-parentheses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/9/2.
 */
public class 有效的括号 {

    public static void main(String[] args) {
        System.out.println(mine_isValid("()"));
    }

    public static boolean mine_isValid(String s) {
        int rp = s.length()-1;
        for(int lp=0;lp<s.length();lp++) {
            char c = s.charAt(lp);
            if(c == ')' || c == '}' || c == ']') {
                return false;
            }
            if(c == '(' || c == '{' || c == '[') {
                while(lp != rp) {
                    char rc = s.charAt(rp);
                    if(rc == '(' || rc == '{' || rc == '[') {
                        return false;
                    }
                    if(rc == ')' || rc == '}' || rc == ']') {
                        if(c == '(' && rc != ')') {
                            return false;
                        }
                        if(c == '{' && rc != '}') {
                            return false;
                        }
                        if(c == '[' && rc != ']') {
                            return false;
                        }
                        return true;
                    }
                    rp--;
                }
                return false;
            }
        }
        return true;
    }

    public static boolean mine_isValid_v2(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if(c == ')' || c == '}' || c == ']') {
                if(stack.empty()) {
                    return false;
                }
                char v = stack.pop();
                if(c == ')' && v != '(') {
                    return false;
                }
                if(c == '}' && v != '{') {
                    return false;
                }
                if(c == ']' && v != '[') {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
