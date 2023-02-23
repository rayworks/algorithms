package org.sean.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 502. IPO
 */
public class IPO {

    // Time O(k*logN)
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // sort the cap/prof
        // add the elems.cap <= w

        // take the max out, update w and k--
        // add more to the queue
        int len = profits.length;
        int[][] rio = new int[len][2];
        for (int i = 0; i < len; i++) {
            rio[i][0] = capital[i];
            rio[i][1] = profits[i];
        }

        Arrays.sort(rio, Comparator.comparingInt(o -> o[0]));

        // MAX Heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        int addedPos = 0;
        for (int i = 0; i < k; i++) {
            while (addedPos < len && w >= rio[addedPos][0]) {
                pq.add(rio[addedPos]);
                addedPos++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll()[1];
            } else {
                break;
            }
        }
        return w;
    }
}
