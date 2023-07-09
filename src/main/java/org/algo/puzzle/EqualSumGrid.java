package org.algo.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Question:
 * Fill the nine squares with the numbers (e.g. 1 to 9), so that the sum of the numbers in each row, column, and
 * diagonal is equal.
 *  - - -
 * |X X X|
 * |X X X|
 * |X X X|
 *  - - -
 */
public class EqualSumGrid {
    public static void main(String[] args) {

        EqualSumGrid grid = new EqualSumGrid();
        grid.place(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    public void place(int[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            candidates[i] = i + 1;
        }

        int[][] grid = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int avg = Arrays.stream(candidates).sum() / grid.length;
        resolvePuzzle(grid, 0, new boolean[9], candidates, avg);

        printResults();
    }

    private void resolvePuzzle(int[][] arr, int pos, boolean[] used, int[] candidates, int avg) {

        int rowCnt = arr.length;
        int colCnt = arr[0].length;

        if (pos >= used.length) {
            int index = 0;
            int[] res = new int[used.length];
            for (int i = 0; i < rowCnt; i++) {
                for (int j = 0; j < colCnt; j++) {
                    res[index++] = arr[i][j];
                }
            }
            list.add(res);
            return;
        }

        int row = pos / colCnt;
        int col = pos % colCnt;


        for (int k = 0; k < candidates.length; k++) {
            if (!used[k] && arr[row][col] == 0) {
                arr[row][col] = candidates[k];
                used[k] = true;

                if (isValid(arr, used, candidates, avg)) {
                    resolvePuzzle(arr, pos + 1, used, candidates, avg);
                }

                used[k] = false;
                arr[row][col] = 0;
            }
        }
    }

    public void printResults() {
        System.out.printf(">>><<< %d result(s) found: \n", list.size());
        for (int[] res : list) {
            int i = 0;

            System.out.printf("\n[\n");
            for (int j = 0; j < res.length; j++) {
                System.out.printf("%d ", res[i++]);
                if (j + 1 >= 3 && (j + 1) % 3 == 0) {
                    System.out.printf("\n");
                }
            }
            System.out.printf("]\n");
        }
    }

    private final List<int[]> list = new ArrayList<>();

    private boolean isValid(int[][] arr, boolean[] used, int[] candidates, int avg) {
        if (!hasValidRows(arr, avg)) return false;

        if (!hasValidColumns(arr, avg)) return false;

        if (!hasValidDiagonalLines(arr, avg)) return false;

        return true;
    }

    private static boolean hasValidDiagonalLines(int[][] arr, int avg) {
        int sum = 0;
        int cnt = 0;
        if (arr[0][0] != 0) {
            sum += arr[0][0];
            cnt++;
        }
        if (arr[1][1] != 0) {
            sum += arr[1][1];
            cnt++;
        }
        if (arr[2][2] != 0) {
            sum += arr[2][2];
            cnt++;
        }
        if (cnt == 2 && sum >= avg) return false;
        if (cnt == 3 && sum != avg) return false;


        sum = cnt = 0;
        if (arr[0][2] != 0) {
            sum += arr[0][2];
            cnt++;
        }
        if (arr[1][1] != 0) {
            sum += arr[1][1];
            cnt++;
        }
        if (arr[2][0] != 0) {
            sum += arr[2][0];
            cnt++;
        }
        if (cnt == 2 && sum >= avg) return false;
        if (cnt == 3 && sum != avg) return false;

        return true;
    }

    private static boolean hasValidColumns(int[][] arr, int avg) {
        for (int i = 0; i < 3; i++) {
            // columns
            int sum = 0;
            int cnt = 0;
            if (arr[0][i] != 0) {
                cnt++;
                sum += arr[0][i];
            }
            if (arr[1][(i + 3) % 3] != 0) {
                cnt++;
                sum += arr[1][(i + 3) % 3];
            }
            if (arr[2][(i + 6) % 3] != 0) {
                cnt++;
                sum += arr[2][(i + 6) % 3];
            }
            if (cnt == 2 && sum >= avg) return false;
            if (cnt == 3 && sum != avg) return false;
        }
        return true;
    }

    private static boolean hasValidRows(int[][] arr, int avg) {
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            int cnt = 0;

            // rows
            if (arr[i][0] != 0) {
                cnt++;
                sum += arr[i][0];
            }
            if (arr[i][1] != 0) {
                cnt++;
                sum += arr[i][1];
            }

            if (arr[i][2] != 0) {
                cnt++;
                sum += arr[i][2];
            }

            if (cnt == 2 && sum >= avg) return false;
            if (cnt == 3 && sum != avg) return false;
        }
        return true;
    }


}
