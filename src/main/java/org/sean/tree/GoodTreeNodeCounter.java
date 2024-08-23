package org.sean.tree;

import java.util.*;

/***
 * 3249. Count the Number of Good Nodes
 */
public class GoodTreeNodeCounter {

    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int[] pair = edges[i];
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        // build the tree by level
        boolean[] visited = new boolean[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;
            for (int v : graph.get(cur)) {
                if (!visited[v]) {
                    map.computeIfAbsent(cur, k -> new HashSet<>()).add(v);

                    queue.add(v);
                }
            }
        }

        // for(int k : map.keySet()) {
        //     System.out.println("K : " + k + " : " + Arrays.toString(map.get(k).toArray()));
        // }

        countGoodNodesHelper(map, 0);

        return count;
    }

    private int countGoodNodesHelper(Map<Integer, Set<Integer>> g, int node) {
        if (g.get(node) == null || g.get(node).isEmpty()) {
            count++;
            return 1;
        }

        int sum = 1;
        int last = -1;
        boolean hasEqualCnt = true;
        for (Integer edge : g.get(node)) {
            int res = countGoodNodesHelper(g, edge);

            if (last == -1) {
                last = res;
            } else {
                if (last != res) {
                    hasEqualCnt = false;
                }
            }
            sum += res;
        }

        if (hasEqualCnt) {
            count++;
        }
        return sum;
    }

    private int count;
}
