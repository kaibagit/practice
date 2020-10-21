package me.luliru.parctice.algorithm.linkedlist;

/**
 * 剑指Offer22链表中倒数第k个节点
 * Created by luliru on 2020/10/20.
 */
public class 剑指Offer22链表中倒数第k个节点 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        // 特殊列表处理
        if(head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        int distance = 1;
        while (fast != null) {
            fast = fast.next;
            if(distance == k+1) {
                slow = slow.next;
            } else {
                distance++;
            }
        }

        return slow;
    }
}
