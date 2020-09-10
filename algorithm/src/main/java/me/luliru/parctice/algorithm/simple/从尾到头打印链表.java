package me.luliru.parctice.algorithm.simple;

import java.util.Enumeration;
import java.util.Stack;

/**
 * 从尾到头打印链表
 从尾到头反过来打印出每个结点的值。
 * Created by luliru on 2020/8/8.
 */
public class 从尾到头打印链表 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        printListFromTailToHead(listNode1);
    }

    public static void printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            Integer val = stack.pop();
            System.out.println(val);
        }
    }

    public static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
}
