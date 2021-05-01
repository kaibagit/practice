package me.luliru.parctice.algorithm.string;

/**
 * LC415字符串相加
 * Created by luliru on 2021/2/19.
 */
public class LC415_字符串相加 {

    public static void main(String[] args) {
        new LC415_字符串相加().addStrings("9", "99");
    }

    /**
     * 模拟数学加法
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int n = num1.length(), m = num2.length();
        int i = 0;
        boolean carry = false;  // 进位
        while (i < n || i < m) {
            int v1 = i < n ? num1.charAt(n - i - 1) - '0' : 0;
            int v2 = i < m ? num2.charAt(m - i - 1) - '0' : 0;
            int sum = v1 + v2 + (carry ? 1 : 0);
            if (sum >= 10) {
                sum -= 10;
                carry = true;
            } else {
                carry = false;
            }
            ans.append(sum);
            i++;
        }
        // 处理最大位数的进位
        if (carry) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }
}
