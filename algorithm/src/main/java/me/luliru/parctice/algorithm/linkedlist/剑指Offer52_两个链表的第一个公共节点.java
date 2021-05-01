package me.luliru.parctice.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指Offer52两个链表的第一个公共节点
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer52_两个链表的第一个公共节点 {

    /**
     * 哈希
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_210301_v1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> aNodeSet = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            aNodeSet.add(node);
            node = node.next;
        }

        node = headB;
        while (node != null) {
            if (aNodeSet.contains(node)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * 双指针
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != null || nodeB != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }
            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
        }

        return null;
    }








































    public ListNode getIntersectionNode_old(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        ListNode pointB = headB;

        while (pointA != pointB) {
            pointA = pointA != null ? pointA.next : headB;
            pointB = pointB != null ? pointB.next : headA;
        }

        return pointA;
    }

    public ListNode getIntersectionNode_old_error(ListNode headA, ListNode headB) {
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
