package me.luliru.parctice.algorithm.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 最小栈
 * Created by luliru on 2020/11/2.
 */
public class LC155_最小栈 {

    public static void main(String[] args) {
        MinStack_210305_v2 minStack = new MinStack_210305_v2();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.getMin();
        minStack.push(-2147483648);
        minStack.top();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }


    /**
     * 辅助栈
     */
    class MinStack_210305_v1 {

        private LinkedList<Integer> stack = new LinkedList<>();
        private LinkedList<Integer> mins = new LinkedList<>();

        /** initialize your data structure here. */
        public MinStack_210305_v1() {

        }

        public void push(int x) {
            stack.push(x);;
            if (mins.isEmpty() || mins.peek() >= x) {
                mins.push(x);
            }
        }

        public void pop() {
            int val = stack.pop();
            if (val == mins.peek()) {
                mins.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }


    /**
     * 存差值：
     * stack用来存储和min的差值，min存储最小值，每次出栈的时候通过差值和当前min计算要出栈的值和之前的min
     * 如果差值diff大于等于0，说明要出栈的值大于等于当前min，那么要出栈的值在入栈的时候没有更新min，返回min+diff；
     * 如果插值diff小于0，说明当前要出栈的值就是min(因为入栈的时候我们选择的就是min和入栈元素的最小值)，同时，通过min-diff计算出之前min
     * 要注意的是diff可能会超出int范围，例如当前min_value = 2147483647，push -2147483648，那么diff = -2147483648 -2147483647，如果diff为int类型显然是越界了
     */
    static class MinStack_210305_v2 {

        private Long min;
        // 当diff > 0 时，代表当前最小值为min，当前值为min + diff
        // 当diff = 0 时，代表当前最小值为min，当前值为min
        // 当diff < 0 时，代表当前最小值为min，前一个最小值为min - diff
        private LinkedList<Long> diffs = new LinkedList<>();

        /** initialize your data structure here. */
        public MinStack_210305_v2() {

        }

        public void push(int x) {
            if (min == null) {
                min = (long)x;
                diffs.push(0L);
            } else if (x > min) {
                diffs.push((long) x - min);
            } else if (x == min) {
                diffs.push(0L);
            } else if (x < min) {
                diffs.push((long)x - min);
                min = (long)x;
            }
        }

        public void pop() {
            long diff = diffs.pop();
            if (diff < 0) {
                min = min - diff;
            }
            if (diffs.isEmpty()) {
                min = null;
            }
        }

        public int top() {
            long diff = diffs.peek();
            if (diff >= 0) {
                return (int)(min + diff);
            } else {
                return min.intValue();
            }
        }

        public int getMin() {
            return min.intValue();
        }
    }




































    static class MinStack_old {

        private Stack<Integer> dataStack;

        private Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack_old() {
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
            if (minStack.peek() == val) {
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
