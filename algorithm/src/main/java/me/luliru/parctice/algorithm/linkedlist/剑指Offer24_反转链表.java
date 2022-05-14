package me.luliru.parctice.algorithm.linkedlist;

/**
 * 剑指Offer24_反转链表
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer24_反转链表 {

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        return reverse(head);
    }

    /**
     * 反转，返回新的head
     * @param node
     * @return
     */
    private ListNode reverse(ListNode node) {
        // 如果该节点已经是Null，没有反转的必要
        if(node == null) {
            return null;
        }
        // 如果下一个是null，即已经是最后一个节点了，也没有必要反转
        if(node.next == null) {
            return node;
        }

        ListNode oriNext = node.next;
        // 相当于从尾部开始往头反转
        ListNode newHead = reverse(oriNext);
        oriNext.next = node;
        node.next = null;   // 保证原先的head节点，指向Null
        return newHead;
    }

    /**
     * 迭代，前后指针
     * @param head
     * @return
     */
    public ListNode reverseList_loop(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }

        return pre;
    }
}
