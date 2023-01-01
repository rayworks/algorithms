package org.sean.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/***
 * 739. Daily Temperatures
 */
public class DailyTemp {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        if (len == 0)
            return new int[]{0};

        int[] res = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while (i < len) {
            int elem = temperatures[i];
            if (stack.isEmpty() || temperatures[stack.peek()] >= elem) {
                stack.push(i);
            } else {  // < elem
                while (!stack.isEmpty()) {
                    int index = stack.peek();
                    if(temperatures[index] < elem) {
                        res[index] = i - index;
                        stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(i);
            }
            i++;
        }
//        System.out.println(Arrays.toString(res));
        return res;
    }
}
