package me.luliru.parctice.algorithm.recurse;

/**
 * LC263_丑数
 * Created by luliru on 2021/4/11.
 */
public class LC263_丑数 {

    /**
     * 递归
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        if (n % 2 == 0) {
            return isUgly(n / 2);
        }

        if (n % 3 == 0) {
            return isUgly(n / 3);
        }

        if (n % 5 == 0) {
            return isUgly(n / 5);
        }

        return false;
    }
}
