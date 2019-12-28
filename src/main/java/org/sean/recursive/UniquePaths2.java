package org.sean.recursive;

import java.util.Arrays;

/** * 63. Unique Paths II */
public class UniquePaths2 {
    private int[][] cache;

    /**
     * * Counts the unique path for an obstacle Grid
     *
     * @param m width of grid
     * @param n height of grid
     * @param obstacleGrid two-dimensional array
     * @return number of unique path from [0, 0] -> [m-1, n-1]
     */
    private int uniquePaths(int m, int n, int[][] obstacleGrid) {

        if (cache == null) {
            cache = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(cache[i], -1);
            }
        }

        if (m >= 1 && n >= 1 && cache[m - 1][n - 1] >= 0) {
            return cache[m - 1][n - 1];
        }

        if (m <= 0 || n <= 0) return 0;

        // check the start point
        if (m == 1 && n == 1) return obstacleGrid[m - 1][n - 1] == 1 ? 0 : 1;

        if (obstacleGrid[m - 1][n - 1] == 1) {
            // blocked node
            return 0;
        }

        int up = uniquePaths(m - 1, n, obstacleGrid);
        int left = uniquePaths(m, n - 1, obstacleGrid);

        if (m - 1 > 0 && n > 0) {
            cache[m - 2][n - 1] = up;
        }

        if (m > 0 && n - 1 > 0) {
            cache[m - 1][n - 2] = left;
        }
        return up + left;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;

        return uniquePaths(obstacleGrid.length, obstacleGrid[0].length, obstacleGrid);
    }
}
