package org.sean.recursive;

// 64. Minimum Path Sum
public class MinPathFinder {
    // add a result cache
    private int[][] marks;

    private int row;
    private int column;

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        if (grid.length == 1 && grid[0].length == 1) return grid[0][0];

        // m
        row = grid.length;
        // n
        column = grid[0].length;

        marks = new int[row][column];

        return calcMinSum(grid, row - 1, column - 1);
    }

    private int calcMinSum(int[][] grid, int row, int column) {
        if (row < 0 || column < 0) {
            return -1; // non-negative values for each cell
        }

        if (marks[row][column] != 0) {
            return marks[row][column];
        }

        if (row == 0 && column == 0) {
            return grid[0][0];
        }

        int sumMin = grid[row][column];

        int topResult = calcMinSum(grid, row - 1, column);
        int leftResult = calcMinSum(grid, row, column - 1);

        int subMin;
        if (topResult >= 0 && leftResult >= 0) {
            // Calling method Math.min() takes extra time!
            subMin = leftResult < topResult ? leftResult : topResult;
        } else {
            subMin = topResult < 0 ? leftResult : topResult;
        }

        int sum = sumMin + subMin;

        marks[row][column] = sum;
        return sum;
    }
}
