package me.luliru.parctice.algorithm.string;

/**
 * LC14最长公共前缀
 * Created by luliru on 2021/2/18.
 */
public class LC14_最长公共前缀 {

    /**
     * 暴力破解（纵向扫描）
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefix_210218_v1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder ans = new StringBuilder();
        String firstStr = strs[0];
        for (int i = 0; i < firstStr.length(); i++) {
            char c = firstStr.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1 ||  strs[j].charAt(i) != c) {      // 处理["ab", "a"]的情况
                    return ans.toString();
                }
            }
            ans.append(c);
        }
        return ans.toString();
    }

    /**
     * 横向扫描
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefix_210218_v2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int end = -1;   // 如果没有字符匹配，则substring为（0，0）
        for (int i = 0; i < str1.length(); i++) {
            if (str2.length() < i + 1 || str1.charAt(i) != str2.charAt(i)) {
                break;
            }
            end = i;
        }
        return str1.substring(0, end + 1);
    }
}
