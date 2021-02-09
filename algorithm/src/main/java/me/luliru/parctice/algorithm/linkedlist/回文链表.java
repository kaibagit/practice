package me.luliru.parctice.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * Created by luliru on 2020/10/18.
 */
public class 回文链表 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,4,3,2,1};

        new 回文链表().isPalindrome(ListHelper.array2List(arr));
    }

    /**
     * 链表一分为二，反转链表二，双指针比对
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return false;
        }

        // 快慢指针将链表一分为二
        ListNode slow = head;       // 奇数个时，左边会多一个节点
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode firstList = head;
        ListNode secondList = slow.next;
        slow.next = null;

        // 反转链表二
        secondList = reverseNode(secondList);

        // 依次比对链表一和链表二
        ListNode firstNode = firstList;
        ListNode secondNode = secondList;
        while (secondNode != null) {
            if(firstNode.val != secondNode.val) {
                return false;
            }
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }

        // 链表一可能会多一个节点
//        if(firstNode != null) {
//            return false;
//        }

        return true;
    }

    private ListNode reverseNode(ListNode node) {
        if(node == null || node.next == null) {
            return node;
        }

        ListNode next = node.next;
        ListNode newHead = reverseNode(next);
        next.next = node;
        node.next = null;

        return newHead;
    }

    public boolean isPalindrome_v1(ListNode head) {
        List<Integer> datas = new ArrayList<>();
        // 遍历链表形成数组
        ListNode node = head;
        while (node != null) {
            datas.add(node.val);
            node = node.next;
        }
        // 双指针从头和尾分别开始遍历
        for(int i=0,j=datas.size()-1;i<=j;i++,j--){
            if(!datas.get(i).equals(datas.get(j))) {
                return false;
            }
        }
        return true;
    }
}
