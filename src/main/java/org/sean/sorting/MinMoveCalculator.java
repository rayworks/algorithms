package org.sean.sorting;

import java.util.Random;

/***
 * 462. Minimum Moves to Equal Array Elements II
 */
public class MinMoveCalculator {
    public int minMoves2(int[] nums) {
        if (nums.length <= 1)
            return 0;

        // Find the median of the array
        // * Sorting the array could be one of the solutions, but its time complexity goes to O(n*lgn)
        // * By partition : O(n)
        int median = partition(nums, 0, nums.length - 1, (nums.length + 1) / 2);

        int mv = 0;
        for (int e : nums) {
            mv += Math.abs(e - median);
        }
        return mv;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int partition(int[] nums, int l, int r, int targetPos) {
        // randomizing the pivot is important
        final int randIndex = new Random().nextInt(r - l + 1) + l;
        swap(nums, randIndex, r);
        final int pivot = nums[r];


        // int pivot = nums[r];
        int swapIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, swapIndex++);
            }
        }
        swap(nums, swapIndex, r);

        int cnt = swapIndex - l + 1;
        if (cnt == targetPos)
            return nums[swapIndex];
        else if (cnt > targetPos) {
            return partition(nums, l, swapIndex - 1, targetPos);
        } else {
            return partition(nums, swapIndex + 1, r, targetPos - cnt);
        }
    }
}
