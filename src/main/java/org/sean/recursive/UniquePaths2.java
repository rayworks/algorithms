package org.sean.recursive;

import java.util.Arrays;

/**
 * 63. Unique Paths II
 */
public class UniquePaths2 {
    private int[][] cache;

    /**
     * * Counts the unique path for an obstacle Grid
     *
     * @param m            width of grid
     * @param n            height of grid
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

    /// solution II
    private int[][] dp;

    public int uniquePaths2(int m, int n, int[][] obstacleGrid) {
        int row = m;
        int col = n;

        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1)
            return 0;

        dp = new int[m + 1][n + 1];
        dp[1][1] = 1;

        for (int j = 1; j <= row; j++) {
            for (int i = 1; i <= col; i++) {
                if (obstacleGrid[j - 1][i - 1] == 1) {
                    dp[j][i] = 0;
                    continue;
                }

                if (j == 1 && i == 1)
                    continue;

                if (j == 1 || i == 1) {
                    if (j == 1) {
                        dp[j][i] = dp[j][i - 1] != 0 ? 1 : 0;
                    } else {
                        dp[j][i] = dp[j - 1][i] != 0 ? 1 : 0;
                    }
                } else {
                    dp[j][i] = getCnt(j, i - 1, obstacleGrid) + getCnt(j - 1, i, obstacleGrid);
                }
            }
        }
        return dp[m][n];
    }

    private int getCnt(int r, int c, int[][] array) {
        return array[r - 1][c - 1] == 1 ? 0 : dp[r][c];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;

        return uniquePaths(obstacleGrid.length, obstacleGrid[0].length, obstacleGrid);
    }
}
