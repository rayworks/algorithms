package org.sean.array;

import java.util.*;

/***
 * 827. Making A Large Island
 */
public class LargeIsland {
    private boolean[][] visited;
    private final int[][] moves = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private final List<int[]> targetCoordination = new ArrayList<>();

    private int maxSize = 0;

    // <areaNum,  maxArea>
    Map<Integer, Integer> map = new HashMap<>();

    // color and count
    private int traverse(int[][] grid, int r, int c, int curr, int order) {
        if (!isValidPos(grid, r, c) || visited[r][c]) return curr;

        visited[r][c] = true;

        if (grid[r][c] == 1) {
            curr++;

            // color the area
            grid[r][c] = order;

            for (int[] move : moves) {
                curr += traverse(grid, r + move[0], c + move[1], curr, order) - curr;
            }
        }

        return curr;
    }

    private boolean isValidPos(int[][] grid, int r, int c) {
        int length = grid.length;
        if (r >= length || r < 0 || c >= grid[0].length || c < 0)
            return false;
        return true;
    }

    public int largestIsland(int[][] grid) {
        if (grid.length == 1)
            return 1;

        int len = grid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 1) {
                    targetCoordination.add(new int[]{i, j});
                }
            }
        }
        if (targetCoordination.isEmpty()) // all zeros!
            return 1;
        if (targetCoordination.size() == len * len)
            return len * len;

        visited = new boolean[len][len];

        int order = 2;
        for (int[] pair : targetCoordination) {

            int r = pair[0];
            int c = pair[1];
            int islandSize = traverse(grid, r, c, 0, order);
            if (islandSize > 0)
                map.put(order, islandSize);

            order++;
        }

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

        return maxSize;
    }

    public static void main(String[] args) {
        LargeIsland island = new LargeIsland();
        int sz = island.largestIsland(new int[][]{{0, 1}, {1, 0}});
        System.out.println(">>> large size : " + sz);
    }
}
