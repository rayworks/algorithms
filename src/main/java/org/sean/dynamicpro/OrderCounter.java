package org.sean.dynamicpro;

/***
 * 1359. Count All Valid Pickup and Delivery Options
 */
public class OrderCounter {
    public int countOrders(int n) {
        int mod = (int) (1e9+7);
        long[] dp = new long[n+1];
        dp[1] = 1;

        // P1D1
        // xP1D1, P1xD1, P1D1x
        // ...
        for (int i = 2; i <= n; i++) {
            int prevPairCnt = (i-1) * 2;

            int interval = prevPairCnt + 1; // prev intervals
            int combCnt = (1 + interval) * interval / 2;
            dp[i] = (combCnt * dp[i-1]) % mod;
        }

        return (int) dp[n];
    }
}
