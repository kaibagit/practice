package me.luliru.parctice.algorithm.linkedlist;

/**
 * 两数相加
 * Created by luliru on 2020/10/19.
 */
public class 两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        ListNode sumDummy = new ListNode();
        ListNode lastNewNode = sumDummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1){
            int v1 = l1Node == null ? 0 : l1Node.val;
            int v2 = l2Node == null ? 0 : l2Node.val;
            int newVal = v1+v2+carry;
            if(newVal >= 10) {
                carry = 1;
                newVal = newVal-10;
            }else{
                carry = 0;
            }

            ListNode newNode = new ListNode(newVal);
            lastNewNode.next = newNode;
            lastNewNode = newNode;

            l1Node = l1Node == null ? null : l1Node.next;
            l2Node = l2Node == null ? null : l2Node.next;
        }
        return sumDummy.next;
    }
}
