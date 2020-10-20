package me.luliru.parctice.algorithm.linkedlist;

/**
 * 删除链表的倒数第N个节点
 * Created by luliru on 2020/10/18.
 */
public class 删除链表的倒数第N个节点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        int gap = 0;
        while (fast != null) {
            fast = fast.next;
            gap++;
            // 如果快慢指针间距没有达到n，则慢指针不动
            if(gap > n+1) {     // 由于结束条件是fast == null，所以实际间隙应该为n+1
                slow = slow.next;
                gap--;
            }
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


}
