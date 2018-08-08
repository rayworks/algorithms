package org.sean.array;

import java.util.Arrays;

/***
 * Leetcode : 27. Remove Element
 */
public class ElementRemoval {
    private int[] nums;

    public ElementRemoval(int[] nums) {
        this.nums = nums;
    }

    /***
     * Removes the values which equal to <code>val</code> from the given array
     * @param val
     * @return the size of remaining elements
     *
     *
     * Given nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * should return length = 5
     * [0, 1, 3, 0, 4]
     * those five elements can be arbitrary.
     */
    public int removeElement(int val) {
        int cnt = 0;
        int length = nums.length;

        int beg = 0;
        int end = length - 1;

        while (beg < end) {
            while (nums[beg] != val && beg < length - 1)
                ++beg;
            while (nums[end] == val && end > 0)
                --end;

            if (beg < end) {
                swap(beg, end);
            }
        }

        int index = 0;
        while (index < length) {
            if (nums[index] != val) {
                ++cnt;
            } else {
                break;
            }

            ++index;
        }

        print();

        return cnt;
    }

    private void print() {
        System.out.println(Arrays.toString(nums));
    }

    private void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
