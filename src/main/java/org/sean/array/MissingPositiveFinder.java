package org.sean.array;

import java.util.HashSet;
import java.util.Set;

/***
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/
 */
public class MissingPositiveFinder {
    public int firstMissingPositive(int[] nums) { // O(N) time | O(1) space
        if (nums == null || nums.length == 0) return 1;

        boolean found = false;
        for (int e : nums) {
            if (e == 1) {
                found = true;
                break;
            }
        }
        if (!found) {
            return 1;
        }

        // 1. separate the negatives and zeroes
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < nums.length && nums[i] > 0) i++;
            while (j >= 0 && nums[j] <= 0) j--;

            if (i >= nums.length || j < 0 || i >= j) break;

            // swap it
            swap(nums, i, j);

            i++;
        }

        int lastPos = -1;
        for (int n = 0; n < nums.length; n++) {
            if (nums[n] <= 0) {
                lastPos = n - 1;
                break;
            }
        }
        if (lastPos == -1) lastPos = nums.length - 1;

        // 2. mark all the present orders
        for (int k = 0; k <= lastPos; k++) {
            int index = Math.abs(nums[k]);
            if (index > 0 && index <= nums.length && nums[index - 1] > 0) { // nums[k] > 0
                nums[index - 1] = -nums[index - 1];
            }
        }
        for (int m = 0; m <= lastPos; m++) {
            if (nums[m] > 0) {
                return m + 1;
            }
        }

        // all located : return size + 1 [lastPos + 1] + 1
        return lastPos + 2;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int firstMissingPositive0(int[] nums) { // O(N) time | O(N) space
        if (nums == null || nums.length == 0) return 1;

        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (i > 0) {
                set.add(i);
            }
        }

        for (int k = 1; k <= nums.length + 1; k++) {
            if (!set.contains(k)) {
                return k;
            }
        }

        return 1;
    }
}
