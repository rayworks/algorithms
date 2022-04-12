package org.sean.array;

/***
 * 289. Game of Life
 */
public class LifeGame {
    // follow up : in-place update
    public void gameOfLife(int[][] board) {
        update(board, 0);
    }

    // recursive
    private void update(int[][] grid, int pos) {
        int rCnt = grid.length;
        int cCnt = grid[0].length;
        if (pos >= rCnt * cCnt)
            return;

        int r = pos / cCnt;
        int c = pos % cCnt;
        int nVal = calc(grid, r, c);

        update(grid, pos + 1);

        grid[r][c] = nVal;
    }

    // basic idea
    public void gameOfLife0(int[][] board) {
        int rCnt = board.length;
        int cCnt = board[0].length;

        int[][] copy = new int[rCnt][cCnt];
        for (int i = 0; i < rCnt; i++) {
            for (int j = 0; j < cCnt; j++) {
                copy[i][j] = calc(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(copy[i], 0, board[i], 0, cCnt);
        }
    }

    private int calc(int[][] grid, int r, int c) {
        int[][] dir = new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        int cnt = 0;
        int val = grid[r][c];
        for (int[] offset : dir) {
            int nr = r + offset[0];
            int nc = c + offset[1];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length) {
                if (grid[nr][nc] == 1) {
                    cnt++;
                }
            }
        }

        int next;
        if (val == 1)
            next = cnt == 2 || cnt == 3 ? 1 : 0;
        else
            next = cnt == 3 ? 1 : 0;

        return next;
    }
}
