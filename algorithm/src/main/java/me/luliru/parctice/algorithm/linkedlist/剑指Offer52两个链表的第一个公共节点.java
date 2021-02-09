package me.luliru.parctice.algorithm.linkedlist;

/**
 * 剑指Offer52两个链表的第一个公共节点
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer52两个链表的第一个公共节点 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        ListNode pointB = headB;

        while (pointA != pointB) {
            pointA = pointA != null ? pointA.next : headB;
            pointB = pointB != null ? pointB.next : headA;
        }

        return pointA;
    }

    public ListNode getIntersectionNode_error(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        ListNode pointB = headB;

        boolean crossList = false;


        if (pointA == pointB) {
            return pointA;
        }
        if (pointA.next != null) {
            pointA = pointA.next;
        } else {
            if (crossList) {
                return null;
            }
            pointA = headB;
            crossList = true;
        }

        if (pointB.next != null) {
            pointB = pointB.next;
        } else {
            pointB = headA;
        }

        return null;
    }
}
