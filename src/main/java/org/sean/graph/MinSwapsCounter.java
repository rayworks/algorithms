package org.sean.graph;

import java.util.Arrays;

/***
 * 3551. Minimum Swaps to Sort by Digit Sum
 */
public class MinSwapsCounter {
    public int minSwaps(int[] nums) {
        int len = nums.length;

        // <val, sum, pos>
        int[][] elements = new int[len][3];
        for (int i = 0; i < len; i++) {
            elements[i][0] = nums[i];

            int elem = nums[i];
            int sum = 0;
            while (elem > 0) {
                sum += elem % 10;
                elem /= 10;
            }
            elements[i][1] = sum;
            elements[i][2] = i;
        }

        int[] actualIndexes = new int[len];
        Arrays.sort(elements, (o1, o2) -> {
            if (o1[1] == o2[1]) { // sort by the min value
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        for (int i = 0; i < len; i++) {
            actualIndexes[i] = elements[i][2];
        }

        // System.out.println(Arrays.toString(actualIndexes));

        // Simplify the problem as sorting the array actualIndexes by swapping 2 elements
        // n - numOfCircles
        boolean[] visited = new boolean[len];
        int res = len;
        for (int i = 0; i < len; i++) {
            if (visited[i]) continue;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = actualIndexes[j];
            }
            res--;
        }
        return res;
    }
}
