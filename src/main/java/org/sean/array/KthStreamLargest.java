package org.sean.array;

import java.util.*;

// 703. Kth Largest Element in a Stream
public class KthStreamLargest {
    private PriorityQueue<Integer> queue;
    private int kMax;

    public KthStreamLargest(int k, int[] nums) {
        this.kMax = k;

        queue = new PriorityQueue<>();

        for (int i : nums) {
            add(i);
        }
    }

    public int add(int val) {
        if (queue.size() < kMax) {
            queue.add(val);
        } else {
            if (queue.peek() < val) {
                queue.add(val);

                // remove the current K
                queue.poll();
            }
        }

        return queue.peek();
    }
}
