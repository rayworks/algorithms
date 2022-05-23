package org.sean.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/***
 * 329. Longest Increasing Path in a Matrix
 */
public class LongestIncPathFinder {
    private final int[][] moves = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // region DFS : TLE 135 / 138 cases passed.
    int maxPathLen = 0;
    boolean[][] visited;

    public int longestIncreasingPath0(int[][] matrix) {
        int rCnt = matrix.length;
        int cCnt = matrix[0].length;
        visited = new boolean[rCnt][cCnt];

        out:for (int i = 0; i < rCnt; i++) {
            for (int j = 0; j < cCnt; j++) {
                search(matrix, i, j, 1);

                if (maxPathLen == rCnt * cCnt)
                    break out;

                for (boolean[] arr : visited) {
                    Arrays.fill(arr, false);
                }

            }
        }

        return maxPathLen;
    }

    private void search(int[][] matrix, int r, int c, int currPathLen) {
        maxPathLen = Math.max(maxPathLen, currPathLen);

        if (visited[r][c]) {
            return ;
        }

        visited[r][c] = true;

        int curr = matrix[r][c];
        for (int[] loc : moves) {
            int nR = r + loc[0];
            int nC = c + loc[1];
            if (isValid(matrix, nR, nC)) {
                if (matrix[nR][nC] > curr) {
                    search(matrix, nR, nC, currPathLen + 1);
                }
            }
        }

        visited[r][c] = false;
    }

    private boolean isValid(int[][] matrix, int nR, int nC) {
        int rCnt = matrix.length;
        int cCnt = matrix[0].length;
        return nR >= 0 && nR < rCnt && nC >= 0 && nC < cCnt;
    }
    // endregion

    // region BFS
    // <loc, [inDegree, outDegree]>
    private int[][] degrees;

    public int longestIncreasingPath(int[][] matrix) {
        int rCnt = matrix.length;
        int cCnt = matrix[0].length;

        degrees = new int[rCnt * cCnt][2];


        for (int i = 0; i < rCnt; i++) {
            for (int j = 0; j < cCnt; j++) {
                int curr = matrix[i][j];

                for (int[] mv : moves) {
                    int nR = i + mv[0];
                    int nC = j + mv[1];
                    int loc = i * cCnt + j;

                    if (nR >= 0 && nR < rCnt && nC >= 0 && nC < cCnt && curr < matrix[nR][nC]) {
                        int newLoc = nR * cCnt + nC;

                        degrees[loc][1] += 1;
                        degrees[newLoc][0] += 1;
                    }
                }
            }
        }

        // enqueue all the vertex with inDegree == 0
        Queue<Integer> queue = new ArrayDeque<>();

        for (int k = 0; k < degrees.length; k++) {
            int[] pair = degrees[k];
            if (pair[0] == 0) {
                queue.add(k);
            }
        }

        return bfs(matrix, queue);
    }

    private int bfs(int[][] matrix, Queue<Integer> queue) {
        int level = 0;
        int rCnt = matrix.length;
        int cCnt = matrix[0].length;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int v = queue.poll();
                int row = v / cCnt;
                int col = v % cCnt;

                for (int[] mv : moves) {
                    int nR = row + mv[0];
                    int nC = col + mv[1];

                    if (nR >= 0 && nR < rCnt && nC >= 0 && nC < cCnt && matrix[nR][nC] > matrix[row][col]) {
                        int loc = nR * cCnt + nC;

                        // enqueue the new vertex with inDegree == 0
                        if (--degrees[loc][0] == 0)
                            queue.offer(loc);
                    }
                }
            }
            level++;
        }
        return level;
    }

    // endregion
}
