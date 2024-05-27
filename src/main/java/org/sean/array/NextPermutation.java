package org.sean.array;

/***
 * 31. Next Permutation
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // [] 4 3 2 1
        //    1 [2] 3
        //    xxx [4] 7 5 3

        int len = nums.length;
        int i;
        for (i = len - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                break;
            }
        }
        if (i == 0) { // already sorted in DESC
            reverse(nums, 0, len - 1);
            return;
        }

        i--; // the one to be swapped
        for (int j = len - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                swap(nums, i, j);
                break;
            }
        }

        // reorder the [i+1, len) part as they're already sorted in reverse order
        reverse(nums, i + 1, len - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
