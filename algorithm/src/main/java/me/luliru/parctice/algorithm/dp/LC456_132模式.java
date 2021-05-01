package me.luliru.parctice.algorithm.dp;

/**
 * LC456_132模式
 * Created by luliru on 2021/3/25.
 */
public class LC456_132模式 {

    public static void main(String[] args) {
//        new LC456_132模式().find132pattern_210325_V2(new int[]{3,1,4,2});
//        new LC456_132模式().find132pattern_210325_V2(new int[]{3,5,0,3,4});
    }

    /**
     * 暴力法
     * @param nums
     * @return
     */
    public boolean find132pattern_210325_V1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            for (int l = 0; l < i; l++) {
                for (int r = i + 1; r < nums.length; r++) {
                    if (nums[l] < nums[r] && nums[r] < nums[i]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
