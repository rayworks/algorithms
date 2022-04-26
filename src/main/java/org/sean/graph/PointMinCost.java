package org.sean.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/***
 * 1584. Min Cost to Connect All Points
 */
public class PointMinCost {
    public int minCostConnectPoints(int[][] points) {
        int length = points.length;
        int[][] graph = new int[length][length];

        List<int[]> list = Arrays.asList(points);
        System.out.println(list.size());
        for (int i = 0; i < length; i++) {
            int[] pt = points[i];

            for (int j = 0; j < length; j++) {
                int[] pair = list.get(j);
                int dist = Math.abs(pair[0] - pt[0]) + Math.abs(pair[1] - pt[1]);
                graph[i][j] = dist;
                graph[j][i] = dist;
            }
        }

        // MST : min spanning tree
        int start = 0;
        boolean[] inTree = new boolean[length];

        // [{dist, prev}]
        int[][] vertexInfo = new int[length][2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                if (i != start) { // init all the vertexes
                    vertexInfo[i][0] = Integer.MAX_VALUE;
                }
            }
        }

        return prim(graph, start, inTree, vertexInfo);
    }

    private int prim(int[][] graph, int start, boolean[] inTree, int[][] vertexInfo) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(start);

        while (!deque.isEmpty()) {
            int vertex = deque.poll();
            inTree[vertex] = true;

            // Steps :
            // dequeue v and update edge: Dw = min {Dw, Cw,v} and prev
            // pick up next vertex which has the min dist in the un-known set
            int next = Integer.MAX_VALUE;
            int nextIndex = -1;
            int[] edges = graph[vertex];
            for (int i = 0; i < edges.length; i++) {
                if (vertexInfo[i][0] > edges[i] && !inTree[i]) {
                    vertexInfo[i][0] = edges[i]; // updated dist
                    vertexInfo[i][1] = vertex; // prev
                }
            }

            // select the next vertex
            for (int i = 0; i < edges.length; i++) {
                if (vertexInfo[i][0] < next && !inTree[i]) {
                    next = vertexInfo[i][0];
                    nextIndex = i;
                }
            }
            if (nextIndex >= 0) {
                deque.offer(nextIndex);
            }
        }

        return Arrays.stream(vertexInfo).mapToInt(value -> value[0]).sum();
    }
}
