package me.luliru.parctice.algorithm.greedy;

/**
 * 单调递增的数字
 * Created by luliru on 2020/12/15.
 */
public class LC738_单调递增的数字 {

    public static void main(String[] args) {
        new LC738_单调递增的数字().monotoneIncreasingDigits(1234321);
        new LC738_单调递增的数字().monotoneIncreasingDigits(2333332);
    }

    /**
     * 参考：https://leetcode-cn.com/problems/monotone-increasing-digits/solution/jian-dan-tan-xin-shou-ba-shou-jiao-xue-k-a0mp/
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        char[] increasingDigits = String.valueOf(N).toCharArray();
        int maxDigit = -1;
        int firstIndexOfMaxDigit = -1;
        for (int i = 0; i < increasingDigits.length - 1; i++) {
            // 如果数字本身是单调递增的，那最好
            // 如果不是，则找到第一个i > i+1的位置，将该位置的值-1，然后把后面所有数都变成9即可
            if (increasingDigits[i] > maxDigit) {
                maxDigit = increasingDigits[i];
                firstIndexOfMaxDigit = i;
            }
            if (increasingDigits[i] > increasingDigits[i + 1]) {
                increasingDigits[firstIndexOfMaxDigit]--;
                for (int j = firstIndexOfMaxDigit + 1; j < increasingDigits.length; j++) {
                    increasingDigits[j] = '9';
                }
                break;
            }
        }

        return Integer.valueOf(String.valueOf(increasingDigits));
    }
}
