package me.luliru.parctice.algorithm.linkedlist;

/**
 * 两数相加
 * Created by luliru on 2020/10/19.
 */
public class LC2_两数相加 {

    /**
     * 模拟相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_210224(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p1 = l1, p2 = l2, pre = dummy;
        int carry = 0;
        while (p1 != null || p2 != null || carry > 0) {
            int v1 = p1 != null ? p1.val : 0;
            int v2 = p2 != null ? p2.val : 0;
            int val = v1 + v2 + carry;
            if (val > 9) {
                carry = 1;
                val -= 10;
            } else {
                carry = 0;
            }
            ListNode sumNode = new ListNode(val);
            pre.next = sumNode;
            pre = sumNode;
            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }
        return dummy.next;
    }








































    public ListNode addTwoNumbers_old(ListNode l1, ListNode l2) {
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
