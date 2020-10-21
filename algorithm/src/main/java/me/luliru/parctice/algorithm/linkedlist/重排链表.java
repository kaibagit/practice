package me.luliru.parctice.algorithm.linkedlist;

/**
 * 重排链表
 * Created by luliru on 2020/10/20.
 */
public class 重排链表 {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 将链表一分为二
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstList = head;
        ListNode secondList = slow.next;
        slow.next = null;

        // 将链表2反正
        ListNode reversedSecondList = reverse(secondList);

        // 合并链表
        ListNode firstListNode = firstList;
        ListNode secondListNode = reversedSecondList;
        while (secondListNode != null) {
            ListNode nextFirstListNode = firstListNode.next;
            ListNode nextSecondListNode = secondListNode.next;
            firstListNode.next = secondListNode;
            secondListNode.next = nextFirstListNode;

            firstListNode = nextFirstListNode;
            secondListNode = nextSecondListNode;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode lastNode = head;
        ListNode node = head.next;
        ListNode nextNode = null;
        while (node != null) {
            nextNode = node.next;
            node.next = lastNode;

            lastNode = node;
            node = nextNode;
        }

        // 处理第一个元素的next
        head.next = null;
        return lastNode;
    }
}
