package org.sean.dynamicpro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 279. Perfect Squares
 */
public class PerfectSquares {

    public int numSquares(int n) {
        int sqr = (int) Math.sqrt(n);
        if (sqr * sqr == n) {
            return 1;
        }

        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= sqr; i++) {
            squares.add(i * i);
        }

        int[] dp = new int[n + 1]; // min elements summed to dp[i]
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int elem : squares) {
                if (elem == i) {
                    dp[i] = 1;
                } else if (elem < i) {
                    dp[i] = Math.min(dp[i], dp[i - elem] + 1);
                } else {
                    break;
                }
            }
        }

        return dp[n];
    }
}
