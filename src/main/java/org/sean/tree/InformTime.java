package org.sean.tree;

import java.util.ArrayList;
import java.util.List;

/***
 * 1376. Time Needed to Inform All Employees
 */
public class InformTime {
    private int timeCost;

    private void numOfMinutesHelper(int pos, List<List<Integer>> g, int[] informTime, int sumSoFar) {
        if (g.get(pos).isEmpty()) {
            timeCost = Math.max(timeCost, sumSoFar);
            return;
        }

        for (int e : g.get(pos)) {
            numOfMinutesHelper(e, g, informTime, sumSoFar + informTime[e]);
        }
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // DFS max sum from root to leaf
        if (n == 1)
            return 0;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int j = 0; j < manager.length; j++) {
            int mgr = manager[j];
            if (mgr != -1) {
                graph.get(mgr).add(j);
            }
        }

        numOfMinutesHelper(headID, graph, informTime, informTime[headID]);

        return timeCost;
    }
}
