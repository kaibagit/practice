package me.luliru.parctice.algorithm.linkedlist;

import java.util.LinkedList;

/**
 * LC92_反转链表II
 * Created by luliru on 2021/3/18.
 */
public class LC92_反转链表II {

    /**
     * 栈
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween_210318_v1(ListNode head, int left, int right) {
        // 1、将left到right的节点入栈
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode node = head;
        ListNode begin = dummy;    // 开始反转的前一个node
        ListNode end = null;       // 反转链表的后一个node
        int position = 1;
        LinkedList<ListNode> stack = new LinkedList<>();
        while (node != null) {
            if (position >= left && position <= right) {
                stack.push(node);
            } else if (position < left) {
                begin = node;
            } else if (position > right) {
                end = node;
                break;
            }

            node = node.next;
            ++position;
        }

        // 2、开始反转
        node = begin;
        while (!stack.isEmpty()) {
            begin.next = stack.pop();
            begin = begin.next;
        }
        begin.next = end;   // 处理反转后链表的最后一个节点

        return dummy.next;
    }

    /**
     * 分割链表 + 反转链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween_210318_v3(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = dummy;
        int position = 0;
        while (position < left - 1) {   // 退出条件：position = left - 1，即left前一个元素
            curr = curr.next;
            ++position;
        }

        ListNode begin = curr;  // left的前一个元素
        ListNode reverseHead = begin.next;
        while (position < right) {      // 退出条件：position = right;
            curr = curr.next;
            ++position;
        }
        ListNode reverseTail = curr;
        ListNode end = curr.next;    // right的后一个元素

        // 分割链表
        begin.next = null;
        reverseTail.next = null;

        ListNode reversedList = reverseList(reverseHead);
        begin.next = reversedList;
        reverseHead.next = end;

        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = pre;

            pre = curr;
            curr = nextNode;
        }

        return pre;
    }


    /**
     * 头插法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween_210318_v4(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 1、 找到left的前一个节点
        int position = 0;
        ListNode curr = dummy;
        while (position < left - 1) {   //退出条件： position = left - 1
            curr = curr.next;
            ++position;
        }
        ListNode begin = curr;

        // 2、从left遍历到rihgt，依次将node插入的left前一个节点的后面
        curr = curr.next;   // curr = left节点
        ++position;
        ListNode leftNode = curr;
        while (position <= right) {     // 退出条件：position = right + 1
            ListNode nextNode = curr.next;

            curr.next = begin.next;
            begin.next = curr;

            curr = nextNode;
            ++position;
        }

        // 3、将left节点指向right节点后面
        leftNode.next = curr;

        return dummy.next;
    }
}
