package me.luliru.parctice.algorithm.stack;

import java.util.Stack;

/**
 * 最小栈
 * Created by luliru on 2020/11/2.
 */
public class 最小栈 {

    static class MinStack {

        private Stack<Integer> dataStack;

        private Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            int val = dataStack.pop();
            if (!minStack.isEmpty() && minStack.peek() == val) {
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                return -1;
            }
            return minStack.peek();
        }
    }
}
