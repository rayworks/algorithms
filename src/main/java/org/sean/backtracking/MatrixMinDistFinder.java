package org.sean.backtracking;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 542. 01 Matrix
 */
public class MatrixMinDistFinder {
    private int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private boolean[][] visited;

    // Lesson learnt : for searching target path, normally BFS is way more efficient than DFS
    // (e.g. similar problem : WordLadder).

    // region Solution O(m*n) : BFS
    public int[][] updateMatrix(int[][] mat) {
        Deque<int[]> deque = new LinkedList<>();
        int rowLen = mat.length;
        int colLen = mat[0].length;
        visited = new boolean[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;
                    deque.offer(new int[]{i, j});
                }
            }
        }

        search(mat, deque, rowLen, colLen);

        return mat;
    }

    private void search(int[][] mat, Queue<int[]> deque, int rowLen, int colLen) {
        int level = 0;

        while (!deque.isEmpty()) {
            int sz = deque.size();

            // ArrayList<int[]> list = new ArrayList<>(deque);
            // list.forEach(ints -> System.out.printf("(%d:%d) ", ints[0],ints[1]));
            // System.out.println("\n");

            for (int k = 0; k < sz; k++) {
                int[] coord = deque.poll();
                int currR = coord[0];
                int currC = coord[1];

                mat[currR][currC] = level;

                for (int[] mv : moves) {
                    int nR = currR + mv[0];
                    int nC = currC + mv[1];

                    if (nR >= 0 && nR < rowLen && nC >= 0 && nC < colLen && !visited[nR][nC]) {
                        visited[nR][nC] = true;
                        deque.offer(new int[]{nR, nC});
                    }
                }
            }

            level++;
        }
    }
    // endregion

    // region Solution O(m*n*4^(m*n)) : DFS - TLE!
    public int[][] updateMatrix0(int[][] mat) {
        int rowCnt = mat.length;
        int colCnt = mat[0].length;

        visited = new boolean[rowCnt][colCnt];
        copy = new int[rowCnt][colCnt];

        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                if (mat[i][j] == 1) {
                    visited[i][j] = true;
                    copy[i][j] = traverse(mat, i, j, 0);
                    visited[i][j] = false;
                }
            }
        }

        return copy;
    }

    int[][] copy;

    private int traverse(int[][] mat, int i, int j, int len) {
        int rowCnt = mat.length;
        int colCnt = mat[0].length;

        if (mat[i][j] == 0)
            return len;

        int increased = -1;
        for (int[] move : moves) {
            int nR = i + move[0];
            int nC = j + move[1];

            if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt) {
                if (!visited[nR][nC]) {
                    visited[nR][nC] = true;

                    if (mat[i][j] == 1) {
                        int offset = traverse(mat, nR, nC, len + 1) - len;
                        if (increased < 0) {
                            increased = offset;
                        } else {
                            increased = Math.min(increased, offset);
                        }
                    } else
                        return len;

                    visited[nR][nC] = false;
                }
            }
        }
        return len + Math.max(increased, 0);
    }

    // endregion
}
