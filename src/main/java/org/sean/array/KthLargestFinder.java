package org.sean.array;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/***
 * 215. Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 */
public class KthLargestFinder {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k+1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int e : nums) {
            queue.add(e);
        }


        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
        }

        int i = 0;
        while (!queue.isEmpty()) {
            ++i;
            Integer integer = queue.remove();
            if(i == k) {
                return integer;
            }

        }

        // as a fallback
        return  -1;
    }
}
