package me.luliru.parctice.algorithm.linkedlist;

/**
 * 合并两个有序链表
 *
 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。



 示例：

 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/9/5.
 */
public class 合并两个有序链表 {

    public static void main(String[] args) {
        ListNode n1_4 = new ListNode(4,null);
        ListNode n1_2 = new ListNode(2,n1_4);
        ListNode n1_1 = new ListNode(1,n1_2);

        ListNode n2_4 = new ListNode(4,null);
        ListNode n2_3 = new ListNode(3,n2_4);
        ListNode n2_1 = new ListNode(1,n2_3);

        ListNode result = mergeTwoLists(n1_1,n2_1);
        System.out.println(result);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode newHead = null;
        if(l1.val <= l2.val) {
            newHead = new ListNode(l1.val);
            l1 = l1.next;
        }else {
            newHead = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode newNode = newHead;
        while(l1 != null || l2 != null) {
            if(l1 == null) {
                newNode.next = l2;
                l2 = l2.next;
            }else if(l2 == null) {
                newNode.next = l1;
                l1 = l1.next;
            }else if(l1.val < l2.val){
                newNode.next = l1;
                l1 = l1.next;
            }else{
                newNode.next = l2;
                l2 = l2.next;
            }
            newNode = newNode.next;
        }
        return newHead;
    }
}
