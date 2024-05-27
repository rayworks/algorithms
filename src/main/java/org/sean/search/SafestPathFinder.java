package org.sean.search;

import java.util.*;

/***
 * 2812. Find the Safest Path in a Grid
 */
public class SafestPathFinder {
    private int[][] moves = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        // bfs : calc the min dist to other non-thief cells
        // max(mins) : binSearch [min, max] the range

        int n = grid.size();
        if (n == 1) return 0;

        // multi-source BFS
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] pair = queue.poll();
                for (int[] diff : moves) {
                    int nR = diff[0] + pair[0];
                    int nC = diff[1] + pair[1];
                    if (nR >= 0 && nC >= 0 && nR < n && nC < n && grid.get(nR).get(nC) == 0) {
                        // cell traversal guarded by the non-zero value
                        grid.get(nR).set(nC, step + 1);

                        queue.offer(new int[]{nR, nC});
                    }
                }
            }
            step++;
        }

        int left = 0, right = 2 * n;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (foundValidPath(grid, mid))
                left = mid;
            else
                right = mid - 1;
        }

        return left;
    }

    private boolean foundValidPath(List<List<Integer>> grid, int threshold) {
        if (grid.get(0).get(0) <= threshold)
            return false;

        int n = grid.size();
        boolean[][] visited = new boolean[n][n];

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] pair = queue.poll();
                int row = pair[0];
                int col = pair[1];
                visited[row][col] = true;

                if (row == n - 1 && col == n - 1) {
                    return true;
                }

                for (int[] diff : moves) {
                    int nR = diff[0] + row;
                    int nC = diff[1] + col;
                    if (nR >= 0 && nC >= 0 && nR < n && nC < n
                            && grid.get(nR).get(nC) > threshold
                            && !visited[nR][nC]) {

                        visited[nR][nC] = true;
                        queue.offer(new int[]{nR, nC});
                    }
                }
            }
        }
        return false;
    }
}
