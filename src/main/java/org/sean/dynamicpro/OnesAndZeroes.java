package org.sean.dynamicpro;

/***
 * 474. Ones and Zeroes
 */
public class OnesAndZeroes {
    // region DP
    // dp[i][j] : max num of string in 'strs' with limitation of i '0's and j '1's.
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int zeroCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    zeroCnt++;
                }
            }
            int oneCnt = s.length() - zeroCnt;

            for (int i = m; i >= zeroCnt; i--) {
                for (int j = n; j >= oneCnt; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroCnt][j - oneCnt] + 1);
                }
            }
        }
        return dp[m][n];
    }
    // endregion

    // region DFS
    private int find(String[] strs, int pos, int m, int n) {
        if (m < 0 || n < 0)
            return -1;

        if (pos >= strs.length)
            return 0;

        if (cache[pos][m][n] > 0) {
            return cache[pos][m][n];
        }

        String s = strs[pos];
        int zeroCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCnt++;
            }
        }

        // select current 0-1 String or not
        int max = Math.max(
                1 + find(strs, pos + 1, m - zeroCnt, n - (s.length() - zeroCnt)),
                find(strs, pos + 1, m, n)
        );

        cache[pos][m][n] = max;

        return max;
    }

    int[][][] cache;

    public int findMaxForm0(String[] strs, int m, int n) {
        cache = new int[strs.length][m + 1][n + 1];
        return find(strs, 0, m, n);
    }
    // endregion
}
