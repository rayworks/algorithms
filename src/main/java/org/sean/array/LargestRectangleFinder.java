package org.sean.array;

import java.util.*;

/***
 * 84. Largest Rectangle in Histogram
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleFinder {

    public int largestRectangleArea(int[] heights) {
        int i = 0;
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        while (i <= len) { // [0, len]
            int h = i == len ? 0 : heights[i];
            if (stack.isEmpty() || heights[stack.peek()] <= h) {
                stack.push(i);
            } else { // h < s[top]
                int top = stack.pop();

                int width;
                int elem = heights[top];

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, elem * width);

                i--;
            }
            i++;
        }

        return maxArea;
    }
}
