package me.luliru.parctice.algorithm.dp;

/**
 * LC115_不同的子序列
 * Created by luliru on 2021/3/17.
 */
public class LC115_不同的子序列 {

    public static void main(String[] args) {
        System.out.println(new LC115_不同的子序列().numDistinct_210316_v1("rabbbit", "rabbit"));
        System.out.println(new LC115_不同的子序列().numDistinct_210316_v2("babgbag", "bag"));
    }

    /**
     * 递归 + 记忆化搜索
     * @param s
     * @param t
     * @return
     */
    public int numDistinct_210316_v1(String s, String t) {
        Integer[][] mem = new Integer[s.length()][t.length()];
        return recurse(s, t, mem, s.length() - 1, t.length() - 1);
    }

    private int recurse(String s, String t, Integer[][] mem, int i, int j) {
        if (i < j) {
            return 0;
        }

        if (j == -1) {
            return 1;
        }

        if (mem[i][j] != null) {
            return mem[i][j];
        }

        int count = 0;
        if (s.charAt(i) == t.charAt(j)) {
            count += recurse(s, t, mem, i - 1, j - 1);
        }

        count += recurse(s, t, mem, i - 1, j);

        mem[i][j] = count;
        return count;
    }

    /**
     * 动态规划
     * @param s
     * @param t
     * @return
     */
    public int numDistinct_210316_v2(String s, String t) {
        Integer[][] mem = new Integer[s.length() + 1][t.length() + 1];      // 要区分0个字符，1个字符的情况，所以这里长度为n + 1
        // 当t为空字符串时，是所有字符的子串
        for (int i = 0; i < s.length() + 1; i++) {
            mem[i][0] = 1;
        }

        // 当s为空时，不是所有非空字符串的子串
        for (int j = 1; j < t.length() + 1; j++) {
            mem[0][j] = 0;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    mem[i][j] = mem[i - 1][j - 1] + mem[i - 1][j];
                } else {
                    mem[i][j] = mem[i - 1][j];
                }
            }
        }

        return mem[s.length()][t.length()];
    }
}
