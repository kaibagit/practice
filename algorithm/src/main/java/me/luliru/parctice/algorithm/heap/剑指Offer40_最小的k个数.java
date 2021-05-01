package me.luliru.parctice.algorithm.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指Offer40_最小的k个数
 * Created by luliru on 2021/3/9.
 */
public class 剑指Offer40_最小的k个数 {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,1};
        new 剑指Offer40_最小的k个数().getLeastNumbers_210308_v3(arr, 1);
    }

    /**
     * 排序
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_210308_v1(int[] arr, int k) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return Arrays.copyOfRange(copy, 0 , k);
    }

    /**
     * 优先级队列
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_210308_v2(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }

        return ans;
    }

    /**
     * 优先级队列+空间优化
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_210308_v3(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            ans[i] = queue.poll();
        }

        return ans;
    }
}
