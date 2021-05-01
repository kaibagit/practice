package me.luliru.parctice.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * LC142_环形链表II
 * Created by luliru on 2021/2/28.
 */
public class LC142_环形链表II {

    /**
     * 哈希
     * @param head
     * @return
     */
    public ListNode detectCycle_v1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Set<ListNode> existedNodes = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (existedNodes.contains(node)) {
                return node;
            }
            existedNodes.add(node);
            node = node.next;
        }
        return null;
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            // 1、找到相遇点
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;

            // 2、两个指针分别从head、相遇点出发，当它们相遇时，就是环的入口
            if (fast == slow) {
                ListNode meeting = slow;
                ListNode node_1 = head, node_2 = meeting;
                while (node_1 != node_2) {
                    node_1 = node_1.next;
                    node_2 = node_2.next;
                }
                return node_2;
            }
        }
        return null;
    }
}
