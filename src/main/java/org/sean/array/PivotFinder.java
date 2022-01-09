package org.sean.array;

/***
 * 724. Find Pivot Index
 */
public class PivotFinder {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        if (nums.length == 1) return 0;

        int length = nums.length;

        // Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
        }
        // check the left boundary
        if (sum - nums[0] == 0) return 0;

        int currentSum = nums[0];
        for (int i = 1; i < length; i++) {
            if (currentSum == sum - nums[i] - currentSum) {
                return i;
            }
            currentSum += nums[i];
        }

        // check the right boundary
        if (sum - nums[length - 1] == 0) return length - 1;

        return -1;
    }
}
