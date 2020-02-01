package org.sean.array;

/** * 240. Search a 2D Matrix II */
public class MatrixSearcher2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // S1: O(m*n)

        // S2: lgN*N
        if (matrix == null || matrix.length == 0) return false;
        if (matrix.length == 1 && matrix[0].length == 1) return matrix[0][0] == target;

        int row = matrix.length;
        int col = matrix[0].length;

        if (col == 0) return false;

        if (col >= row) {
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] > target || matrix[i][col - 1] < target) continue;
                boolean found = binSearch(matrix, target, true, i);
                if (found) return true;
            }
        } else {
            for (int i = 0; i < col; i++) {
                if (matrix[0][i] > target || matrix[row - 1][i] < target) continue;
                boolean found = binSearch(matrix, target, false, i);
                if (found) return true;
            }
        }

        return false;
    }

    private boolean binSearch(int[][] array, int target, boolean byRow, int index) {
        int left = 0;
        int right = byRow ? array[0].length - 1 : array.length - 1; // column : row

        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            int elem; // = array[mid];
            if (byRow) {
                elem = array[index][mid];
            } else {
                elem = array[mid][index];
            }

            if (elem == target) return true;
            else if (elem < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
