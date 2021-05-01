package me.luliru.parctice.algorithm.linkedlist;

/**
 * 两两交换链表中的节点
 * Created by luliru on 2020/10/13.
 */
public class LC24_两两交换链表中的节点 {


    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode swapPairs_210318_v1(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            ListNode nextPair = node.next.next;

            pre.next = next;
            next.next = node;
            node.next = nextPair;

            pre = node;
            node = nextPair;
        }

        return dummy.next;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode swapPairs_210318_v2(ListNode head) {
        return recurse(head);
    }

    private ListNode recurse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode nextNode = node.next;
        ListNode nextPairNode = nextNode.next;
        nextNode.next = node;
        node.next = recurse(nextPairNode);

        return nextNode;
    }






















    public ListNode swapPairs(ListNode head) {
        return recur_old(head);
    }

    /**
     * 递归
     * @param head
     * @return
     */
    private ListNode recur_old(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        ListNode nextList = newHead.next;
        newHead.next = head;
        head.next = recur_old(nextList);
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
