package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LC93_复原IP地址
 * Created by luliru on 2021/2/20.
 */
public class LC93_复原IP地址 {

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(ans, s, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(List<String> ans, String s, List<String> path, int chooseLower) {     // 包含chooseLowe
        // 满足条件，加入结果
        if (path.size() == 4) {
            if (chooseLower == s.length()) {
                // 加入结果
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < path.size(); i++) {
                    builder.append(path.get(i)).append('.');
                }
                builder.deleteCharAt(builder.length() - 1);
                ans.add(builder.toString());
            }
            return;     // 有多余数字，不合法
        }

        // 字符串的长度小于 4 或者大于 12 ，一定不能拼凑出合法的 ip 地址 => 规律泛化，即剩余ip段个数 * 1 <= 剩余数字 <= 剩余ip段个数 * 3
        if (s.length() - chooseLower < 4 - path.size()) {
            return;
        }
        if (s.length() - chooseLower > 3 * (4 - path.size())) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (chooseLower + i > s.length()) {     // 剩余数字已不够用
                continue;
            }
            // 做出选择
            String select = s.substring(chooseLower, chooseLower + i);
            if (select.length() > 1 && select.charAt(0) == '0') {   // 0开头的数字，不合法，直接跳过
                continue;
            }
            if (Integer.valueOf(select) > 255) {
                continue;
            }
            path.add(select);
            dfs(ans, s, path, chooseLower + i);

            // 撤销选择
            path.remove(path.size() - 1);
        }

    }
}
