package org.sean.dynamicpro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 823. Binary Trees With Factors
 */
public class FactoredBinaryTree {
    private int multiple(int x, int y, int mod) {
        if (y == 1) {
            return x % mod;
        }

        if (y % 2 == 0)
            return (2 * (multiple(x, y / 2, mod) % mod)) % mod;
        else {
            return (x + (multiple(x, y - 1, mod))) % mod;
        }
    }

    public int numFactoredBinaryTrees(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        Arrays.sort(arr);

        // tree numbers for pos i
        int[] dp = new int[arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            map.put(arr[i], i);
        }

        int mod = (int) (1e9 + 7);
        for (int j = 1; j < len; j++) {
            for (int k = 0; k < j; k++) {
                if (arr[j] % arr[k] == 0) {
                    int div = arr[j] / arr[k];
                    boolean posVal = map.containsKey(div);
                    if (posVal) {
                        int divPos = map.get(div);
                        dp[j] = (dp[j] + multiple(dp[divPos], dp[k], mod)) % mod;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum = (sum + dp[i]) % mod;
        }
        return sum;
    }
}
