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
}
