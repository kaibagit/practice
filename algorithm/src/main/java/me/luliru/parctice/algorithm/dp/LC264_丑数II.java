package me.luliru.parctice.algorithm.dp;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LC264_丑数II
 * Created by luliru on 2021/4/11.
 */
public class LC264_丑数II {

    /**
     * 递归 + 记忆化搜索（超时）
     * @param n
     * @return
     */
    public int nthUglyNumber_210411_v1(int n) {
        int seq = 0, num = -1;
        Set<Integer> mem = new HashSet<>();
        while (seq < n) {
            ++num;
            if (num == 1) {
                mem.add(num);
                ++seq;
            } else if (num % 2 == 0) {
                if (mem.contains(num / 2)) {
                    mem.add(num);
                    ++seq;
                }
            } else if (num % 3 == 0) {
                if (mem.contains(num / 3)) {
                    mem.add(num);
                    ++seq;
                }
            } else if (num % 5 == 0) {
                if (mem.contains(num / 5)) {
                    mem.add(num);
                    ++seq;
                }
            }
        }

        return num;
    }

    /**
     * 堆 + 哈希
     * @param n
     * @return
     */
    public int nthUglyNumber_210411_v2(int n) {
        int seq = 1;
        long lastNum = -1;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        HashSet<Long> set = new HashSet<>();
        while (seq <= n) {
            lastNum = heap.poll();
            long another = lastNum * 2;
            if (!set.contains(another)) {
                heap.offer(another);
                set.add(another);
            }
            another = lastNum * 3;
            if (!set.contains(another)) {
                heap.offer(another);
                set.add(another);
            }
            another = lastNum * 5;
            if (!set.contains(another)) {
                heap.offer(another);
                set.add(another);
            }
            ++seq;
        }
        return Long.valueOf(lastNum).intValue();
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int nthUglyNumber_210411_v3(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int nextP = 2, p2 = 1, p3 = 1, p5 = 1;
        while (nextP <= n) {    // 退出条件：p = n + 1
            int next = Math.min(Math.min(dp[p2] * 2, dp[p3] * 3), dp[p5] * 5);
            dp[nextP] = next;
            if (dp[p2] * 2 <= next) {
                ++p2;
            }
            if (dp[p3] * 3 <= next) {
                ++p3;
            }
            if (dp[p5] * 5 <= next) {
                ++p5;
            }
            ++nextP;
        }

        return dp[n];
    }
}
