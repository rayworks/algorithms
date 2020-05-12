package org.sean.array;

// 540. Single Element in a Sorted Array
public class SingleElemFinder {  // O(lgN) time, O(1) space
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];

        int cnt = nums.length;
        int start = 0;
        int end = cnt - 1;

        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            int midVal = nums[mid];

            if (mid > 0 && mid < end) {
                if (midVal != nums[mid - 1] && midVal != nums[mid + 1]) return midVal;
            }

            // Given that the target range length should be an odd number, we
            // can reduce the range accordingly:
            // e.g:
            // 3 3 {7 7 10}
            // {3 3 5} 7 7 10 10
            if (mid > 0) {
                if (nums[mid - 1] == midVal) {
                    if ((mid - start + 1) % 2 == 0) start = mid + 1;
                    else end = mid;
                } else {
                    if (mid < end) {
                        if (nums[mid + 1] == midVal) {
                            if ((end - mid + 1) % 2 == 0) end = mid - 1;
                            else start = mid;
                        }
                    } else {
                        return nums[end];
                    }
                }
            } else {
                return midVal;
            }

            if (start + 1 <= end && nums[start] != nums[start + 1]) {
                return nums[start];
            }
            if (end - 1 >= 0 && nums[end] != nums[end - 1]) return nums[end];
        }

        return nums[0]; // should never hit here
    }
}
