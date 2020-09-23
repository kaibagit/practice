package me.luliru.parctice.algorithm.dp;

public class 斐波那契数 {

    public int fib_递归(int N) {
        if(N ==0) {
            return 0;
        }
        if(N == 1) {
            return 1;
        }
        return fib_递归(N-1) + fib_递归(N-2);
    }

    public int fib_dp(int N) {
        if(N ==0) {
            return 0;
        }
        if(N == 1) {
            return 1;
        }
        int sum_1 = 1;
        int sum_2 = 0;
        int result = 0;
        for(int i=2;i<=N;i++) {
            result = sum_1 + sum_2;
            sum_2 = sum_1;
            sum_1 = result;
        }
        return result;
    }
}
