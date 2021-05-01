package me.luliru.parctice.algorithm.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC23_合并K个升序链表
 * Created by luliru on 2021/3/1.
 */
public class LC23_合并K个升序链表 {

    /**
     * 逐一两两合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists_210301_v1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode merged = lists[0];
        for (int i = 1; i < lists.length; i++) {
            merged = merge(merged, lists[i]);
        }

        return merged;
    }

    private ListNode merge(ListNode listA, ListNode listB) {
        if (listA == null) {
            return listB;
        }
        if (listB == null) {
            return listA;
        }

        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (listA != null && listB != null) {
            if (listA.val <= listB.val) {
                pre.next = listA;
                listA = listA.next;
            } else {
                pre.next = listB;
                listB = listB.next;
            }
            pre = pre.next;
        }
        pre.next = listA == null ? listB : listA;

        return dummy.next;
    }

    /**
     * 逐一比较法
     * @param lists
     * @return
     */
    public ListNode mergeKLists_210301_v2(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }

        ListNode[] points = new ListNode[n];
        for (int i = 0; i < n; i++) {
            points[i] = lists[i];
        }

        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (true) {
            ListNode minNode = null;
            int nodeIndex = -1;

            // 依次比较每个链表指针，找出最小节点
            for (int i = 0; i < n; i++) {
                if (points[i] == null) {
                    continue;
                }else if (minNode == null || minNode.val > points[i].val) {
                    minNode = points[i];
                    nodeIndex = i;
                }
            }

            // 所有指针都为null，说明已经都遍历完了
            if (minNode == null) {
                break;
            }

            // 插入新链表，指针往后移动一位
            pre.next = minNode;
            pre = pre.next;

            // 被选中的节点也向后移动一位
            points[nodeIndex] = minNode.next;
        }

        return dummy.next;
    }

    /**
     * 逐一比价+优先级队列
     * @param lists
     * @return
     */
    public ListNode mergeKLists_210301_v3(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }

        PriorityQueue<ListNodeHolder> queue = new PriorityQueue<>(n);

        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                queue.offer(new ListNodeHolder(lists[i]));
            }
        }

        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (!queue.isEmpty()) {
            ListNodeHolder holder = queue.poll();

            // 将节点加入最终链表，然后向后移动一位
            pre.next = holder.node();
            pre = pre.next;

            // 如果链表没有遍历完，将节点后移一位，重新加入队列
            holder.next();
            if (holder.node() != null) {
                queue.offer(holder);
            }
        }

        return dummy.next;
    }

    static class ListNodeHolder implements Comparable<ListNodeHolder>{
        private ListNode node;

        ListNodeHolder (ListNode head) {
            node = head;
        }

        ListNode node() {
            return node;
        }

        void next() {
            node = node.next;
        }

        @Override
        public int compareTo(ListNodeHolder o) {
            return node.val - o.node.val;
        }
    }

    /**
     * 逐一比价+优先级队列 V2
     * @param lists
     * @return
     */
    public ListNode mergeKLists_210301_v4(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(n, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();

            // 将节点加入最终链表，然后向后移动一位
            pre.next = node;
            pre = pre.next;

            // 如果链表没有遍历完，将节点后移一位，重新加入队列
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return dummy.next;
    }



    /**
     * 归并法
     * @param lists
     * @return
     */
    public ListNode mergeKLists_v3(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode[] pre = lists;
        while (pre.length > 1) {    // 直到数组只剩一个链表为止
            ListNode[] merged = new ListNode[(pre.length - 1) / 2 + 1];
            // 将数组中的链表两两合并
            for (int i = 0; i < pre.length; i += 2) {
                if (i + 1 < pre.length) {
                    merged[i / 2] = merge(pre[i], pre[i + 1]);
                } else {
                    merged[i / 2] = pre[i];
                }
            }
            pre = merged;
        }

        return pre[0];
    }
}
