package me.luliru.parctice.algorithm.linkedlist;

/**
 * LC61_旋转链表
 * Created by luliru on 2021/3/27.
 */
public class LC61_旋转链表 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 由于k可能比较大，所以先根据链表长度求余
        int len = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            ++len;
        }
        int move = k % len;
        if (move == 0) {
            return head;
        }

        ListNode slow = head, fast = head;
        int distance = 0;
        // 一直往前移动，直到fast指向链表最后一个元素
        // slow与fast保持move的距离
        while (fast.next != null) {
            if (distance == move) {
                slow = slow.next;
            } else {
                ++distance;
            }
            fast = fast.next;
        }
        // 将slow与之后元素断开拦截，fast指向head
        ListNode newHead = slow.next;
        fast.next = head;
        slow.next = null;

        return newHead;
    }
}
