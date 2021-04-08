package org.sean.array;

import java.util.ArrayDeque;
import java.util.Deque;

// 42. Trapping Rain Water
public class WaterBar {
    public int trap(int[] height) {
        if (height == null || height.length <= 1) return 0;

        int max = 0;
        int maxWaterUnit = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while (i < height.length) {
            int n = height[i];
            if (stack.isEmpty() || height[stack.peek()] >= n) {
                stack.push(i);
            } else {
                int top = stack.pop();

                // https://leetcode.com/problems/trapping-rain-water/discuss/17414/A-stack-based-solution-for-reference-inspired-by-Histogram
                // Optimization:
                // "use a stack that store the indices with decreasing bar height, once we find a bar who's height is
                // larger, then let the top of the stack be bot, the cur bar is ir, and the previous bar is il."
                maxWaterUnit = stack.isEmpty() ? 0 : (Math.min(height[stack.peek()], n) - height[top]) * (i - stack.peek() - 1);
                max += maxWaterUnit;
                i--;
            }
            i++;
        }

        return max;
    }

    public int trap0(int[] height) {
        if (height == null || height.length <= 1) return 0;

        int sum = 0, i = 0;
        int len = height.length;

        // Stack<Integer> stack = new Stack<>();
        // Deque is preferred.
        Deque<Integer> stack = new ArrayDeque<Integer>();

        int inStackMax = 0;
        int inMaxIndex = 0;
        while (height[i] <= 0) i++;


        while (i < len) {
            int h = height[i];
            if (stack.isEmpty() || height[stack.peek()] >= h) {
                if (h > inStackMax) {
                    inStackMax = h;
                    inMaxIndex = i;
                }

                stack.push(i);
                //System.out.println(String.format(">>> Pushed pos: %d, val : %d", i, height[i]));

            } else { // h > s[top]
                int poppedCnt = 0;
                int completionIndex = i;
                int boardValue;
                while (!stack.isEmpty() && height[stack.peek()] < h) {
                    if (stack.peek() == inMaxIndex) break;

                    poppedCnt++;
                    int top = stack.pop();
                    int elem = height[top];

                    boardValue = h;
                    if (inStackMax < h) {
                        completionIndex = inMaxIndex;
                        boardValue = inStackMax;
                    }

                    // Math.min(inStackMax, h)
                    int trappedBlocks = boardValue - elem; // within the boarder
                    sum += trappedBlocks;
                }


                // rePush the completed sections
                while (poppedCnt > 0) {
                    stack.push(completionIndex);
                    poppedCnt--;
                }


                // rePush current section
                stack.push(i);
                if (h > inStackMax) {
                    inStackMax = h;
                    inMaxIndex = i;
                }
            }
            i++;
        }
        return sum;
    }
}
