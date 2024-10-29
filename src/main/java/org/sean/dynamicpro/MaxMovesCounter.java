package org.sean.dynamicpro;

/***
 * 2684. Maximum Number of Moves in a Grid
 */
public class MaxMovesCounter {
    public int maxMoves(int[][] grid) {
        //              r-1, c+1
        //               /
        //       r,c -  r,   c+1
        //               \
        //              r+1, c+1
        // dfs with cached values
        int rCnt = grid.length;
        int cCnt = grid[0].length;

        // the count of concated cells
        caches = new int[rCnt][cCnt];

        int max = 0;
        for (int i = 0; i < rCnt; i++) {
            int curr = maxMovesHelper(grid, i, 0);
            if (curr > max) {
                max = curr;
            }
        }

        return max - 1;
    }

    private final int[][] moves = new int[][]{
            {-1, 1}, {0, 1}, {1, 1}
    };

    private int maxMovesHelper(int[][] grid, int r, int c) {
        int rSize = grid.length;
        int cSize = grid[0].length;

        if (caches[r][c] > 0)
            return caches[r][c];

        int curr = grid[r][c];
        int max = 1;
        for (int[] dlt : moves) {
            int nR = r + dlt[0];
            int nC = c + dlt[1];

            if (nR >= rSize || nR < 0 || nC >= cSize || nC < 0)
                continue;
            int next = grid[nR][nC];
            if (next > curr) {
                max = Math.max(max, maxMovesHelper(grid, nR, nC) + 1);
            }
        }
        caches[r][c] = max;
        return caches[r][c];
    }

    private int[][] caches;
}
