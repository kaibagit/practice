package me.luliru.parctice.algorithm.linkedlist;

/**
 * 环形链表
 * Created by luliru on 2020/10/18.
 */
public class 环形链表 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        while(fast != null) {
            fast = fast.next;
            count++;
            if(fast == slow) {
                return true;
            }
            if((count & 1) == 0){
                slow = slow.next;
            }
        }
        return false;
    }
}
