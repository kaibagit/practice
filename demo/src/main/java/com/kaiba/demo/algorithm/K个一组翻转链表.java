package com.kaiba.demo.algorithm;

import java.util.Stack;

public class K个一组翻转链表 {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5,null);
        ListNode n4 = new ListNode(4,n5);
        ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);
        ListNode result = reverseKGroup(n1,3);
        System.out.println(result);
    }

    /**
     * 官方答案
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0,head);
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] headAndTail = myReverse(head, tail);
            head = headAndTail[0];
            tail = headAndTail[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;  // 上一个节点
        ListNode p = head;  // 当前节点
        while (prev != tail) {
            ListNode nex = p.next;  //下一个节点
            p.next = prev;  // 当前节点指向上一个节点
            prev = p;   // prev向后移动
            p = nex;    // p向后移动
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 递归
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup_v2(ListNode head, int k) {
        ListNode point = head;
        Stack<ListNode> stack = new Stack<>();
        while (stack.size() < k) {
            if(point == null) {
                return head;
            }
            stack.push(point);
            point = point.next;
        }
        ListNode first = stack.pop();
        ListNode pre = first;
        while (!stack.empty()) {
            ListNode current = stack.pop();
            pre.next = current;
            pre = current;
        }
        pre.next = reverseKGroup_v2(point,k);
        return first;
    }

    /**
     * 错误
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup_1(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode point = head;
        ListNode nextHead = null;
        ListNode result = null;
        int count = 0;
        while(point != null) {
            if(count < k-1) {
                stack.push(point);
                count ++;
            } else {
                if(result == null) {
                    result = point;
                }
                nextHead = point.next;
                while(count != 0) {
                    ListNode val = stack.pop();
                    point.next = val;
                    point = val;
                    count--;
                }
                point.next = nextHead;
            }
            point = point.next;
        }
        return result;
    }
}
