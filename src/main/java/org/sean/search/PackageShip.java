package org.sean.search;

import java.util.Arrays;

/***
 * 1011. Capacity To Ship Packages Within D Days
 */
public class PackageShip {

    // Binary search : Time O(N*logN)
    // see also : https://www.topcoder.com/thrive/articles/Binary%20Search
    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int low = Arrays.stream(weights).max().getAsInt();
        int high = Arrays.stream(weights).sum();

        while (low < high) {
            int x = low + (high - low) / 2;

            int daysRequired = 1, curr = 0;
            for (int i = 0; i < n; i++) {
                if (curr + weights[i] <= x) {
                    // can be shipped
                    curr += weights[i];
                } else {
                    // a new day
                    ++daysRequired;
                    curr = weights[i];
                }
            }
            if (daysRequired <= days)
                high = x;
            else
                low = x + 1;
        }

        return low;
    }

    // DP : Time O(k*N^2) TLE
    public int shipWithinDays0(int[] weights, int days) {
        int n = weights.length;

        int[] s = new int[n + 1];
        System.arraycopy(weights, 0, s, 1, n);

        int[][] dp = new int[n + 1][days + 1]; // N packages | in K days
        int[] sumCaches = new int[n + 1];

        int cost;
        sumCaches[0] = 0;
        for (int i = 1; i <= n; i++) {
            sumCaches[i] = sumCaches[i - 1] + s[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = sumCaches[i];
        }
        for (int j = 1; j <= days; j++) {
            dp[1][j] = s[1];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= days; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int x = 1; x <= (i - 1); x++) {
                    cost = Math.max(dp[x][j-1], sumCaches[i] - sumCaches[x]);
                    if (dp[i][j] > cost) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        int max = 0;
        for (int[] grp : dp) {
            max = Math.max(max, grp[days]);
            // System.out.println(Arrays.toString(grp));
        }

        return max;
    }
}
