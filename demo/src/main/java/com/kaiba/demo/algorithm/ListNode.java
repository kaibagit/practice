package com.kaiba.demo.algorithm;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int x,ListNode next) {
        this.val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next.val +
                '}';
    }
}
