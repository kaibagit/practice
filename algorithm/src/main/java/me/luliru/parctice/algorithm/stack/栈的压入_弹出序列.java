package me.luliru.parctice.algorithm.stack;

import java.util.Stack;

/**
 * 栈的压入_弹出序列
 * Created by luliru on 2020/10/13.
 */
public class 栈的压入_弹出序列 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pushedIndex = 0;
        for(int i=0;i<popped.length;i++) {
            int val = popped[i];
            // 处理stack中数据
            if(!stack.isEmpty() && stack.peek() == val){
                stack.pop();
                continue;
            }
            //当前循环是否找到相等的值，如果找到了，则处理下一个popped数据，否则直接结束
            boolean currSucc = false;
            // 遍历入栈数据，如果不相等，则依次入栈
            while(pushedIndex< pushed.length) {
                int pushedVal = pushed[pushedIndex];
                pushedIndex++;
                if(pushedVal == val) {
                    currSucc = true;
                    break;
                }else {
                    stack.push(pushedVal);
                }
            }
            if(!currSucc){
                return false;
            }
        }
        return true;
    }
}
