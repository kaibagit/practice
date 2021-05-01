package me.luliru.parctice.algorithm.linkedlist;

import java.util.LinkedList;

/**
 * LC25_K个一组翻转链表
 * Created by luliru on 2021/3/18.
 */
public class LC25_K个一组翻转链表 {

    public static void main(String[] args) {
        ListNode head = ListHelper.array2List(new int[]{1,2,3,4,5});
        new LC25_K个一组翻转链表().reverseKGroup(head, 2);
    }

    /**
     * 分割链表 + 反转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode preGroup = dummy;
        ListNode node = preGroup;
        while (node != null) {
            int count = 0;
            for (int i = 1; i <= k; i++) {   // 退出时，node指向第k个节点
                node = node.next;
                if (node == null) {
                    break;
                }
                ++count;
            }
            if (count == k) {
                ListNode nextGroup = node.next;
                // 将需要反转的链表摘除
                ListNode unReverseHead = preGroup.next;
                ListNode unReversetTail = node;
                preGroup.next = null;
                unReversetTail.next = null;

                // 将链表反转
                ListNode reversed = reverseList(unReverseHead);

                // 将链表放回
                preGroup.next = reversed;
                unReverseHead.next = nextGroup;

                // 继续执行下一轮
                preGroup = unReverseHead;
                node = preGroup;
            } else {
                // 剩余链表长度不足K个，不作处理
            }
        }

        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;

            pre = node;
            node = next;
        }
        return pre;
    }
}
