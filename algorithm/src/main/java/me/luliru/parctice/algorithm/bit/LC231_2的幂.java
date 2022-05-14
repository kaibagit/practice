package me.luliru.parctice.algorithm.bit;

/**
 * LC231_2的幂
 * Created by luliru on 2022/5/9.
 */
public class LC231_2的幂 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

}
