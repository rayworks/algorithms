package org.sean.graph;

import java.util.*;

/***
 * 827. Making A Large Island
 */
public class LargeIsland {
    private final int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int[][] grid, int i, int j, int order, int cnt) {

        int n = grid.length;
        grid[i][j] = order;

        for (int[] move : moves) {
            int nR = i + move[0];
            int nC = j + move[1];
            if (nR >= 0 && nR < n && nC >= 0 && nC < n && grid[nR][nC] == 1) {
                grid[nR][nC] = order;
                cnt += dfs(grid, nR, nC, order, 1);
            }
        }
        return cnt;
    }

    // <order, size>
    private Map<Integer, Integer> map = new HashMap<>();

    // Large Island
    public int largestIsland(int[][] grid) {
        // dfs find all the SCCs
        // mark it and cnt the area
        // for each 0, check the max

        int order = 2;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int sum = dfs(grid, i, j, order, 1);
                    map.put(order, sum);

                    //System.out.printf("[%d:%d] - %d\n", i, j, sum);
                    order++;
                }
            }
        }

        if (map.isEmpty())
            return 1;

        int maxSize = 0, len = n;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    int sum = 1;
                    int top = (i > 0 && grid[i - 1][j] >= 1 ? grid[i - 1][j] : -1);
                    int bottom = (i < len - 1 && grid[i + 1][j] >= 1 ? grid[i + 1][j] : -1);
                    int left = (j > 0 && grid[i][j - 1] >= 1 ? grid[i][j - 1] : -1);
                    int right = (j < len - 1 && grid[i][j + 1] >= 1 ? grid[i][j + 1] : -1);

                    // concatenate the different area blocks
                    if (top != -1)
                        sum += map.get(top);
                    if (bottom != -1 && bottom != top) {
                        sum += map.get(bottom);
                    }
                    if (left != -1 && left != top && left != bottom) {
                        sum += map.get(left);
                    }
                    if (right != -1 && right != top && right != bottom && right != left) {
                        sum += map.get(right);
                    }

                    if (sum > maxSize) {
                        maxSize = sum;
                    }
                }
            }
        }


        return maxSize == 0 ? n * n : maxSize;
    }

    public static void main(String[] args) {
        LargeIsland island = new LargeIsland();
        int sz = island.largestIsland(new int[][]{{0, 1}, {1, 0}});
        System.out.println(">>> large size : " + sz);
    }
}
