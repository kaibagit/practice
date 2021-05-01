package me.luliru.parctice.algorithm.union_find;

import java.util.LinkedList;

/**
 * LC200岛屿数量
 * Created by luliru on 2021/2/20.
 */
public class LC200_岛屿数量 {

    public static void main(String[] args) {
        char[][] grid = new char[][]{new char[]{'1'}, new char[]{'1'}};
        System.out.println(new LC200_岛屿数量().numIslands_210329_v2(grid));
    }


    /**
     * DFS
     * @param grid
     * @return
     */
    public int numIslands_210329_v1(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ++count;
                    dfs_210229(grid, m, n, i, j);
                }
            }
        }

        return count;
    }

    private void dfs_210229(char[][] grid, int m, int n, int i, int j) {
        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        // 岛屿有可能是-|，或者是-|的形状，所以需要往左往上查找
        if (i > 0) {
            dfs_210229(grid, m , n, i - 1, j);
        }
        if (i + 1 < m) {
            dfs_210229(grid, m , n, i + 1, j);
        }
        if (j > 0) {
            dfs_210229(grid, m , n, i, j - 1);
        }
        if (j + 1 < n) {
            dfs_210229(grid, m , n, i, j + 1);
        }

    }


    /**
     * 并查集
     * @param grid
     * @return
     */
    public int numIslands_210329_v2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == '1') {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                }
            }
        }

        return uf.count;
    }

    static class UnionFind {

        private int[] parents;
        private int count;

        UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parents = new int[m * n];
            for (int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    parents[i * n + j] = i * n + j;
                    if (grid[i][j] == '1') {
                        ++count;
                    }
                }
            }
        }

        void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);

            if (xParent != yParent) {
                parents[yParent] = xParent;
                --count;
            }
        }

        int find(int x) {
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];   // 路径优化
                x = parents[x];
            }
            return x;
        }
    }















    /**
     * DFS
     * @param grid
     * @return
     */
    public int numIslands_v1(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    change(grid, i, j);
                }
            }
        }
        return count;
    }

    private void change(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') {
            return;
        }
        int n = grid.length, m = grid[0].length;
        grid[i][j] = '0';
        if (j > 0) {
            change(grid, i , j - 1);
        }
        if (j < m - 1) {
            change(grid, i, j + 1);
        }
        if (i > 0) {
            change(grid, i - 1, j);
        }
        if (i < n - 1) {
            change(grid, i + 1, j);
        }
    }

    /**
     * BFS
     * @param grid
     * @return
     */
    public int numIslands_v2(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        LinkedList<Node> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    queue.offer(new Node(i, j));
                    while (!queue.isEmpty()) {
                        Node node = queue.poll();
                        if (node.col > 0 && grid[node.row][node.col - 1] == '1') {
                            grid[node.row][node.col - 1] = '0';
                            queue.offer(new Node(node.row, node.col - 1));
                        }
                        if (node.col < m - 1 && grid[node.row][node.col + 1] == '1') {
                            grid[node.row][node.col + 1] = '0';
                            queue.offer(new Node(node.row, node.col + 1));
                        }
                        if (node.row > 0 && grid[node.row - 1][node.col] == '1') {
                            grid[node.row - 1][node.col] = '0';
                            queue.offer(new Node(node.row - 1, node.col));
                        }
                        if (node.row < n - 1 && grid[node.row + 1][node.col] == '1') {
                            grid[node.row + 1][node.col] = '0';
                            queue.offer(new Node(node.row + 1, node.col));
                        }
                    }
                }
            }
        }
        return count;
    }

    static class Node {
        int row;
        int col;
        Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * 并查集
     * @param grid
     * @return
     */
//    public int numIslands(char[][] grid) {
//        int n = grid.length, m = grid[0].length;
//        UnionFind uf = new UnionFind(grid);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (grid[i][j] == '1') {
//                    // 往右找
//                    if (j + 1 < m && grid[i][j + 1] == '1') {
//                        uf.union(i * m + j, i * m + j + 1);
//                    }
//                    // 往下找
//                    if (i + 1 < n && grid[i + 1][j] == '1') {
//                        uf.union(i * m + j, (i + 1) * m + j);
//                    }
//                }
//            }
//        }
//        return uf.count;
//    }
//
//    static class UnionFind {
//        int count;      // 岛屿个数
//        int[] parent;
//
//        UnionFind(char[][] grid) {
//            int n = grid.length, m = grid[0].length;
//            parent = new int[n * m];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (grid[i][j] == '1') {
//                        parent[i * m + j] = i * m + j;
//                        ++count;
//                    }
//                }
//            }
//        }
//
//        void union(int x, int y) {
//            int xParent = find(x);
//            int yParent = find(y);
//            if (xParent == yParent) {
//                return;
//            }
//            parent[xParent] = yParent;
//            --count;
//        }
//
//        /**
//         * 找到最终的parent
//         * @param x
//         * @return
//         */
//        int find(int x) {
//            // 一直向上查找自己的父母
//            while (x != parent[x]) {
//                // 压缩路径，optional
//                parent[x] = parent[parent[x]];
//                x = parent[x];
//            }
//            return x;
//        }
//    }
}
