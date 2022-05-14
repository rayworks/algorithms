package org.sean.graph;

import java.util.*;

/***
 * 743. Network Delay Time
 */
public class NetworkDelayTime {
    private int maxWight = Integer.MIN_VALUE;

    public int networkDelayTime(int[][] times, int n, int k) {
        // construct the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] grp : times) {
            int v = grp[0];
            List<int[]> edges = graph.getOrDefault(v, new ArrayList<>());
            edges.add(new int[]{grp[1], grp[2]}); // <u, w>
            graph.put(v, edges);
        }

        int[] dist = calcDistances(graph, n, k);
        for (int i = 1; i < n + 1; i++) {
            if (i != k) {
                maxWight = Math.max(maxWight, dist[i]);
            }
        }

        return maxWight == Integer.MAX_VALUE ? -1 : maxWight;
    }

    private int[] calcDistances(Map<Integer, List<int[]>> graph, int n, int src) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new int[]{src, 0});
        dist[src] = 0;
        while (!queue.isEmpty()) {
            // the vertex with min distance
            int[] pair = queue.poll();
            int u = pair[0];

            if (graph.get(u) == null) continue;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int weight = edge[1];

                // relax
                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;

                    queue.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}
