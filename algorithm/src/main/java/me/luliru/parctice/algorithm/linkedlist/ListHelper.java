package me.luliru.parctice.algorithm.linkedlist;

/**
 * Helpers
 * Created by luliru on 2020/10/25.
 */
public class ListHelper {

    public static ListNode array2List(int[] arr) {
        ListNode lastNode = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            ListNode node = new ListNode(val);
            node.next = lastNode;
            lastNode = node;
        }
        return lastNode;
    }
}
