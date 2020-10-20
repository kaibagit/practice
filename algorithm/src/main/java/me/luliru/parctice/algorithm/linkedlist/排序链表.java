package me.luliru.parctice.algorithm.linkedlist;

/**
 * 排序链表
 * Created by luliru on 2020/10/19.
 */
public class 排序链表 {

    public ListNode sortList(ListNode head) {
        return sort(head);
    }

    private ListNode sort(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        // 找到中间节点，快慢指针，慢从1开始，快从2开始
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将链表一分为二并断开
        ListNode firstListNode = node;
        ListNode secondListNode = slow.next;
        slow.next = null;

        // 递归对链表进行排序
        firstListNode = sort(firstListNode);
        secondListNode = sort(secondListNode);

        // 合并链表
        ListNode dummy = new ListNode();
        ListNode lastNode = dummy;
        while (firstListNode != null && secondListNode != null) {
            if (firstListNode.val <= secondListNode.val) {
                lastNode.next = firstListNode;
                firstListNode = firstListNode.next;
            } else {
                lastNode.next = secondListNode;
                secondListNode = secondListNode.next;
            }
            lastNode = lastNode.next;
        }
        lastNode.next = firstListNode == null ? secondListNode : firstListNode;

        return dummy.next;
    }
}
