package org.sean.dynamicpro;

public class Lcs {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;

                int left = 0;
                int up = 0;
                if (i - 1 >= 0) {
                    up = matrix[i - 1][j];
                }
                if (j - 1 >= 0) {
                    left = matrix[i][j - 1];
                }

                if (text1.charAt(i) == text2.charAt(j)) {
                    if (i >= 1 && j >= 1) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = 1;
                    }
                } else {
                    matrix[i][j] = left > up ? left : up;
                }

            }
        }

        return matrix[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Lcs lcs = new Lcs();
        int cnt = lcs.longestCommonSubsequence("abcde", "ace");
        System.out.println(">>> count : " + cnt);
    }
}
