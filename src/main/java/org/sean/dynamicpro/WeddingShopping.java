package org.sean.dynamicpro;

import java.util.Arrays;

// UVa 11450 - Wedding Shopping
public class WeddingShopping {

    //
    // 1 <= M <= 200 and 1 <= C <= 20,
    // where M is the budget and C is the number of garments that you have to buy,
    // followed by some information about the C garments. For the garment g in [0..C-1].

    // The output is one integer that indicates the maximum amount of money we can spend purchasing one of each garment
    // without exceeding the budget.
    //
    int maxPurchaseAmount(int M, int C, int[][] prices) {
        // prices not sorted
        // max to buy C items <= M
        memo = new int[C + 1][M + 1];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        dp(0, M, M, C, prices);

        return memo[0][M];
    }

    private int[][] memo;

    private int dp(int num, int amount, int M, int C, int[][] prices) { // num / budget
        if (amount < 0) return -1;
        if (num == C) return M - amount;

        if (memo[num][amount] >= 0) return memo[num][amount];

        int res = (int) -1e3;
        for (int i = 0; i < prices[num].length; i++) {
            int cost = prices[num][i];
            res = Math.max(res, dp(num + 1, amount - cost, M, C, prices));
        }
        return memo[num][amount] = res;
    }

    public static void main(String[] args) {
        WeddingShopping shopping = new WeddingShopping();
        int res = shopping.maxPurchaseAmount(20, 3, new int[][]{
                {6, 4, 8},
                {6, 10},
                {1, 5, 3, 5},
        });
        System.out.println("Result : " + res);
    }
}
