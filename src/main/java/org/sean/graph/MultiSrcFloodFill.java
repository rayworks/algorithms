package org.sean.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/***
 * 3905. Multi Source Flood Fill
 */
public class MultiSrcFloodFill {

    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] grid = new int[n][m];

        Deque<int[]> deque = new ArrayDeque<>();

        for (int[] src : sources) {
            int r = src[0];
            int c = src[1];
            grid[r][c] = src[2];

            // add all sources
            deque.add(new int[]{r, c});
        }

        int[][] offsets = new int[][]{
                {-1, 0}, {0, 1}, {1, 0}, {0, -1}
        };

        int step = 0;
        // <loc, step>
        Map<Integer, Integer> cache = new HashMap<>();

        // BFS
        while (!deque.isEmpty()) {
            int sz = deque.size();

            step++;
            for (int i = 0; i < sz; i++) {
                int[] grp = deque.poll();

                if (grp == null) continue;
                int currRow = grp[0];
                int currCol = grp[1];
                int currVal = grid[currRow][currCol];

                for (int[] off : offsets) {
                    int nRow = currRow + off[0];
                    int nCol = currCol + off[1];
                    if (nRow >= n || nRow < 0 || nCol >= m || nCol < 0)
                        continue;

                    // at the same time step
                    int nextStep = step + 1;
                    int key = nRow * m + nCol;

                    if (grid[nRow][nCol] == 0) {
                        grid[nRow][nCol] = currVal;
                        cache.put(key, nextStep);

                        // added once for next round coloring
                        deque.add(new int[]{nRow, nCol});
                    } else {
                        if (cache.getOrDefault(key, 0) == nextStep
                                && grid[nRow][nCol] < currVal) {
                            grid[nRow][nCol] = currVal;
                        }
                    }
                }

            }
        }

        return grid;
    }
}
