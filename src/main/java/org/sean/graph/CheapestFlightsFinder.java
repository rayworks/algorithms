package org.sean.graph;

import java.util.*;

/***
 * 787. Cheapest Flights Within K Stops
 */
public class CheapestFlightsFinder {
    // region BFS
    private int[] dist;
    private List<List<int[]>> graph = new ArrayList<>();

    private void bfs(int start, int step, int limit) {
        Queue<int[]> deque = new LinkedList<>();
        deque.add(new int[]{start, 0});

        while (!deque.isEmpty() && step < limit) {
            int sz = deque.size();
            for (int i = 0; i < sz; i++) {
                int[] pair = deque.poll();
                int u = pair[0];
                int curr = pair[1];

                if (graph.get(u).isEmpty()) continue;

                List<int[]> list = graph.get(u);
                for (int[] info : list) {
                    int v = info[0];

                    // revisit the node only when the cost gets smaller
                    if (curr + info[1] >= dist[v]) continue;

                    dist[v] = curr + info[1];
                    deque.add(new int[]{v, dist[v]});
                }
            }

            step++;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : flights) {
            int s = edge[0];
            int d = edge[1];
            int price = edge[2];

            List<int[]> list = graph.get(s);
            list.add(new int[]{d, price});
        }

        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        bfs(src, 0, k + 1);

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
    // endregion

    // region DP
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[n][k + 2];

        for (int i = 0; i < n; i++) {
            int[] arr = dp[i];
            Arrays.fill(arr, Integer.MAX_VALUE / 2);
        }
        dp[src][0] = 0;

        int ret = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= k + 1; i++) {
            for (int j = 0; j < flights.length; j++) {
                int from = flights[j][0];
                int to = flights[j][1];
                int cost = flights[j][2];

                dp[to][i] = Math.min(dp[to][i], dp[from][i - 1] + cost);
                if (to == dst) {
                    ret = Math.min(ret, dp[to][i]);
                }
            }
        }
        return ret == Integer.MAX_VALUE / 2 ? -1 : ret;
    }
    // endregion
}
