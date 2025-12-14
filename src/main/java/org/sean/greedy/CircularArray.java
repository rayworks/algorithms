package org.sean.greedy;

import java.util.ArrayList;
import java.util.PriorityQueue;

/***
 * 3776. Minimum Moves to Balance Circular Array
 */
public class CircularArray {

    public long minMoves(int[] balance) {
        int len = balance.length;
        long sum = 0L;
        int centerPos = -1;
        for (int i = 0; i < len; i++) {
            int e = balance[i];
            sum += e;
            if (e < 0) {
                centerPos = i;
            }
        }
        if (sum < 0)
            return -1;

        if (centerPos < 0) // no negative element
            return 0;

        int[] visited = new int[len];
        int negativeVal = -balance[centerPos];

        // BFS: track the increasing steps

        // val, pos, increasedDir?
        // {elem, index, dir}
        // int[] pair
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));
        int next = (centerPos + 1 + len) % len;
        int prev = (centerPos - 1 + len) % len;

        if (visited[next] != 1) {
            visited[next] = 1;
            pq.add(new int[]{balance[next], next, 1});
        }
        if (visited[prev] != 1) {
            visited[prev] = 1;
            pq.add(new int[]{balance[prev], prev, 0});
        }

        long res = 0;
        long step = 1;
        while (!pq.isEmpty() && negativeVal > 0) {
            int sz = pq.size();
            // System.out.printf(">>>  queue size : %d\n", sz);
            // System.out.println(Arrays.toString(pq.stream().mapToInt(arr->arr[0]).toArray()));

            ArrayList<int[]> subCells = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                int[] pair = pq.poll();
                int val = pair[0];
                int pos = pair[1];
                int inc = pair[2];

                if (val >= negativeVal) {
                    res += step * negativeVal;
                    // System.out.printf(">>> inc val comp: %d at pos: %d with Step : %d\n", negativeVal, pos, step);
                    return res;
                } else /*if (val > 0)*/ {
                    res += step * val;
                    negativeVal -= val; // value complemented
                    // System.out.printf(">>> inc val : %d at pos: %d with Step : %d\n", val, pos, step);
                }

                // prep for next round
                if (inc == 1) {
                    next = (pos + 1 + len) % len;
                    if (visited[next] != 1) {
                        visited[next] = 1;
                        subCells.add(new int[]{balance[next], next, 1});
                    }
                } else {
                    prev = (pos - 1 + len) % len;
                    if (visited[prev] != 1) {
                        visited[prev] = 1;
                        subCells.add(new int[]{balance[prev], prev, 0});
                    }
                }
            }

            pq.addAll(subCells);
            step++;
        }

        return res;
    }
}
