package org.sean.recursive;

/***
 * 695. Max Area of Island
 */
public class IslandMaxArea {
    private boolean[][] visited;
    private int max = 0;

    int[][] moves = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    /***
     *
     * 1. Mark all the visited cells
     *
     * 2. Traverse the cells with value 1 recursively, and calculate
     * the total area along the way
     *
     */
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;

        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                int cellVal = grid[i][j];
                if (cellVal == 1) {
                    calcArea(grid, i, j, 0);
                } else {
                    visited[i][j] = true;
                }
            }
        }
        return max;
    }

    private int calcArea(int[][] grid, int r, int c, int currSum) {
        if (visited[r][c]) return currSum;

        currSum += 1;
        int row = grid.length;
        int col = grid[0].length;

        int sum = currSum;
        visited[r][c] = true;
        for (int[] move : moves) {
            int x = c + move[1];
            int y = r + move[0];

            if (x >= 0 && x < col && y >= 0 && y < row) {
                int cellVal = grid[y][x];
                if (cellVal == 1) {
                    // add up all extended cells from the 4 directions
                    int n = calcArea(grid, y, x, currSum);
                    sum += n - currSum;
                } else {
                    visited[y][x] = true;
                }
            }
        }

        max = Math.max(max, sum);

        return sum;
    }
}
