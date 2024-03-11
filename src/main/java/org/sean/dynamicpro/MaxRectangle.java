package org.sean.dynamicpro;

import java.util.Stack;

/***
 * 85. Maximal Rectangle
 */
public class MaxRectangle {
    private int[][] verticalSums;
    private int maxArea = 0;

    private void findMaxArea(int[] row) {
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < row.length; i++) {
            int len = row[i];
            if (stack.isEmpty() || stack.peek()[1] < len) {
                stack.push(new int[]{i, len});
            } else {
                // monotonic stack
                int target = i;
                while (!stack.isEmpty() && stack.peek()[1] >= len) {
                    int[] arr = stack.pop();
                    maxArea = Math.max(maxArea, arr[1] * (i - arr[0]));

                    // update the index for the new added item
                    target = arr[0];
                }
                stack.push(new int[]{target, len});
            }
        }
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // extra row + column added for marking it as the end, all historical record will be evaluated.
        verticalSums = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '0') {
                    verticalSums[i][j] = 0;
                } else {
                    verticalSums[i][j] = i > 0 ? verticalSums[i - 1][j] + 1 : 1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            findMaxArea(verticalSums[i]);
        }

        return maxArea;
    }
}
