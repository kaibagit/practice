package me.luliru.parctice.algorithm.array;

/**
 * 缺失数字
 * Created by luliru on 2020/9/25.
 */
public class 缺失数字 {

    public int missingNumber(int[] nums) {
        boolean[] arr = new boolean[nums.length+1];
        for(int i=0;i<nums.length;i++) {
            int v = nums[i];
            arr[v] = true;
        }
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == false) {
                return i;
            }
        }
        return -1;
    }
}
