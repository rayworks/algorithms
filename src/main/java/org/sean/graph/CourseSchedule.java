package org.sean.graph;

import java.util.*;

/** * 207. Course Schedule */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        int pairLen = prerequisites.length;

        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) inDegrees[i] = -1;

        Map<Integer, List<Integer>> preMap = new HashMap<>();

        for (int i = 0; i < pairLen; i++) {
            int node = prerequisites[i][0];
            int pre = prerequisites[i][1];

            if (inDegrees[pre] < 0) {
                inDegrees[pre] = 0; // involved Pre-Node
            }

            if (inDegrees[node] <= 0) inDegrees[node] = 1;
            else inDegrees[node] += 1;

            if (preMap.containsKey(pre)) {
                preMap.get(pre).add(node);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(node);
                preMap.put(pre, list);
            }
        }

        int labelCnt = 0;
        int allNodeCnt = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] >= 0) ++allNodeCnt;

            // Topological Sort :  starts processing the vertexes with 0 inDegree
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int elem = queue.remove();
            ++labelCnt;

            if (preMap.containsKey(elem)) {
                List<Integer> list = preMap.get(elem);

                // update related vertex inDegree according to the adjacent
                // edges when removing current vertex
                for (Integer node : list) {
                    inDegrees[node] -= 1;

                    if (inDegrees[node] == 0) {
                        queue.add(node);
                    }
                }
            }
        }

        return labelCnt == allNodeCnt;
    }
}
