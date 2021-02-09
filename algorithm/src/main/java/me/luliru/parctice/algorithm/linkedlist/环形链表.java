package me.luliru.parctice.algorithm.linkedlist;

/**
 * 环形链表
 * Created by luliru on 2020/10/18.
 */
public class 环形链表 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = null;
            }
        }
        return false;
    }
}
