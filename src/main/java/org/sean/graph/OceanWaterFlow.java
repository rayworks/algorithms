package org.sean.graph;

import java.util.*;

/***
 * 417. Pacific Atlantic Water Flow
 */
public class OceanWaterFlow {
    private int[][] moves = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };
    private Set<Integer> visited = new HashSet<>();
    private int[][] matrix;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rowCnt = heights.length;
        int colCnt = heights[0].length;

        Queue<Integer> queue = new LinkedList<>();
        matrix = new int[rowCnt][colCnt];

        // pacific
        for (int i = 0; i < rowCnt; i++) {
            queue.add(i * colCnt);
            visited.add(i * colCnt);
        }
        for (int i = 0; i < colCnt; i++) {
            queue.add(i);
            visited.add(i);
        }
        traverse(heights, rowCnt, colCnt, queue, 1);

        // atlantic
        visited.clear();
        queue.clear();
        for (int i = 0; i < rowCnt; i++) {
            queue.add(colCnt * (i + 1) - 1);
        }
        for (int j = 0; j < colCnt; j++) {
            queue.add((rowCnt - 1) * colCnt + j);
        }
        traverse(heights, rowCnt, colCnt, queue, 2);

        ArrayList<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                if (matrix[i][j] == 3) {
                    out.add(Arrays.asList(i, j));
                }
            }
        }

        return out;
    }

    private void traverse(int[][] heights, int rowCnt, int colCnt, Queue<Integer> queue, int dlt) {
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int loc = queue.poll();
                int row = loc / colCnt;
                int col = loc % colCnt;

                if (matrix[row][col] < dlt)
                    matrix[row][col] += dlt;

                for (int[] diff : moves) {
                    int nR = row + diff[0];
                    int nC = col + diff[1];
                    int newLoc = nR * colCnt + nC;
                    if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt && heights[row][col] <= heights[nR][nC]
                            && !visited.contains(newLoc)) {
                        queue.add(newLoc);
                        visited.add(loc);
                    }
                }
            }
        }
    }
}
