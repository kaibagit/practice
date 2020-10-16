package me.luliru.parctice.algorithm.linkedlist;

/**
 * 两两交换链表中的节点
 * Created by luliru on 2020/10/13.
 */
public class 两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        return recursion(head);
    }

    private ListNode recursion(ListNode node) {
        if(node == null) {
            return null;
        }
        if(node.next == null) {
            return node;
        }

        ListNode secondNode = node.next;
        ListNode nextLink = secondNode.next;
        secondNode.next = node;
        node.next = recursion(nextLink);
        return secondNode;
    }
}
