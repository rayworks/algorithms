package org.sean.backtracking;

/***
 * 494. Target Sum
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;

        find(nums, 0, 0, S);
        return counter;
    }

    int counter = 0;

    void find(int[] sums, int i, int sum, int target) {
        int len = sums.length;
        if (i == len) {
            if (sum == target) ++counter;
            return;
        }
        int elem = sums[i];

        find(sums, i + 1, sum + elem, target);
        find(sums, i + 1, sum - elem, target);
    }
}
