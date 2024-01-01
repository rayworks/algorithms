package org.sean.search;

import java.util.*;

/***
 * 2616. Minimize the Maximum Difference of Pairs
 */
public class MinPairsDiff {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int len = nums.length;

        int left = nums[0];
        int right = nums[len - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (pairFound(nums, p, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean pairFound(int[] nums, int p, int diff) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1) {
                if (nums[i + 1] - nums[i] <= diff) {
                    count++;

                    i++; // for next pair
                }
            }
        }
        return count >= p;
    }
}
