package org.sean.recursive;

import java.util.Arrays;

/***
 * 62. Unique Paths
 */
public class UniquePaths {
    private int[][] cache;
    public int uniquePaths(int m, int n) {

        if(cache == null) {
            cache = new int[m][n];
            for(int i = 0; i < m; i++) {
                Arrays.fill(cache[i], -1);
            }
        }

        if(m >= 1 && n >= 1 && cache[m -1][n - 1] >= 0) {
            return cache[m -1][n - 1];
        }

        if(m <= 0 || n <= 0)
            return 0;

        if(m == 1 && n == 1)
            return 1;

        int up = uniquePaths(m -1, n);
        int left = uniquePaths(m, n - 1);

        if(m - 1 > 0 && n > 0) {
            cache[m - 2][n - 1] = up;
        }

        if(m > 0 && n - 1 > 0) {
            cache[m - 1][n - 2] = left;
        }
        return  up + left;
    }
}
