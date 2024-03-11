package org.algo.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DijkstraHeapImpl {
    // The cost values from start to other vertexes
    private int[] costs;

    // The previous vertex at vertex i on the shortest path
    private int[] prev;

    public int[] getCosts() {
        return costs;
    }

    /***
     * Finds the shortest path from start to end and returns the min cost.
     * Time complexity: O((V+E)*logV)
     *
     * @param n num of vertex
     * @param pathCost array of {from to, cost}
     * @param start start position
     * @param end end position
     * @return the min cost
     */
    public int shortestPath(int n, int[][] pathCost, int start, int end) {

        // construct the graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] ints : pathCost) {
            int from = ints[0];
            int to = ints[1];
            int cost = ints[2];
            graph.get(from).add(new Edge(to, cost));
        }

        prev = new int[n];
        prev[0] = 0;

        costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        //       source (s)
        //        |    \
        //        |     \
        //        V      \
        //     from (v) --> to (u)
        //
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            // pick up the vertex with the min cost
            Edge edge = pq.poll();
            int from = edge.to;

            for (Edge u : graph.get(from)) {

                if (costs[u.to] > costs[from] + u.cost) {

                    // vertex v is discovered before u,
                    // so let's try to relax the path for the new one.
                    costs[u.to] = costs[from] + u.cost;
                    prev[u.to] = from;

                    // added into the discovered queue
                    pq.add(new Edge(u.to, costs[u.to]));
                }
            }
        }

        return costs[end];
    }

    public List<Integer> extractPath(int start, int pos) {
        if (costs[pos] == Integer.MAX_VALUE)
            return Collections.emptyList();

        List<Integer> path = new LinkedList<>();
        while (pos != start) {
            path.add(pos);
            pos = prev[pos];
        }
        path.add(start);

        Collections.reverse(path);
        return path;
    }

    static class Edge implements Comparable<Edge> {
        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return to == edge.to && cost == edge.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(to, cost);
        }

        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(@NotNull Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }

    public static void main(String[] args) {
        DijkstraHeapImpl algo = new DijkstraHeapImpl();
        int start = 0;
        int end = 4;
        int res = algo.shortestPath(5, new int[][]{
                {0, 1, 4}, {0, 2, 2},
                {1, 2, 3}, {1, 3, 2}, {1, 4, 3},
                {2, 1, 1}, {2, 3, 4}, {2, 4, 5},
                {4, 3, 1}
        }, start, end);

        System.out.println(Arrays.toString(algo.costs));
        System.out.printf(Locale.ENGLISH, "Dist from %d to %d is : %d%n", start, end, res);

        List<Integer> path = algo.extractPath(start, end);
        int[] array = path.stream().mapToInt(i -> i).toArray();
        System.out.println("The path is : " + Arrays.toString(array));
    }
}
