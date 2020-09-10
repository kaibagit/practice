package me.luliru.parctice.algorithm.array;

/**
 * 移动零

 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]

 说明:

 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/move-zeroes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/8/30.
 */
public class 移动零 {

    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        mine_moveZeroes(arr);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
            System.out.print(",");
        }
    }

    public static void mine_moveZeroes(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            int n = nums[i];
            if(n == 0) {
                count++;
            }else {
                nums[i-count] = n;
            }
        }
        for(int i=nums.length-1;i>nums.length-1-count;i--) {
            nums[i] = 0;
        }
    }
}
