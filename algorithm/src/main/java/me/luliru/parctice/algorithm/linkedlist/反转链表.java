package me.luliru.parctice.algorithm.linkedlist;

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
public class 反转链表 {

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
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
    public ListNode reverseList_V2(ListNode head) {
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

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
