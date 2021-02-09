package me.luliru.parctice.algorithm.linkedlist;

/**
 * 两两交换链表中的节点
 * Created by luliru on 2020/10/13.
 */
public class 两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        return recur(head);
    }

    /**
     * 递归
     * @param head
     * @return
     */
    private ListNode recur(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        ListNode nextList = newHead.next;
        newHead.next = head;
        head.next = recur(nextList);
        return newHead;
    }

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode swapPairs_v2(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null && temp.next.next != null) { //后续存在2个node
            ListNode node1 = temp.next;
            ListNode node2 = node1.next;

            // temp -> node1 -> node2 -> others 变更为 temp -> node2 -> node1 -> others
            node1.next = node2.next;
            node2.next = node1;
            temp.next = node2;

            // 前进执行下一轮
            temp = node1;
        }

        return dummy.next;  // 在之前的循环中，dummy.nex已经被改为node2
    }
}
