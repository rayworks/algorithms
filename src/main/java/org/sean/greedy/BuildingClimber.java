package org.sean.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/***
 * 1642. Furthest Building You Can Reach
 */
public class BuildingClimber {

    // region Brute force (backtracking) : O(2^N)
    public int furthestBuilding0(int[] heights, int bricks, int ladders) {
        climbBuilding(heights, bricks, ladders, 0);
        return maxIndex;
    }

    private int maxIndex = 0;

    private void climbBuilding(int[] heights, int bricks, int ladders, int index) {
        if (index == heights.length) {
            return;
        }

        maxIndex = Math.max(index, maxIndex);

        if (index + 1 < heights.length) {
            if (heights[index] < heights[index + 1]) {
                int diff = heights[index + 1] - heights[index];
                //using a ladder
                if (ladders > 0) {
                    // try with 2 possible ways
                    climbBuilding(heights, bricks, ladders - 1, index + 1);

                    if (bricks >= diff) {
                        climbBuilding(heights, bricks - diff, ladders, index + 1);
                    }
                } else if (bricks >= diff) {
                    climbBuilding(heights, bricks - diff, ladders, index + 1);
                }
            } else {
                climbBuilding(heights, bricks, ladders, index + 1);
            }
        }
    }
    // endregion

    // region Greedy : O(N*lgN)
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        List<int[]> idxDiffs = new ArrayList<>();
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            if (i + 1 < len) {
                if (heights[i] < heights[i + 1]) {
                    idxDiffs.add(new int[]{i, heights[i + 1] - heights[i]});
                }
            }
        }

        int sum = 0;

        // max diff heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        for (int[] pair : idxDiffs) {
            sum += pair[1];

            pq.add(pair);
            if (sum > bricks) {
                int[] node = pq.poll();
                if (ladders > 0) {
                    ladders--;
                    sum -= node[1];
                } else {
                    return pair[0];
                }
            }
        }
        return len - 1;
    }
    // endregion
}
