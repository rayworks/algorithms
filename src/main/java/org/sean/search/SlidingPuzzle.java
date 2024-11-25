package org.sean.search;

import java.util.*;

/***
 * 773. Sliding Puzzle
 */
public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        // bfs ->
        // exclude the visited state

        int moveCnt = 0;
        if (isFinal(board)) {
            return moveCnt;
        }

        Set<String> visitedPatterns = new HashSet<>();
        visitedPatterns.add(snapshot(board));
        Queue<int[][]> queue = new ArrayDeque<>();
        queue.add(board);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[][] grid = queue.poll();
                if (isFinal(grid)) {
                    return moveCnt;
                }

                addVariedBoards(queue, visitedPatterns, grid);
            }
            moveCnt++;
        }

        return -1;
    }

    private final int[][] finalBoard = {
            {1, 2, 3},
            {4, 5, 0}
    };

    private String snapshot(int[][] arrays) {
        StringBuilder sb = new StringBuilder();
        for (int[] array : arrays) {
            for (int i : array) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    boolean isFinal(int[][] board) {
        return Arrays.equals(board[0], finalBoard[0])
                && Arrays.equals(board[1], finalBoard[1]);
    }

    private final int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int[][] cloneBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    private void addVariedBoards(Queue<int[][]> queue, Set<String> visitedPatterns, int[][] grid) {
        int rCnt = grid.length;
        int cCnt = grid[0].length;

        int i = 0, j = 0;
        out:
        for (i = 0; i < rCnt; i++) {
            for (j = 0; j < cCnt; j++) {
                if (grid[i][j] == 0) {
                    break out;
                }
            }
        }
        for (int[] diff : moves) {
            int nextRow = diff[0] + i;
            int nextCol = diff[1] + j;
            if (nextRow >= rCnt || nextCol >= cCnt || nextRow < 0 || nextCol < 0) {
                continue;
            }

            // swapping
            int tmp = grid[nextRow][nextCol];
            grid[nextRow][nextCol] = 0;
            grid[i][j] = tmp;

            String snap = snapshot(grid);
            if (!visitedPatterns.contains(snap)) {
                visitedPatterns.add(snap);
                queue.add(cloneBoard(grid));
            }

            // reset after swapping
            grid[nextRow][nextCol] = tmp;
            grid[i][j] = 0;
        }

    }
}
