package org.sean.backtracking;

/***
 * 980. Unique Paths III
 */
public class UniquePaths3 {
    private static final int INT_END = 2;
    private static final int INT_OBSTACLE = -1;
    private static final int INT_START = 1;
    private int pathCnt = 0;
    private boolean[][] visited;

    private final int[][] directions = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };
    private int startRow = 0, startCol = 0;

    private void traverse(int[][] grid, int row, int col) {
        int rowCnt = grid.length;
        int colCnt = grid[0].length;

        if (grid[row][col] == INT_OBSTACLE) return;

        visited[row][col] = true;

        if (grid[row][col] == INT_END) {
            boolean allVisited = true;
            out:
            for (int i = 0; i < rowCnt; i++) {
                for (int j = 0; j < colCnt; j++) {
                    if (grid[i][j] != INT_OBSTACLE && !visited[i][j]) {
                        allVisited = false;
                        break out;
                    }
                }
            }

            if (allVisited)
                pathCnt++;
            return;
        }

        for (int[] dir : directions) {
            int nR = row + dir[0];
            int nC = col + dir[1];
            if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt
                    && grid[nR][nC] != INT_OBSTACLE && !visited[nR][nC]) {

                visited[nR][nC] = true;
                traverse(grid, nR, nC);
                visited[nR][nC] = false;
            }
        }

    }

    public int uniquePathsIII(int[][] grid) {
        int rowCnt = grid.length;
        int colCnt = grid[0].length;
        visited = new boolean[rowCnt][colCnt];

        outer:
        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                if (grid[i][j] == INT_START) {
                    startRow = i;
                    startCol = j;
                    break outer;
                }
            }
        }

        traverse(grid, startRow, startCol);

        return pathCnt;
    }
}
