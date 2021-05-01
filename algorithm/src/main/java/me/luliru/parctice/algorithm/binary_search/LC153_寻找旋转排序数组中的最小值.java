package me.luliru.parctice.algorithm.binary_search;

/**
 * LC153_寻找旋转排序数组中的最小值
 * Created by luliru on 2021/3/8.
 */
public class LC153_寻找旋转排序数组中的最小值 {


    /**
     * 二分查找
     * @param nums
     * @return
     */
    public int findMin_210408(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }







































    /**
     * 二分查找
     * @param nums
     * @return
     */
    public int findMin_210308_v1(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 有可能二分之后，左右两边都是有序的，如：[4,5,6,7,0,1,2]
        // 单凭是否无序，是不足以判断，所以需要记录每个有序分区的最左值，取最小的
        int min = nums[0];
        while (left <= right) {     // 退出条件：left = right + 1
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {
                min = Math.min(nums[left], min);
                // 左边有序
                left = mid + 1;
            } else {
                // 右边有序
                right = mid;
            }
        }
        return min;
    }

    /**
     * 二分查找，v1变种
     * @param nums
     * @return
     */
    public int findMin_210308_v2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {     // // 实际上是不会跳出循环，当 left==right 时直接返回
            // 针对二分之后，左右两边都是有序的情况，则最小值必定在右侧
            // 下次迭代，右侧如果一开始就是有序，则最左边就是最小值
            if (nums[left] <= nums[right]) { // 如果 [left,right] 递增，直接返回
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {
                // 左边有序
                left = mid + 1;
            } else {
                // 右边有序
                right = mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * @param nums
     * @return
     */
    public int findMin_210308_v3(int[] nums) {
        int left = 0, right = nums.length - 1;  /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                  /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] < nums[right]) {      /* 明确中值 < 右值，最小值在左半边，收缩右边界 */
                // 右边有序                         /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
                right = mid;
            } else {
                // 左边有序                     /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                 /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            }
        }
        return nums[left];                       /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
    }
}
