package org.sean.tree;

import java.util.ArrayList;
import java.util.List;

/***
 * 2477. Minimum Fuel Cost to Report to the Capital
 */
public class FuelCost {
    private boolean[] visited;
    private long fuelCost;

    // return numbers on this node and calc cost along the way
    private int traverse(int pos, List<List<Integer>> map, int seats) {
        visited[pos] = true;

        int nodeCnt = 1;
        for (int e : map.get(pos)) {
            if (visited[e]) continue;
            nodeCnt += traverse(e, map, seats);
        }

        if (pos != 0) {
            if (nodeCnt > seats) {
                if (nodeCnt % seats == 0) {
                    fuelCost += nodeCnt / seats;
                } else {
                    fuelCost += nodeCnt / seats + 1;
                }
            } else {
                fuelCost += 1;
            }
        }
        return nodeCnt;
    }

    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads == null || roads.length == 0)
            return 0;

        int len = roads.length + 1; // node count
        visited = new boolean[len];
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] pair : roads) {
            int from = pair[0];
            int to = pair[1];
            map.get(from).add(to);
            map.get(to).add(from);
        }

        traverse(0, map, seats);

        return fuelCost;
    }
}
