package me.luliru.parctice.algorithm.dp;

/**
 * 接雨水
 * Created by luliru on 2020/10/19.
 */
public class LC42_接雨水 {

    public static void main(String[] args) {
//        new LC42_接雨水().trap_dp(new int[]{4,2,0,3,2,5});
        new LC42_接雨水().trap_210223_v1(new int[]{4,2,3});
    }


    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap_210326_v1(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int sum = 0;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {      // 退出条件：left = right，此时left为整个数据的最大高度，不会积蓄，所以不需要考虑
            if (height[left] < height[right]) {     // 左边比较矮，值可能在左边积蓄
                if (height[left] < leftMax) {       // 当前高度比leftMax矮，所以可以积水
                    sum += (leftMax - height[left]);
                } else {                            // 当前高度比leftMax高，所以积水为0，需要更新左侧最大值
                    leftMax = height[left];
                }
                ++left;
            } else {                                // 右边比较矮，只可能在右边积蓄
                if (height[right] < rightMax) {     // 当前高度比rightMax矮，所以可以积水
                    sum += (rightMax - height[right]);
                } else {                            // 当前高度比rightMax高，所以积水为0，需要更新右侧最大值
                    rightMax = height[right];
                }
                --right;
            }
        }

        return sum;
    }


























    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap_210223_v1(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int sum = 0;
        int leftMax = height[0], rightMax = height[n - 1];
        int left = 1, right = n - 2;
        while (left <= right) {
            if (leftMax <= rightMax) {
                if (height[left] < leftMax && height[left] < rightMax) {   // 在凹陷处才能蓄水
                    sum += leftMax - height[left];
                }
                leftMax = Math.max(leftMax, height[left]);
                ++left;
            } else {
                if (height[right] < leftMax && height[right] < rightMax) {
                    sum += rightMax - height[right];
                }
                rightMax = Math.max(rightMax, height[right]);
                --right;
            }
        }
        return sum;
    }

    /**
     * 动态规划
     * @param height
     * @return
     */
    public int trap_210223_v2(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int[] leftDp = new int [n - 1];
        int leftMax = height[0];
        for (int i = 1; i < n - 1; i++) {
            if (leftMax > height[i]) {
                leftDp[i] = leftMax - height[i];
            }
            leftMax = Math.max(height[i], leftMax);
        }

        int[] rightDp = new int[n - 1];
        int rightMax = height[n - 1];
        for (int i = n - 2; i > 0; i--) {
            if (rightMax > height[i]) {
                rightDp[i] = rightMax - height[i];
            }
            rightMax = Math.max(height[i], rightMax);
        }

        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum += Math.min(leftDp[i], rightDp[i]);
        }
        return sum;
    }

    /**
     * 暴力求解
     * @param height
     * @return
     */
    public int trap_210223_v3(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            // 寻找左侧最高值
            int leftMax = height[i];
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(height[j], leftMax);
            }
            // 寻找右侧最高值
            int rightMax = height[i];
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(height[j], rightMax);
            }
            // 计算当前位置能蓄水多少
            sum += Math.min(leftMax, rightMax) - height[i];
        }

        return sum;
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
