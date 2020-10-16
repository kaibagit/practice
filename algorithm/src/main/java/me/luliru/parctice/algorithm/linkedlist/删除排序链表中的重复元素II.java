package me.luliru.parctice.algorithm.linkedlist;

/**
 * 删除排序链表中的重复元素II
 * Created by luliru on 2020/9/25.
 */
public class 删除排序链表中的重复元素II {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5,null);
        ListNode n4_2 = new ListNode(4,n5);
        ListNode n4_1 = new ListNode(4,n4_2);
        ListNode n3_2 = new ListNode(3,n4_1);
        ListNode n3_1 = new ListNode(3,n3_2);
        ListNode n2 = new ListNode(2,n3_1);
        ListNode n1 = new ListNode(1,n2);

        删除排序链表中的重复元素II question = new 删除排序链表中的重复元素II();
        ListNode result = question.deleteDuplicates(n1);
        System.out.println(result);

    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        // 新链表状态
        ListNode blankNode = new ListNode(head.val -1);
        ListNode lastNodeOfNewList = blankNode;

        // 前一个节点状态
        ListNode lastNode = blankNode;
        int base = blankNode.val;
        boolean single = false;

        // 当前节点状态
        ListNode node = head;
        while(node != null) {
            if(base != node.val) {
                if(single) {
                    lastNodeOfNewList.next = lastNode;
                    lastNodeOfNewList = lastNode;
                }
                single = true;
                base = node.val;
            }else {
                single = false;
            }
            lastNode = node;
            node = node.next;
        }
        // 处理最后一个节点
        if(single){     //最后节点没有重复
            lastNodeOfNewList.next = lastNode;
        }else {     //最后节点有出现重复
            lastNodeOfNewList.next = null;
        }
        return blankNode.next;
    }
}
