package me.luliru.parctice.algorithm.divde_conquer;

/**
 * 计算x的n次幂函数
 * Created by luliru on 2021/2/13.
 */
public class 计算x的n次幂函数 {

    public static void main(String[] args) {
        double result = new 计算x的n次幂函数().myPow(2.0d, -2147483648);
        System.out.println(result);
    }

    /**
     * 分治+递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            return recurse(x, -n);
        } else {
            return recurse(x, n);
        }
    }

    public double recurse(double x, long n) {       // 当n=-2147483648时，取它的负数会溢出，所以需要转成long
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double y = recurse(x, n / 2);   // n如果是奇数，则相当于(n-1)/2
        if ((n & 1) == 1) {     // 奇数
            return y * y * x;
        } else {
            return y * y;
        }
    }
}
