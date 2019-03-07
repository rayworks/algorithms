package org.sean.recursive;

// 64. Minimum Path Sum
public class MinPathFinder {
    // add a result cache
    private int[][] marks;

    private int row;
    private int column;

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        if (grid.length == 1 && grid[0].length == 1)
            return grid[0][0];

        // m
        row = grid.length;
        // n
        column = grid[0].length;

        marks = new int[row][column];

        int minSum = calcMinSum(grid, row - 1, column - 1);
        return minSum;
    }

    private int calcMinSum(int[][] grid, int row, int column) {
        if (row < 0 || column < 0) {
            return 0;
        }

        if (marks[row][column] != 0) {
            return marks[row][column];
        }

        int sumMin = grid[row][column];
        if (row == 0 || column == 0) {
            if (row == 0 && column == 0) {
                return grid[0][0];
            }

            if (row == 0) {
                return sumMin + calcMinSum(grid, row, column - 1);
            } else {
                // column == 0
                return sumMin + calcMinSum(grid, row - 1, column);
            }

        }

        int topResult = calcMinSum(grid, row - 1, column);
        int leftResult = calcMinSum(grid, row, column - 1);

        int sum = sumMin + Math.min(leftResult, topResult);

        marks[row][column] = sum;
        return sum;
    }
}
