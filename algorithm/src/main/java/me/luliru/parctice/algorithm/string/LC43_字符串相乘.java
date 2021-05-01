package me.luliru.parctice.algorithm.string;

/**
 * LC43字符串相乘
 * Created by luliru on 2021/2/19.
 */
public class LC43_字符串相乘 {

    public static void main(String[] args) {
        System.out.println(new LC43_字符串相乘().multiply("123", "45"));
        System.out.println(new LC43_字符串相乘().multiply("123", "456"));
        System.out.println(new LC43_字符串相乘().multiply("9133", "0"));
    }

    /**
     * 做加法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply_v1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int n = num1.length(), m = num2.length();
        for (int i = 0; i < n; i++) {
            int single = num1.charAt(n - i - 1) - '0';
            String product = singleMultiply(single, i, num2);
            ans = plus(ans, product);
        }
        return ans;
    }

    private String singleMultiply(int single, int zeros, String num) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < zeros; i++) {
            ans.append(0);
        }
        int carry = 0;
        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(num.length() - i - 1) - '0';
            int sum = single * val + carry;
            if (sum >= 10) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            ans.append(sum);
        }
        if (carry > 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }

    private String plus(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int n = num1.length(), m = num2.length();
        int i = 0;
        int carry = 0;
        while (i < n || i < m) {
            int v1 = i < n ? num1.charAt(n - i - 1) - '0' : 0;
            int v2 = i < m ? num2.charAt(m - i -1) - '0' : 0;
            int sum = v1 + v2 + carry;
            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }
            ans.append(sum);
            i++;
        }
        if (carry == 1) {
            ans.append("1");
        }
        return ans.reverse().toString();
    }

    /**
     * 做乘法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n = num1.length(), m = num2.length();
        int[] ansArr = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                // 2个数乘积，低位是i + j + 1，高位是i + j
                int sum = a * b + ansArr[i + j + 1];
                ansArr[i + j + 1] = sum % 10;
                // 因为是从后往前，所以即使数组中的int值大于9，也会在下一次循环时被处理
                // 如果已经是最左边的数，即使不处理，也不影响最终结果
                ansArr[i + j] += (sum / 10);
            }
        }

        // 统计前面0的个数
        int zeroCount = 0;
        for (int i = 0; i < ansArr.length; i++) {
            if (ansArr[i] != 0) {
                break;
            }
            zeroCount++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = zeroCount; i < ansArr.length; i++) {
            ans.append(ansArr[i]);
        }
        return ans.toString();
    }
}
