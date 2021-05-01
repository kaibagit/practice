package me.luliru.parctice.algorithm.linkedlist;

/**
 * LC83_删除排序链表中的重复元素
 * Created by luliru on 2021/3/26.
 */
public class LC83_删除排序链表中的重复元素 {

    public ListNode deleteDuplicates_210326(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode curr = head, pre = dummy;
        while (curr != null) {
            if (curr.val == pre.val) {
                pre.next = curr.next;
                curr = curr.next;
            } else {
                pre = curr;
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}
