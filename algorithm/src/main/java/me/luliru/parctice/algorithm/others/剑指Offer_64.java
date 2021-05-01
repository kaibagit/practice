package me.luliru.parctice.algorithm.others;

/**
 * 剑指Offer_64
 * Created by luliru on 2021/3/15.
 */
public class 剑指Offer_64 {

    /**
     * ||运算断路写法
     * @param n
     * @return
     */
    public int sumNums(int n) {
        // 如果无限制的写法
//        if (n == 0) {
//            return 0;
//        }
//        return n + sumNums(n - 1);

        boolean ignore = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public int sumNums2(int n) {
        // 如果无限制的写法
//        return (1 + n) * n / 2;

        int ans = 0, a = 1 + n, b = n;
        boolean ignore = false;
        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        ignore = (b & 1) > 0 && (ans += a) > 0;
        a <<= 1;
        b >>= 1;

        return ans;
    }
}
