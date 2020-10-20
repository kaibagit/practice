package me.luliru.parctice.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * Created by luliru on 2020/10/18.
 */
public class 回文链表 {

    public boolean isPalindrome(ListNode head) {
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
