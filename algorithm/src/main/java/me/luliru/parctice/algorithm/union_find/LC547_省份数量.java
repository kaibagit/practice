package me.luliru.parctice.algorithm.union_find;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LC547_省份数量
 * Created by luliru on 2021/2/23.
 */
public class LC547_省份数量 {

    /**
     * DFS
     * @param isConnected
     * @return
     */
    public int findCircleNum_v1(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(isConnected, visited, i);
                ++count;
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }


    /**
     * BFS
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && isConnected[curr][j] == 1) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                }

                ++count;
            }
        }
        return count;
    }

    /**
     * 并查集
     * @param isConnected
     * @return
     */
    public int findCircleNum_v3(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {      // 整个二维数据只需要处理右上半部分就可以
                    continue;
                }
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.top.size();
    }
    static class UnionFind {

        Set<Integer> top;

        int[] parent;

        UnionFind(int n) {
            top = new HashSet<>(n);
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                top.add(i);
            }
        }

        void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent == yParent) {
                return;
            }
            parent[xParent] = yParent;
            top.remove(xParent);
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }
}
