package org.sean.graph;

import java.util.ArrayList;
import java.util.List;

// Chap16.19 Pond Sizes in CTCI 6E
public class PondSize {
    private final int[][] moves = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    private int calcSize(int[][] matrix, int r, int c) {
        int rowCnt = matrix.length;
        int colCnt = matrix[0].length;
        matrix[r][c] = 1;

        int size = 1;
        for (int[] diff : moves) {
            int nR = r + diff[0];
            int nC = c + diff[1];
            if (nR >= 0 && nR < rowCnt && nC >= 0 && nC < colCnt && matrix[nR][nC] == 0) {
                size += calcSize(matrix, nR, nC);
            }
        }
        return size;
    }

    public int[] size(int[][] matrix) {
        // hor,ver,dra -> pool
        // 0 -> water area
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    out.add(calcSize(matrix, i, j));
                }
            }
        }
        return out.stream().mapToInt(i -> i).toArray();
    }
}
