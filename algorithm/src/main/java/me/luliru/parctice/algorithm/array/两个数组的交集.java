package me.luliru.parctice.algorithm.array;

import java.util.*;

/**
 * 两个数组的交集
 * Created by luliru on 2020/11/2.
 */
public class 两个数组的交集 {

    /**
     * 排序+双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }
        if (nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> intersection = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int left = 0;
        int right = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] == nums2[right]) {
                intersection.add(nums1[left]);
                left ++;
                right ++;
            } else if (nums1[left] < nums2[right]) {
                left ++;
            } else {
                right ++;
            }
        }

        int[] res = new int[intersection.size()];
        Iterator<Integer> interator = intersection.iterator();
        int i = 0;
        while (interator.hasNext()) {
            res[i] = interator.next();
            i ++;
        }
        return res;
    }
}
