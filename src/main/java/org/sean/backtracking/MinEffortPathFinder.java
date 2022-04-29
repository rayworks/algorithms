package org.sean.backtracking;

import java.util.Arrays;
import java.util.PriorityQueue;

/****
 * 1631. Path With Minimum Effort
 */
public class MinEffortPathFinder {
    private int minEffort = Integer.MAX_VALUE;

    int[][] moves = new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    boolean[][] visited;


    // region : PriorityQueue
    static class Cell implements Comparable<Cell> {
        int row;
        int col;
        int diff;

        public Cell(int row, int col, int diff) {
            this.row = row;
            this.col = col;
            this.diff = diff;
        }

        @Override
        public int compareTo(Cell o) {
            return Integer.compare(diff, o.diff);
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int rowCnt = heights.length;
        int colCnt = heights[0].length;

        visited = new boolean[rowCnt][colCnt];
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        pq.add(new Cell(0, 0, 0));
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();

            int row = cell.row;
            int col = cell.col;
            visited[row][col] = true;

            int diff = cell.diff;
            if (row == rowCnt - 1 && col == colCnt - 1 ) {
                minEffort = Math.min(minEffort, diff);
            }

            for (int[] mv : moves) {
                int nR = mv[0] + row;
                int nC = mv[1] + col;

                if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt && !visited[nR][nC]) {
                    int abs = Math.abs(heights[row][col] - heights[nR][nC]);
                    pq.add(new Cell(nR, nC, Math.max(abs, diff)));
                }
            }
        }
        return minEffort;
    }
    // endregion

    int[][] dp;

    int traverse(int[][] grid, int r, int c, int effort) {
        if (dp[r][c] != Integer.MAX_VALUE) {
            if (effort >= dp[r][c]) {
                // >= prev visited value
                return dp[r][c];
            } else {
                return Math.min(dp[r][c], effort);
            }
        }

        int rowCnt = grid.length;
        int colCnt = grid[0].length;

        if (r == (rowCnt - 1) && c == (colCnt - 1)) {
            minEffort = Math.min(effort, minEffort);
            return effort;
        }

        int reusult = Integer.MAX_VALUE;
        visited[r][c] = true;

        for (int[] mv : moves) {
            int nR = mv[0] + r;
            int nC = mv[1] + c;
            if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt) {
                if (!visited[nR][nC]) {
                    int diff = Math.abs(grid[nR][nC] - grid[r][c]);
                    int prev = effort;

                    effort = Math.max(effort, diff);
                    reusult = Math.min(reusult, traverse(grid, nR, nC, effort));
                    dp[nR][nC] = reusult;

                    effort = prev;
                }
            }
        }

        visited[r][c] = false;

        return reusult;
    }

    public int minimumEffortPath0(int[][] heights) {
        int rowCnt = heights.length;
        int colCnt = heights[0].length;

        visited = new boolean[rowCnt][colCnt];
        dp = new int[rowCnt][colCnt]; // (0,0) -> (i,j) 的minEffort
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        traverse(heights, 0, 0, 0);

        System.out.println("Min : " + minEffort);
        System.out.println("DP : ");
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return minEffort;
    }
}
