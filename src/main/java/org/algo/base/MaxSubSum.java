package org.algo.base;

/***
 * 53. Maximum Subarray
 */
public class MaxSubSum {
    /***
     * O(N³)
     * Cubic maximum contiguous subsequence sum algorithm.
     */
    public int maxSubSumCubic(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }

                if (thisSum > maxSum) maxSum = thisSum;
            }
        }

        return maxSum;
    }

    /***
     * O(N²)
     * Quadratic maximum contiguous subsequence sum algorithm.
     */
    public int maxSubSumQuad(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];

                if (thisSum > maxSum) maxSum = thisSum;
            }
        }

        return maxSum;
    }

    // O(N*lgN)
    public int maxSubSumLog(int[] a) {
        return maxSubSumRec(a, 0, a.length - 1);
    }

    /***
     * O(N) Linear-time
     *
     * Dynamic Programming:
     * dp[i] : max sum subarray ending with index i
     * dp[i] = max(dp[i-1] + a[i], a[i])
     */
    public int maxSubArray(int[] a) {
        int maxValue = a[0];
        int n = a.length;
        int[] dp = new int[n + 1];
        dp[0] = a[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
            maxValue = Math.max(dp[i], maxValue);
        }
        return maxValue;
    }

    /***
     * O(N) Linear-time maximum contiguous subsequence sum algorithm.
     */
    public int maxSubSumLinear(int[] a) {
        int max = 0, maxSoFar = 0;
        for (int i = 0; i < a.length; i++) {
            maxSoFar += a[i];
            if (maxSoFar > max) max = maxSoFar;
            else if (maxSoFar < 0) maxSoFar = 0;
        }
        return max;
    }

    /***
     * Recursive maximum contiguous subsequence sum algorithm.
     * Finds maximum sum in subarray spanning a[left..right].
     * Does not attempt to maintain actual best sequence.
     */
    private int maxSubSumRec(int[] a, int l, int r) {
        if (l >= r) return Math.max(0, a[l]);

        int m = l + (r - l) / 2;
        int maxLeft = maxSubSumRec(a, l, m);
        int maxRight = maxSubSumRec(a, m + 1, r);

        int leftSideMax = 0, leftSumMax = 0;
        for (int i = m; i >= l; i--) {
            leftSumMax += a[i];
            if (leftSumMax > leftSideMax) {
                leftSideMax = leftSumMax;
            }
        }

        int rightSideMax = 0, rightSumMax = 0;
        for (int i = m + 1; i <= r; i++) {
            rightSumMax += a[i];
            if (rightSumMax > rightSideMax) {
                rightSideMax = rightSumMax;
            }
        }
        return Math.max(Math.max(maxLeft, maxRight), leftSideMax + rightSideMax);
    }

    public static void main(String[] args) {
        MaxSubSum maxSubSum = new MaxSubSum();
        System.out.println(
                "Result : " + maxSubSum.maxSubArray(new int[] {4, -3, 5, -2, -1, 2, 6, -2}));
    }
}
