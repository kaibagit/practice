package me.luliru.parctice.algorithm.union_find;

import java.util.LinkedList;

/**
 * LC695_岛屿的最大面积
 * Created by luliru on 2021/2/22.
 */
public class LC695_岛屿的最大面积 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{new int[]{0,1,1},new int[]{1,0,1}};
        new LC695_岛屿的最大面积().maxAreaOfIsland(grid);
    }

    /**
     * 并查集
     * @param grid
     * @return
     */
    public int maxAreaOfIsland_v3(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (j + 1 < m && grid[i][j + 1] == 1) {
                        uf.union(i * m + j, i * m + j + 1);
                    }
                    if (i + 1 < n && grid[i + 1][j] == 1) {
                        uf.union(i * m + j, (i + 1) * m + j);
                    }
                }
            }
        }

        return uf.maxArea;
    }

    static class UnionFind {
        int maxArea = 0;
        int[] parent;
        int[] area;

        UnionFind(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            parent = new int[n * m];
            area = new int[n * m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        parent[i * m + j] = i * m + j;
                        area[i * m + j] = 1;
                        maxArea = 1;
                    }
                }
            }
        }

        void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent == yParent) {
                return;
            }
            // x父节点吞并y父节点
            if (area[xParent] > area[yParent]) {
                parent[yParent] = xParent;
                area[xParent] += area[yParent];
                maxArea = Math.max(maxArea, area[xParent]);
            } else {
                parent[xParent] = yParent;
                area[yParent] += area[xParent];
                maxArea = Math.max(maxArea, area[yParent]);
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }

    /**
     * DFS
     * @param grid
     * @return
     */
    public int maxAreaOfIsland_v1(int[][] grid) {
        int maxArea = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid,i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        int area = 1;
        grid[i][j] = 0;
        if (j > 0 && grid[i][j - 1] == 1) {
            area += dfs(grid, i, j - 1);
        }
        if (j + 1 < m && grid[i][j + 1] == 1) {
            area += dfs(grid, i, j + 1);
        }
        if (i  > 0 && grid[i - 1][j] == 1) {
            area += dfs(grid, i - 1, j);
        }
        if (i + 1 < n && grid[i + 1][j] == 1) {
            area += dfs(grid, i + 1, j);
        }
        return area;
    }

    /**
     * BFS
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    queue.offer(i * m + j);
                    int area = 0;
                    while (!queue.isEmpty()) {
                        int position = queue.poll();
                        ++area;
                        int row = position / m;
                        int col = position % m;
                        if (col > 0 && grid[row][col - 1] == 1) {
                            grid[row][col - 1] = 0;
                            queue.offer(row * m + col - 1);
                        }
                        if (col + 1 < m && grid[row][col + 1] == 1) {
                            grid[row][col + 1] = 0;
                            queue.offer(row * m + col + 1);
                        }
                        if (row > 0 && grid[row - 1][col] == 1) {
                            grid[row - 1][col] = 0;
                            queue.offer((row - 1) * m + col);
                        }
                        if (row + 1 < n && grid[row + 1][col] == 1) {
                            grid[row + 1][col] = 0;
                            queue.offer((row + 1) * m + col);
                        }
                    }
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }
}
