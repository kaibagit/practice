package me.luliru.parctice.algorithm.bit;

/**
 * LC190_颠倒二进制位
 * Created by luliru on 2021/3/29.
 */
public class LC190_颠倒二进制位 {

    public static void main(String[] args) {
        new LC190_颠倒二进制位().reverseBits(43261596);
    }

    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0, markRight = 1, markLeft = Integer.parseUnsignedInt("10000000000000000000000000000000", 2); i < 32; i++, markRight <<= 1, markLeft >>>= 1) {
            if ((n & markRight) == markRight) {
                ans = ans ^ markLeft;
            }
        }
        return ans;
    }
}
