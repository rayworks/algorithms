package org.sean.array;

/***
 * 304. Range Sum Query 2D - Immutable
 */
public class NumMatrix {
    private int[][] sumCache;

    public NumMatrix(int[][] matrix) {
        int rowCnt = matrix.length;
        int colCnt = matrix[0].length;
        sumCache = new int[rowCnt][colCnt];

        for (int i = 0; i < rowCnt; i++) {
            sumCache[i][0] = (i > 0 ? sumCache[i - 1][0] : 0) + matrix[i][0];
        }

        for (int j = 0; j < colCnt; j++) {
            sumCache[0][j] = (j > 0 ? sumCache[0][j - 1] : 0) + matrix[0][j];
        }

        for (int i = 1; i < rowCnt; i++) {
            for (int j = 1; j < colCnt; j++) {
                sumCache[i][j] = sumCache[i - 1][j] + sumCache[i][j - 1] + matrix[i][j] - sumCache[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 0 <= row1 <= row2 < m
        int res = sumCache[row2][col2];
        if (row1 > 0) {
            res -= sumCache[row1 - 1][col2];
        }
        if (col1 > 0) {
            res -= sumCache[row2][col1 - 1];
        }
        if (row1 > 0 && col1 > 0) {
            res += sumCache[row1 - 1][col1 - 1];
        }
        return res;
    }
}
