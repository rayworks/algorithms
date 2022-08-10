package org.sean.dynamicpro;

/***
 * 377. Combination Sum IV
 */
public class CombinationSum4 {
    // region DP : O(N*M)
    public int combinationSum4(int[] nums, int target) {
        // dp[i] : number of combinations for target i
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int e : nums) {
            if (e <= target) {
                dp[e] = 1;
            }
        }
        int len = nums.length;
        for (int k = 1; k <= target; k++) {
            for (int i = 0; i < len; i++) {
                if (k > nums[i])
                    dp[k] += dp[k - nums[i]];
            }
        }

        return dp[target];
    }
    // endregion

    // region DFS : TLE O(N^M)
    private int count;

    private void combinationSum4Helper(int[] nums, int target) {
        if (target == 0)  {
            count++;
            return ;
        }
        if (target < 0)
            return;

        for (int i = 0; i < nums.length; i++) {
            combinationSum4Helper(nums, target- nums[i]);
        }

    }

    public int combinationSum40(int[] nums, int target) {
        combinationSum4Helper(nums, target);
        return count;
    }
    // endregion


}
