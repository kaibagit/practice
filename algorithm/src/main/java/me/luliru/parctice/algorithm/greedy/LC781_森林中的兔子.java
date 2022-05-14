package me.luliru.parctice.algorithm.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * LC781_森林中的兔子
 * Created by luliru on 2021/4/4.
 */
public class LC781_森林中的兔子 {

    /**
     * 哈希
     * @param answers
     * @return
     */
    public int numRabbits_210404(int[] answers) {
        // 两只相同颜色的兔子看到的其他同色兔子数必然是相同的。反之，若两只兔子看到的其他同色兔子数不同，那么这两只兔子颜色也不同。
        // 因此，将 \textit{answers}answers 中值相同的元素分为一组，对于每一组，计算出兔子的最少数量，然后将所有组的计算结果累加，就是最终的答案。
        Map<Integer/* 回答的数量 */, Integer/* 该数量出现的次数 */> sat = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            sat.put(answers[i], sat.getOrDefault(answers[i], 0) + 1);
        }

        // 假设x只兔子回答了y，则该种颜色的兔子数为y + 1，所需最少的颜色数为 colors = ((x - 1) / (y + 1)) + 1，这里向下取整，所以分子为(x - 1)
        // 这些兔子的数量则为 colors * (y + 1)
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : sat.entrySet()) {
            int x = entry.getValue();
            int y = entry.getKey();
            int colors = (x - 1) / (y + 1) + 1;
            int num = colors * (y + 1);
            ans += num;
        }

        return ans;
    }
}
