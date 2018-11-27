package org.sean.array;

import java.util.HashSet;
import java.util.Set;

public class ZeroMatrix {
    private int[][] matrix;

    public int[][] getMatrix() {
        return matrix;
    }

    public ZeroMatrix(int[][] matrix) {
        this.matrix = matrix;

        System.out.println("Initialized matrix :");
        print();
    }

    public void print() {
        if (matrix != null) {
            int rowCnt = matrix.length;
            int columnCnt = matrix[0].length;

            for (int i = 0; i < rowCnt; i++) {
                for (int j = 0; j < columnCnt; j++) {
                    System.out.print(matrix[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
        }
    }

    public void setZero() {
        if (matrix != null) {
            Set<Integer> set = new HashSet<>();
            int rowCnt = matrix.length;
            int columnCnt = matrix[0].length;

            for (int i = 0; i < rowCnt; i++) {
                for (int j = 0; j < columnCnt; j++) {
                    if (matrix[i][j] == 0) {
                        set.add(i);
                        set.add(j);
                    }
                }
            }

            for (Integer e : set) {
                for (int i = 0; i < rowCnt; i++)
                    matrix[i][e] = 0;

                for (int j = 0; j < columnCnt; j++)
                    matrix[e][j] = 0;
            }

            System.out.println("Changed matrix :");
            print();
        }
    }
}
