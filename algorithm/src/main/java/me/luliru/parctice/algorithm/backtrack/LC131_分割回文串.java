package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LC131_分割回文串
 * Created by luliru on 2021/3/7.
 */
public class LC131_分割回文串 {

    public static void main(String[] args) {
        new LC131_分割回文串().partition_210307_v2("aab");
    }

    /**
     * 回溯法
     * @param s
     * @return
     */
    public List<List<String>> partition_210307_v1(String s) {
        List<List<String>> ans = new ArrayList<>();

        backtracking(ans, s, 0, new ArrayList<>());

        return ans;
    }
    private void backtracking(List<List<String>> ans, String s, int start, List<String> path) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
        }
        for (int end = start + 1; end <= s.length(); end++) {   // end 不包含
            if (!validate(s, start, end)) {
                continue;
            }
            path.add(s.substring(start, end));
            backtracking(ans, s, end, path);
            path.remove(path.size() - 1);
        }
    }
    private boolean validate(String s, int start, int end) {
        int left = start, right = end - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    /**
     * 回溯法+动态优化
     * @param s
     * @return
     */
    public List<List<String>> partition_210307_v2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];     // dp[i][j] 代表 s[i,j]这段是否为回文
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if(s.charAt(i) == s.charAt(j)) {
                    if (i == j || i + 1 == j || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();

        backtrackingV2(ans, dp, s, 0, new ArrayList<>());

        return ans;
    }

    private void backtrackingV2(List<List<String>> ans, boolean[][] dp, String s, int start, List<String> path) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
        }

        for (int end = start + 1; end <= s.length(); end++) {     // end不包含
            if(!dp[start][end - 1]) {
                continue;
            }
            path.add(s.substring(start, end));
            backtrackingV2(ans, dp, s, end, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 回溯法+记忆化搜索
     * @param s
     * @return
     */
    public List<List<String>> partition_210307_v3(String s) {
        int n = s.length();
        List<List<String>> ans = new ArrayList<>();
        Boolean[][] validateMem = new Boolean[n][n];
        backtrackingV3(ans, validateMem, s, 0, new ArrayList<>());

        return ans;
    }
    private void backtrackingV3(List<List<String>> ans, Boolean[][] validateMem, String s, int start, List<String> path) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
        }
        for (int end = start + 1; end <= s.length(); end++) {   // end 不包含
            if (!validateV3(validateMem, s, start, end)) {
                continue;
            }
            path.add(s.substring(start, end));
            backtrackingV3(ans, validateMem, s, end, path);
            path.remove(path.size() - 1);
        }
    }
    private boolean validateV3(Boolean[][] validateMem, String s, int start, int end) {     // end 不包含
        if (validateMem[start][end - 1] != null) {
            return validateMem[start][end - 1];
        }

        if (s.charAt(start) == s.charAt(end - 1)) {
            if (start == end - 1 || start + 1 == end - 1 || validateV3(validateMem, s, start + 1, end - 1)) {
                validateMem[start][end - 1] = true;
                return true;
            }
        }

        validateMem[start][end - 1] = false;
        return false;
    }


}
