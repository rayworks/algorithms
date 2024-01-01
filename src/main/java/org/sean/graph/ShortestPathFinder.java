package org.sean.graph;

import java.util.*;

/***
 * 847. Shortest Path Visiting All Nodes
 */
public class ShortestPathFinder {

    public int shortestPathLength(int[][] graph) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int len = graph.length;

        for (int i = 0; i < len; i++) {
            if (!g.containsKey(i))
                g.put(i, new ArrayList<>());

            int edgeCnt = graph[i].length;
            for (int j = 0; j < edgeCnt; j++) {
                g.get(i).add(graph[i][j]);
            }
        }

        int maskMax = (int) Math.pow(2, len);
        int[][] dists = new int[maskMax][len];
        for (int[] row : dists)
            Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int bitMask = 1 << i;
            queue.add(new int[]{i, bitMask});
            dists[bitMask][i] = 0;
        }

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] pair = queue.remove();
                int lead = pair[0];
                int path = pair[1];
                if (path == maskMax - 1) {
                    return dists[path][lead];
                }

                for (int child : g.getOrDefault(lead, Collections.emptyList())) {
                    int next = child;
                    int nextPath = path | (1 << next);

                    if (dists[nextPath][next] != -1) {
                        continue;
                    }

                    dists[nextPath][next] = dists[path][lead] + 1;
                    queue.add(new int[]{next, nextPath});
                }
            }
        }

        return 0;
    }
}
