package me.luliru.parctice.algorithm.array;

/**
 * LC88_合并两个有序数组
 * Created by luliru on 2021/4/5.
 */
public class LC88_合并两个有序数组 {

    /**
     * 双指针
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_210405_v1(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                tmp[k] = nums1[i];
                ++i;
            } else {
                tmp[k] = nums2[j];
                ++j;
            }
            ++k;
        }
        while (i < m) {
            tmp[k] = nums1[i];
            ++i;
            ++k;
        }
        while (j < n) {
            tmp[k] = nums2[j];
            ++j;
            ++k;
        }

        for (int t = 0; t < m + n; t++) {
            nums1[t] = tmp[t];
        }
    }

    /**
     * 逆向 + 双指针
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_210405_v2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                --i;
            } else {
                nums1[k] = nums2[j];
                --j;
            }
            --k;
        }
        while (i >= 0) {
            nums1[k] = nums1[i];
            --i;
            --k;
        }
        while (j >= 0) {
            nums1[k] = nums2[j];
            --j;
            --k;
        }
    }
}
