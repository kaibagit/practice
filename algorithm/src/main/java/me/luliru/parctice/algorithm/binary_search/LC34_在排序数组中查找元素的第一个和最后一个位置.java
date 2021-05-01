package me.luliru.parctice.algorithm.binary_search;

/**
 * LC34_在排序数组中查找元素的第一个和最后一个位置
 * Created by luliru on 2021/3/8.
 */
public class LC34_在排序数组中查找元素的第一个和最后一个位置 {

    public static void main(String[] args) {
//        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210312(new int[]{5,7,7,8,8,10}, 8);
//        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210312(new int[]{1}, 1);
        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210312(new int[]{1}, 2);
        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210312(new int[]{1}, 0);
        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210312(new int[]{2,2}, 2);
//        new LC34_在排序数组中查找元素的第一个和最后一个位置().searchRange_210308_v2(new int[]{2, 2}, 3);
    }

    /**
     * 暴力法
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange_210308_v1(int[] nums, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            } else if (nums[i] > target) {
                break;
            }
        }
        return new int[]{first, last};
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange_210308_v2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        return new int[]{firstIndex(nums, target), lastIndex(nums, target)};
    }

    private int firstIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {      // 退出时，left == right
            int mid = left + (right - left) / 2;

            // 当遍历到nums[i] == target时，有3种情况：
            // 1、i是相同target最左边的一个
            // 2、i的左右两边都有相同的值
            // 3、i是相同target最右边的一个

            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;    // 一直缩小范围，有可能最终没找到
    }

    private int lastIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {      // 退出时，left == right
            // 上取整之前的逻辑如下：
//            int mid = left + (right - left) / 2;
//            if (target < nums[mid]) {
//                right = mid - 1;
//            } else if (target == nums[mid]) {
//                left = mid;
//            } else if (target > nums[mid]) {
//                left = mid + 1;
//            }
            // 这里注意到，出现了left = mid 和left = mid + 1，需要特殊处理
            // 所以取中间数mid时，需要上取整，变成(left + right + 1) / 2，否则会出现死循环，如[5,7,7,8,8,10]，查找8
            // 而left = mid 和left = mid + 1合并为left = mid，否则会出现index溢出，如[2,2]，查找3
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                left = mid;
            } else if (target > nums[mid]) {
                left = mid;      // 向上取整，如果left = mid + 1，则会溢出，所以这里少上移一位，反正向上取整，最后会一直靠近right
            }
        }
        return nums[right] == target ? right : -1;    // 一直缩小范围，有可能最终没找到；因为是最右侧，所以向上取整，正好对应最左侧取left，反正最终left=right，right更好理解点
    }

    public int[] searchRange_210312(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        return new int[]{firstIndex_v2(nums, target), lastIndex_v2(nums, target)};
    }

    /**
     * 左闭右开，取靠左的索引，所以mid要取下中位数，压缩右边界，最终让nums[left] = target
     * @param nums
     * @param target
     * @return
     */
    private int firstIndex_v2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {      // 退出条件：left = right，区间为空
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        return nums[left] == target ? left : -1;
    }
    private int lastIndex_v2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {      // 退出条件：left = right
            int mid = left + (right - left + 1) / 2;    // 向上取整
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                left = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        return nums[right] == target ? right : -1;  // 对称写法，这里取right，left有可能会溢出
    }
}
