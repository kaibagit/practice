package me.luliru.parctice.algorithm.bit;

/**
 * LC191_位1的个数
 * Created by luliru on 2021/3/22.
 */
public class LC191_位1的个数 {

    public static void main(String[] args) {
//        new LC191_位1的个数().hammingWeight_210322()
    }


    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }


































    public int hammingWeight_210322(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            ++count;
        }
        return count;
    }
}
