package me.luliru.parctice.algorithm.dp;

/**
 * 接雨水
 * Created by luliru on 2020/10/19.
 */
public class 接雨水 {

    public static void main(String[] args) {
        new 接雨水().trap_dp(new int[]{4,2,0,3,2,5});
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height.length < 3){
            return 0;
        }
        int ans = 0;

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        while(left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if(leftMax <= rightMax){
                ans += leftMax - height[left];
                left++;
            }else{
                ans += rightMax - height[right];
                right--;
            }
        }

        return ans;
    }

    /**
     * 双动态规划数组
     * @param height
     * @return
     */
    public int trap_dp(int[] height) {
        if(height.length < 3){
            return 0;
        }
        int ans = 0;

        // 初始化dp数组
        int[] leftMaxArr = new int[height.length];
        int[] rightMaxArr = new int[height.length];
        int maxHeight = 0;
        for(int i=0;i<height.length;i++){
            if(height[i] > maxHeight) {
                maxHeight = height[i];
            }
            leftMaxArr[i] = maxHeight;
        }
        maxHeight = 0;
        for(int i=height.length-1;i>=0;i--){
            if(height[i] > maxHeight) {
                maxHeight = height[i];
            }
            rightMaxArr[i] = maxHeight;
        }

        for(int i=1;i<height.length-1;i++){
            int boundLower = Math.min(leftMaxArr[i],rightMaxArr[i]);
            if(height[i] < boundLower) {
                ans += boundLower - height[i];
            }
        }

        return ans;
    }
}
