package org.sean.array;

/***
 * 3698. Split Array With Minimum Difference
 */
public class SubArrayMinDiffer {

    public long splitArray(int[] nums) {
        // two pointers
        // left ->     <- right
        //     |
        //   | |    |
        // | | | ...| |
        //
        int n = nums.length;
        int left = 0, right = n - 1;
        long sumLeft = 0, sumRight = 0;

        sumLeft += nums[left];
        sumRight += nums[right];

        long res = Long.MAX_VALUE;
        while (left + 1 < right) {
            if (nums[left + 1] > nums[left]) {
                sumLeft += nums[left + 1];
                left++;
            } else {
                break;
            }
        }

        // move the right ptr
        while (right - 1 > left) {
            if (nums[right - 1] > nums[right]) {
                sumRight += nums[right - 1];
                right--;
            } else {
                break;
            }
        }

        //System.out.printf(">>> left: %d, right: %d\n", left, right);
        if (left + 1 == right) {
            res = Math.min(Math.abs(sumLeft - sumRight), res);
        } else {
            return -1;
        }

        while (left > 0) {
            if (nums[left] > nums[right]) {
                sumRight += nums[left];
                sumLeft -= nums[left];

                res = Math.min(Math.abs(sumLeft - sumRight), res);
                left--;
                right--;
            } else {
                break;
            }
        }

        return res;
    }
}
