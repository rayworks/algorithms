package org.sean.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * 442. Find All Duplicates in an Array
 */
public class DuplicatesFinder {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length <= 1)
            return Collections.emptyList();

        int length = nums.length;

        int mask = ~(1 << 31);
        List<Integer> result = new LinkedList<>();

        for (int i = 0; i < length; i++) {
            int pos = (nums[i] & mask) - 1;

            int flag = (nums[pos] >> 31) & 0x01;
            if (flag == 1) { // already marked
                result.add(nums[i] & mask);
            } else {
                nums[pos] = (1 << 31) | nums[pos];// mark the value
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates = new DuplicatesFinder().findDuplicates(array);
        System.out.println(Arrays.toString(duplicates.toArray()));
    }
}
