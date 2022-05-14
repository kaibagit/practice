package me.luliru.parctice.algorithm.stack;

import java.util.LinkedList;
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

        private LinkedList<Integer> tailStack;
        private LinkedList<Integer> headStack;


        public CQueue() {
            tailStack = new LinkedList<>();
            headStack = new LinkedList<>();
        }

        public void appendTail(int value) {
            // 尾部只管push
            tailStack.push(value);
        }

        public int deleteHead() {
            // 如果头部为空，则需要从tailStack迁移数据
            if (headStack.isEmpty()) {
                while (!tailStack.isEmpty()) {
                    headStack.push(tailStack.pop());
                }
            }

            if (headStack.isEmpty()) {
                return -1;
            }

            return headStack.pop();
        }
    }


//    static class CQueue {
//
//        private LinkedList<Integer> tailStack;
//        private LinkedList<Integer> headStack;
//
//
//        public CQueue() {
//            tailStack = new LinkedList<>();
//            headStack = new LinkedList<>();
//        }
//
//        public void appendTail(int value) {
//            while (!outStack.isEmpty()) {
//                inStack.push(outStack.pop());
//            }
//            inStack.push(value);
//        }
//
//        public int deleteHead() {
//            while (!inStack.isEmpty()) {
//                outStack.push(inStack.pop());
//            }
//            if (outStack.isEmpty()) {
//                return -1;
//            }
//            return outStack.pop();
//        }
//    }

















//    static class CQueue {
//
//        private Stack<Integer> inStack;
//
//        private Stack<Integer> outStack;
//
//        public CQueue() {
//            inStack = new Stack<>();
//            outStack = new Stack<>();
//        }
//
//        public void appendTail(int value) {
//            inStack.push(value);
//        }
//
//        public int deleteHead() {
//            if (outStack.isEmpty()) {
//                while (!inStack.isEmpty()) {
//                    outStack.push(inStack.pop());
//                }
//            }
//            if (outStack.isEmpty()) {
//                return -1;
//            }
//            return outStack.pop();
//        }
//    }
}
