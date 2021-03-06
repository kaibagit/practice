package me.luliru.parctice.algorithm.stack;

import java.util.Stack;

/**
 * 剑指Offer09用两个栈实现队列
 * Created by luliru on 2020/11/2.
 */
public class 剑指Offer09用两个栈实现队列 {

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    static class CQueue {

        private Stack<Integer> inStack;

        private Stack<Integer> outStack;

        public CQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            if (outStack.isEmpty()) {
                return -1;
            }
            return outStack.pop();
        }
    }
}
