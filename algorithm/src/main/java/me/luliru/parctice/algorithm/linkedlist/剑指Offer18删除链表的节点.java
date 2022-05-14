package me.luliru.parctice.algorithm.linkedlist;

/**
 * 剑指Offer18删除链表的节点
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer18删除链表的节点 {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val == val) {
                pre.next = next;
                break;
            } else {
                pre = curr;
            }

            curr = next;
        }

        return dummy.next;
    }











































    public ListNode deleteNode_old(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(head.val-1);
        dummy.next = head;

        ListNode last = dummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                last.next = curr.next;
                break;
            }
            // 下一轮
            last = curr;
            curr = curr.next;
        }

        return dummy.next;
    }
}
