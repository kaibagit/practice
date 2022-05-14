package me.luliru.parctice.algorithm.linkedlist;

import me.luliru.parctice.algorithm.PrintTool;

import java.util.LinkedList;

/**
 * 剑指Offer06_从尾到头打印链表
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer06_从尾到头打印链表 {

    public static void main(String[] args) {
        ListNode head = ListHelper.array2List(new int[]{1,3,2});
        int[] ans = new 剑指Offer06_从尾到头打印链表().reversePrint_stack(head);
        PrintTool.printArray(ans);
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next =curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;

            len++;
        }

        int[] ans = new int[len];
        ListNode node = pre;
        int i = 0;
        while (node != null) {
            ans[i] = node.val;
            node = node.next;
            i++;
        }

        return ans;
    }

    /**
     * 使用栈
     * @param head
     * @return
     */
    public int[] reversePrint_stack(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }

        int len = stack.size();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}
