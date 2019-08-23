package org.sean.array;

import java.util.*;

/***
 * 565. Array Nesting
 *
 * N is an integer within the range [1, 20,000].
 * The elements of A are all distinct.
 * Each element of A is an integer within the range [0, N-1].
 */
public class NestingArray {
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int length = nums.length;
        if (length == 1) {
            return 1;
        }

        int max = 0;
        Set<Integer> visitedPositions = new HashSet<>();

        for (int i = 0; i < length; i++) {
            if (!visitedPositions.contains(i)) {
                int elem = nums[i];
                int cnt = 0;
                while (!visitedPositions.contains(elem)) {
                    ++cnt;

                    visitedPositions.add(elem);
                    elem = nums[elem];
                }

                max = Math.max(max, cnt);
            }
        }

        return max;
    }
}
