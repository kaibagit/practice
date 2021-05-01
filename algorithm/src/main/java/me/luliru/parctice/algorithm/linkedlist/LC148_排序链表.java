package me.luliru.parctice.algorithm.linkedlist;

/**
 * 排序链表
 * Created by luliru on 2020/10/19.
 */
public class LC148_排序链表 {

    public static void main(String[] args) {
        ListNode head = ListHelper.array2List(new int[]{4,2,1,3});
        new LC148_排序链表().sortList(head);
    }


    /**
     * 自顶向下归并排序
     * @param head
     * @return
     */
    public ListNode sortList_old(ListNode head) {
        return sort(head);
    }

    private ListNode sort(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        // 找到中间节点，快慢指针，慢从1开始，快从2开始
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将链表一分为二并断开
        ListNode firstListNode = node;
        ListNode secondListNode = slow.next;
        slow.next = null;

        // 递归对链表进行排序
        firstListNode = sort(firstListNode);
        secondListNode = sort(secondListNode);

        // 合并链表
        ListNode dummy = new ListNode();
        ListNode lastNode = dummy;
        while (firstListNode != null && secondListNode != null) {
            if (firstListNode.val <= secondListNode.val) {
                lastNode.next = firstListNode;
                firstListNode = firstListNode.next;
            } else {
                lastNode.next = secondListNode;
                secondListNode = secondListNode.next;
            }
            lastNode = lastNode.next;
        }
        lastNode.next = firstListNode == null ? secondListNode : firstListNode;

        return dummy.next;
    }


    /**
     * 自顶向下排序
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1、计算整个链表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            ++length;
            node = node.next;
        }

        // 2、从长度为1、2、4一直往上两两合并链表，合并过程中进行排序
        ListNode dummy = new ListNode();
        dummy.next = head;
        for (int subLength = 1; subLength < length; subLength <<= 1) {  // 每次subLength * 2，链表都要从头遍历一遍
            ListNode preTail = dummy;
            ListNode curr = dummy.next;
            while (curr != null) {
                ListNode firstList = curr;
                // 2.1.1、找到第一个子链表
                for (int i = 1; i < subLength && curr != null; ++i) {      // 退出条件：链表长度已达到subLenght-1 || 原链表长度不够用，此时curr为第一个链表尾部
                    curr = curr.next;
                }

                // 2.1.2、将第一个子链表与后续链表断开
                if (curr != null) {
                    ListNode tail = curr;
                    curr = curr.next;
                    tail.next = null;
                }

                // 2.2.1、找到第二个子链表
                ListNode secondList = curr;
                for (int i = 1; i < subLength && curr != null; ++i) {
                    curr = curr.next;
                }

                // 2.2.2、将第二个子链表与后续链表断开
                if (curr != null) {
                    ListNode tail = curr;
                    curr = curr.next;
                    tail.next = null;
                }

                ListNode next = curr;   // 下一组的头节点

                // 2.3.1、将两个链表合并
                ListNode sortedList = merge(firstList, secondList);
                // 2.3.2、之前链表指向新链表头节点
                preTail.next = sortedList;
                // 2.3.3、排序后链表指向后续节点，这里直接借用preTail变量
                while (preTail.next != null) {
                    preTail = preTail.next;
                }
                preTail.next = next;
            }
        }


        return dummy.next;
    }

    private ListNode merge (ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
