package org.sean.graph;

import java.util.*;

/***
 * 2101. Detonate the Maximum Bombs
 */
public class MaxBombs {

    //
    // Edges in the graph could be up to N^2.
    // Time complexity O(N^3)
    //
    public int maximumDetonation(int[][] bombs) {
        int len = bombs.length;
        if (len == 1)
            return 1;

        boolean[] visited = new boolean[len];
        List<List<Integer>> graph = new ArrayList<>();
        for (int n = 0; n < len; n++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j) {
                    if (included(bombs[i], bombs[j])) {
                        graph.get(i).add(j);
                    }
                }
            }
        }

        // detonate one bomb : check the max reachable booms
        int max = 0;
        for (int i = 0; i < len; i++) {
            int res = dfs(graph, i, visited);
            max = Math.max(max, res);

            Arrays.fill(visited, false);
        }

        return max;
    }

    private boolean included(int[] circle1, int[] circle2) {
        // also consider the possible data precision loss
        double dist =
                Math.pow(circle1[0] - circle2[0], 2) + Math.pow(circle1[1] - circle2[1], 2);
        return 1.0f * circle1[2] * circle1[2] >= dist;
    }

    private int dfs(List<List<Integer>> graph, int pos, boolean[] visited) {
        int cnt = 1;
        visited[pos] = true;

        for (int edge : graph.get(pos)) {
            if (!visited[edge]) {
                cnt += dfs(graph, edge, visited);
            }
        }

        return cnt;
    }
}
