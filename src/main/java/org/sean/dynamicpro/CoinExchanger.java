package org.sean.dynamicpro;

// 322. Coin Change
public class CoinExchanger {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;

        if (amount == 0) return 0;

        int row = coins.length;
        int column = amount + 1;

        // min cnt
        int[] dp = new int[column]; // dp[0] = 0

        for (int c = 1; c <= amount; c++) { // 1 .. N

            int minVal = Integer.MAX_VALUE;

            int gridVal = 0;
            for (int r = 0; r < row; r++) {
                int coin = coins[r];

                if (c < coin) {
                    gridVal = 0;
                } else if (c == coin) {
                    gridVal = 1;
                } else {
                    if (dp[c - coin] != 0) {
                        gridVal = dp[c - coin] + 1;
                    }
                }

                if (gridVal != 0) {
                    if (minVal > gridVal) {
                        minVal = gridVal;
                    }
                }
            }

            if (minVal != Integer.MAX_VALUE) {
                dp[c] = minVal;
            }
        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }

    //
    // Infinity coins
    //
    public boolean exchangeable(int[] coins, int n) {
        boolean[] dp = new boolean[n + 1];
        for (int c : coins) {
            if (c <= n) {
                dp[c] = true;
                if (c == n) return true;
            }
        }
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (coin < i && !dp[i])
                    dp[i] = dp[i - coin];
                // else continue
            }
        }

        return dp[n];
    }

    // 0-1 Knapsack
    public boolean exchangeableWithLimit(int[] coins, int n) {
        int len = coins.length;
        boolean[][] dp = new boolean[n + 1][len + 1]; //  1...j coins to exchange i  -> DP[W][C]
        for (int j = 0; j <= len; j++) {
            dp[0][j] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= len; j++) {
                int coin = coins[j - 1];
                if (coin <= i) {
                    dp[i][j] = dp[i][j - 1] || dp[i - coin][j - 1]; // picked up or not
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][len];
    }
}
