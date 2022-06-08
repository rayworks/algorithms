package org.sean.backtracking;

import java.util.*;

/***
 * 51. N-Queens
 */
public class NQueens {
    private Set<List<String>> results = new HashSet<>();

    private void placeQueens(int[][] board, int pos, int r, int c) {
        int n = board.length;

        if (pos >= n) {
            List<String> solution = locateBoard(board);
            results.add(solution);

            return;
        }
        // c + 1, r -> [0, n)
        int maxY = board.length;
        int maxX = board[0].length;
        if (c >= maxX || r >= maxY || c < 0 || r < 0 || !isValid(board, r, c)) return;

        board[r][c] = 1;
        for (int i = 0; i < n; i++) { // r -[0, n) | c + 1
            placeQueens(board, pos + 1, i, c + 1);
        }

        board[r][c] = 0;

        return;
    }

    private boolean isValid(int[][] board, int x, int y) {
        int rowLength = board.length;
        int col = board[0].length;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < col; j++) {
                if (i == x && j != y && board[i][j] == 1) {
                    return false;
                }
                if (y == j && x != i && board[i][j] == 1) return false;
            }
        }

        int sX = x - 1; // left-top
        int sY = y - 1;
        while (sX >= 0 && sY >= 0) {
            if (board[sX][sY] == 1) {
                return false;
            }
            --sX;
            --sY;
        }

        sX = x + 1; // right-bottom
        sY = y + 1;
        while (sX < col && sY < rowLength) {
            if (board[sX][sY] == 1) {
                return false;
            }
            ++sX;
            ++sY;
        }

        sX = x + 1; // right-top
        sY = y - 1;
        while (sX < col && sY >= 0) {
            if (board[sX][sY] == 1) {
                return false;
            }
            ++sX;
            --sY;
        }

        sX = x - 1; // left-bottom
        sY = y + 1;
        while (sX >= 0 && sY < rowLength) {
            if (board[sX][sY] == 1) {
                return false;
            }
            --sX;
            ++sY;
        }

        return true;
    }

    private List<String> locateBoard(int[][] board) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder builder = new StringBuilder();

            for (int j = 0; j < board[0].length; j++) {
                int dot = board[i][j];
                if (dot == 1) {
                    builder.append('Q');
                } else {
                    builder.append('.');
                }
            }

            list.add(builder.toString());
        }
        return list;
    }

    public List<List<String>> solveNQueens0(int n) {
        if (n == 1) {
            List<List<String>> lists = new ArrayList<>();
            lists.add(Arrays.asList("Q"));
            return lists;
        }
        if (n < 4) return new ArrayList<List<String>>();

        results.clear();
        int[][] board = new int[n][n];
        int pos = 0;

        int y = n;

        for (int i = 0; i < y; i++) {
            placeQueens(board, pos, i, 0);

            // clean up
            for (int j = 0; j < n; j++) {
                Arrays.fill(board[j], 0);
            }
        }

        return new ArrayList<>(results);
    }

    // region backtracking
    List<List<Integer>> out = new ArrayList<>();
    private void placeNQueue(int n, List<Integer> positions) {
        if (positions.size() == n) {
            out.add(new ArrayList<>(positions));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValidPos(n, i, positions)) {
                positions.add(i);
                placeNQueue(n, positions);
                positions.remove(positions.size() - 1);
            }
        }
    }

    private boolean isValidPos(int n, int row, List<Integer> positions) {

        int next = positions.size();
        for (int i = 0; i < positions.size(); i++) {
            int pos = positions.get(i);
            if (pos == row)
                return false;

            if (next - i == pos - row || next - i == row - pos) // check diagonals
                return false;
        }

        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        out.clear();

        placeNQueue(n, new ArrayList<>());

        List<List<String>> ret = new ArrayList<>();
        for (List<Integer> places : out) {
            ArrayList<String> list = new ArrayList<>();

            for (int p : places) {
                char[] arr = new char[n];
                Arrays.fill(arr, '.');
                arr[p]  = 'Q';
                list.add(new String(arr));
            }

            ret.add(list);
        }
        return ret;
    }
    // endregion
}
