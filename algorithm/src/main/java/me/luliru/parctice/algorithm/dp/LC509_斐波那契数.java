package me.luliru.parctice.algorithm.dp;

public class LC509_斐波那契数 {


    /**
     * 递归
     * @param n
     * @return
     */
    public int fib_recursion(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib_recursion(n - 1) + fib_recursion(n - 2);
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int fib_dp(int n) {
        // 处理边界
        if (n == 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 动态规划+空间优化
     * @param n
     * @return
     */
    public int fib_dp_optimize(int n) {
        // 处理边界
        if (n == 0) return 0;
        if (n == 1) return 1;

        int minus2 = 0;
        int minus1 = 1;
        for (int i = 2; i <= n; i++) {
            int val = minus1 + minus2;

            // 下一轮循环
            minus2 = minus1;
            minus1 = val;
        }

        return minus1;
    }







































    public int fib_递归(int N) {
        if(N ==0) {
            return 0;
        }
        if(N == 1) {
            return 1;
        }
        return fib_递归(N-1) + fib_递归(N-2);
    }

    public int fib_dp_200917(int N) {
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
