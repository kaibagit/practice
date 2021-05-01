package me.luliru.parctice.algorithm.linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 反转链表

 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL

 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/9/5.
 */
public class 剑指24_反转链表 {


    /** 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList_210318_loop(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {      // 退出条件：curr = null，此时，pre为原先最后一个节点
            ListNode next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }
        return pre;
    }


    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList_210318_recurse(ListNode head) {
        if (head == null) {
            return null;
        }
        return recurse(head);
    }

    private ListNode recurse(ListNode head) {
        ListNode oriNext = head.next;
        if (oriNext == null) {
            return head;
        }

        ListNode newHead = recurse(head.next);
        oriNext.next = head;
        head.next = null;

        return newHead;
    }















    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList_210224_recur(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = recurse_210224(head);
        head.next = null;
        return newHead;
    }

    private ListNode recurse_210224(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode head = recurse_210224(node.next);
        node.next.next = node;
        return head;    // 这里node.next还是保持原值
    }


    /**
     * 栈
     * @param head
     * @return
     */
    public ListNode reverseList_210224_stack(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        ListNode newHead = stack.pop();
        ListNode pre = newHead;
        while (!stack.isEmpty()) {
            ListNode curr = stack.pop();
            pre.next = curr;
            pre = curr;
        }
        pre.next = null;    // 最后一个节点指向null，即原先的head节点
        return newHead;
    }









































    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList_old(ListNode head) {
        ListNode pre = null;
        ListNode curr = head.next;
        while (curr != null) {  //当curr=null退出循环，前一个节点就是新的head
            ListNode next = curr.next;
            curr.next = pre;

            // 下一轮
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 栈
     * @param head
     * @return
     */
    public ListNode reverseList_stack_old(ListNode head) {
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while(head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = stack.pop();
        ListNode lastNode = newHead;
        while (!stack.empty()){
            ListNode next = stack.pop();
            lastNode.next = next;
            lastNode = next;
        }
        lastNode.next = null;
        return newHead;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList_old_V2(ListNode head) {
        return revert(head);
    }

    public ListNode revert(ListNode node) {
        if(node == null) {
            return null;
        }
        if(node.next == null) {
            return node;
        }
        ListNode oriNext = node.next;
        ListNode newHead = revert(oriNext);
        oriNext.next = node;
        node.next = null;
        return newHead;
    }
}
