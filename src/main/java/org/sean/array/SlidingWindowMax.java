package org.sean.array;

import java.util.LinkedList;

// 239. Sliding Window Maximum
public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        if (k == 1) {
            return nums;
        }

        LinkedList<Integer> deque = new LinkedList<>();
        int len = nums.length;

        // replace List with raw Array
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len ; i++) {
            int elem = nums[i];
            // delete the left-out element
            if(i >= k && deque.peek() < i - k + 1) {
                deque.pop();
            }

            // remove the left smaller elements
            while (!deque.isEmpty() && nums[deque.peekLast()] <= elem) {
                deque.removeLast();
            }

            deque.add(i); // index

            if(i >= k - 1 && !deque.isEmpty()) {
                res[i - (k - 1)] = nums[deque.peek()];
            }
        }

        return res;
    }
}
