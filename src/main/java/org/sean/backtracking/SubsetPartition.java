package org.sean.backtracking;

import java.util.Arrays;

/***
 * 698. Partition to K Equal Sum Subsets
 */
public class SubsetPartition {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) return true;

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;

        int avg = sum / k;

        Arrays.sort(nums);

        int endIndex = nums.length - 1;
        if (nums[endIndex] > avg)
            return false;

        // reduce the elements equal to avg
        while (endIndex >= 0 && nums[endIndex] == avg) {
            endIndex--;
            k--;
        }

        return dfs(new int[k], nums, endIndex, avg);
    }

    private boolean dfs(int[] subSets, int[] nums, int index, int target) {
        if (index < 0)
            return true;

        for (int i = 0; i < subSets.length; i++) {
            // try to put elem into one of the subSets recursively
            if (subSets[i] + nums[index] <= target) {
                subSets[i] += nums[index];
                if (dfs(subSets, nums, index - 1, target))
                    return true;
                subSets[i] -= nums[index];
            }
        }

        return false;
    }
}
