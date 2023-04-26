package org.sean.array;

import java.util.ArrayList;
import java.util.List;

/***
 * 2653. Sliding Subarray Beauty
 */
public class SlidingSubarrayBeauty {
    private final int offset = 50; // -50 <= nums[i] <= 50, 1 <= n <= 10^5

    public int[] getSubarrayBeauty(int[] nums, int k, int x) { // the x-th smallest integer
        int len = nums.length;
        List<Integer> out = new ArrayList<>();

        int[] numCnt = new int[101];


        int i = 0;
        int j = i + k - 1;

        for (int m = i; m <= j; m++) {
            int elem = nums[m];
            numCnt[elem + offset] += 1; // normalize and count the numbers
        }
        out.add(getMin(x, numCnt));

        while (j < len - 1) {
            numCnt[nums[i] + offset] -= 1;
            numCnt[nums[j + 1] + offset] += 1;
            out.add(getMin(x, numCnt));

            i++;
            j++;
        }

        return out.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getMin(int x, int[] numCnt) {
        int sum = 0;
        for (int i = 0; i < numCnt.length; i++) {
            if (numCnt[i] > 0) {
                sum += numCnt[i];
                if (sum >= x) { // traverse to look up the x-th smallest int
                    int raw = i - offset;
                    return Math.min(raw, 0);
                }
            }
        }
        return 0;
    }
}
