package me.luliru.parctice.algorithm.backtrack;

import java.util.*;

/**
 * 全排列
 * Created by luliru on 2020/9/26.
 */
public class LC46_全排列 {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 第二次编写
     * @param nums
     * @return
     */
    public List<List<Integer>> permute_v2(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        backtrace_v2(path,nums);
        return res;
    }

    private void backtrace_v2(LinkedList<Integer> path, int[] nums) {
        if(path.size() == nums.length) {
            List<Integer> sol = new ArrayList<>(path);
            res.add(sol);
            return;
        }

        for(int i=0;i<nums.length;i++) {
            int select = nums[i];
            // 剪枝
            if(path.contains(select)) {
                continue;
            }

            // 选择
            path.add(select);

            // 深度遍历
            backtrace_v2(path,nums);

            // 撤销选择
            path.removeLast();
        }
    }


    /**
     * 第一次编写
     * @param nums
     * @return
     */
    public List<List<Integer>> permute_v3(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }




















    public List<List<Integer>> permute_210220(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), new HashSet<>(), nums);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> path, Set<Integer> used, int[] nums) {  // used用于性能优化
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            int select = nums[i];
            if (used.contains(select)) {
                continue;
            }
            // 做出选择
            path.add(select);
            used.add(select);
            dfs(ans, path, used, nums);
            // 撤销选择
            path.remove(path.size() - 1);
            used.remove(select);
        }
    }























}
