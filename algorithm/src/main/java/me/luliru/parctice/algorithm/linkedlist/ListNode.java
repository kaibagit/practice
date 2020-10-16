package me.luliru.parctice.algorithm.linkedlist;

/**
 * ListNode
 * Created by luliru on 2020/9/5.
 */
public class ListNode {
    public int val;
    public ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
