package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC47_全排列II
 * Created by luliru on 2021/2/20.
 */
public class LC47_全排列II {

    public static void main(String[] args) {
        new LC47_全排列II().permuteUnique(new int[]{1,1,2});
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 需要先排序
        Arrays.sort(nums);
        dfs(ans, new ArrayList<>(), new boolean[nums.length], nums);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> path, boolean[] used, int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            int select = nums[i];

            if (used[i]) {
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && select == nums[i - 1] && used[i - 1] == false) {   // 处理相同数字被处理了2次
                continue;
            }

            // 做出选择
            path.add(select);
            used[i] = true;
            dfs(ans, path, used, nums);

            // 撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
