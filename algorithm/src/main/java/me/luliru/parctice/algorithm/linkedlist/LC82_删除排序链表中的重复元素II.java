package me.luliru.parctice.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * LC82_删除排序链表中的重复元素II
 * Created by luliru on 2021/3/25.
 */
public class LC82_删除排序链表中的重复元素II {

    /**
     * 迭代法，模拟栈
     * @param head
     * @return
     */
    public ListNode deleteDuplicates_210325_v1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        // pre：栈顶元素；
        // prePre：栈顶元素的前一个元素；
        // node：当前元素
        ListNode prePre = dummy, pre = head, node = head.next;
        int lastVal = head.val;
        while (node != null) {
            if (node.val == lastVal) {
                pre = prePre;
                pre.next = node.next;

                lastVal = node.val;
                node = node.next;
            } else {
                prePre = pre;
                pre = node;

                lastVal = node.val;
                node = node.next;
            }
        }

        return dummy.next;
    }

    /**
     * 哈希
     * @param head
     * @return
     */
    public ListNode deleteDuplicates_210325_v2(ListNode head) {
        Map<Integer,Integer> countMap = new HashMap<>();
        ListNode node = head;
        while (node != null) {
            countMap.put(node.val, countMap.getOrDefault(node.val, 0) + 1);
            node = node.next;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        node = head;
        while (node != null) {
            if (countMap.get(node.val) > 1) {
                pre.next = node.next;
            } else {
                pre = node;
            }
            node = node.next;
        }

        return dummy.next;
    }
}
