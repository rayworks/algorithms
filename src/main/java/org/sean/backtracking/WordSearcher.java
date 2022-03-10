package org.sean.backtracking;

import java.util.*;

/***
 * 79. Word Search
 */
public class WordSearcher {

    // dfs + backtracking
    private boolean search(
            char[][] board, String word, int pos, int x, int y, boolean[][] visited) {

        if (pos > word.length() - 1) {
            return true;
        }

        int[] offsetsX = {0, 1, 0, -1};
        int[] offsetsY = {-1, 0, 1, 0};

        int row = board.length;
        int col = board[0].length;

        int offsetLen = offsetsX.length;
        if (x < 0
                || x >= row
                || y < 0
                || y >= col
                || visited[x][y]
                || (board[x][y] != word.charAt(pos))) return false;

        visited[x][y] = true;

        boolean found = false;
        for (int i = 0; i < offsetLen; i++) {
            boolean result =
                    (search(board, word, pos + 1, x + offsetsX[i], y + offsetsY[i], visited));
            found = found || result;
            if (found) {
                return true;
            }
        }
        visited[x][y] = false;

        return false;
    }

    public boolean exist1(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null) return false;
        int row = board.length;
        int column = board[0].length;

        if (word.length() > row * column) {
            return false;
        }

        visited = new boolean[row][column];
        char start = word.charAt(0);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == start) {
                    if (search(board, word, 0, i, j, visited)) return true;
                }
            }
        }
        return false;
    }

    // ===================== iterative ===================== //
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 4;
    private static final int LEFT = 8;

    int[][] marks;
    int[][] added;
    Stack<int[]> stack = new Stack<>();
    Map<Character, List<int[]>> map = new HashMap<>();

    public boolean exist0(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null) return false;
        int row = board.length;
        int column = board[0].length;

        if (word.length() > row * column) {
            return false;
        }

        map.clear();
        marks = new int[row][column];
        added = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                char ch = board[i][j];
                if (map.containsKey(ch)) {
                    List<int[]> positions = map.get(ch);
                    positions.add(new int[] {i, j});
                } else {
                    List<int[]> positions = new LinkedList<>();
                    positions.add(new int[] {i, j});

                    map.put(ch, positions);
                }
            }
        }

        int wordLen = word.length();
        for (int i = 0; i < wordLen; i++) {
            char c = word.charAt(i);

            if (!map.containsKey(c)) {
                return false;
            }
        }

        List<int[]> positions = map.get(word.charAt(0));

        for (int[] location : positions) {
            stack.push(location);
            added[location[0]][location[1]] = 1;

            while (!stack.isEmpty()) {
                int[] loc = stack.peek();
                int r = loc[0], c = loc[1];

                int size = stack.size();
                if (size < wordLen) {
                    if (access(TOP, r, c, row, column)) {
                        if (board[r - 1][c] == word.charAt(size) && added[r - 1][c] != 1) {
                            stack.push(new int[] {r - 1, c});
                            added[r - 1][c] = 1;

                            marks[r - 1][c] |=
                                    DOWN; // mark the current movement for the next position
                            continue;
                        }
                    }
                    if (access(RIGHT, r, c, row, column)) {
                        if (board[r][c + 1] == word.charAt(size) && added[r][c + 1] != 1) {
                            stack.push(new int[] {r, c + 1});
                            added[r][c + 1] = 1;

                            marks[r][c + 1] |= LEFT;
                            continue;
                        }
                    }
                    if (access(DOWN, r, c, row, column)) {
                        if (board[r + 1][c] == word.charAt(size) && added[r + 1][c] != 1) {
                            stack.push(new int[] {r + 1, c});
                            added[r + 1][c] = 1;

                            marks[r + 1][c] |= TOP;
                            continue;
                        }
                    }
                    if (access(LEFT, r, c, row, column)) {
                        if (board[r][c - 1] == word.charAt(size) && added[r][c - 1] != 1) {
                            stack.push(new int[] {r, c - 1});
                            added[r][c - 1] = 1;

                            marks[r][c - 1] |= RIGHT;
                            continue;
                        }
                    }
                    // else
                    {
                        if (stack.isEmpty()) {
                            break;
                        }

                        // reset the visited history
                        int[] pts = stack.pop();
                        int x = pts[0];
                        int y = pts[1];
                        added[x][y] = 0;
                        marks[x][y] = 0;
                    }

                } else {
                    int pos = 0;
                    int cnt = 0;
                    for (int i = 0; i < wordLen; i++) {
                        int[] pts = stack.pop();
                        char ch = board[pts[0]][pts[1]];
                        if (word.charAt(wordLen - 1 - i) == ch) {
                            ++cnt;
                        }
                    }
                    if (cnt == wordLen) {
                        return true;
                    }
                }
            }

            // reset the history
            added[location[0]][location[1]] = 0;
        }
        return false;
    }

    private boolean access(int direction, int r, int c, int row, int column) {
        if ((marks[r][c] & direction) != direction) {
            marks[r][c] |= direction;

            switch (direction) {
                case TOP:
                    if (r - 1 >= 0) {
                        return true;
                    }
                    break;
                case RIGHT:
                    if (c + 1 < column) {
                        return true;
                    }
                    break;
                case DOWN:
                    if (r + 1 < row) {
                        return true;
                    }
                    break;
                case LEFT:
                    if (c - 1 >= 0) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    // region backtracking
    boolean found = false;
    int[][] moves =  new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    boolean[][] visited;
    void searchWord(char[][] board, String word, int pos, int currR, int currC) {

        if(pos >= word.length() - 1) {
            found = true;
            return;
        }

        int rMax = board.length;
        int cMax = board[0].length;

        char next = word.charAt(pos + 1);
        for (int i = 0; i < moves.length; i++) {
            int[] mv = moves[i];
            int nR = currR + mv[0];
            int nC = currC + mv[1];
            if (nR >= 0 && nR < rMax && nC >= 0 && nC < cMax && !visited[nR][nC]) {
                if(board[nR][nC] == next) {
                    visited[nR][nC] = true;

                    searchWord(board, word, pos + 1, nR, nC);
                    if(found) return;

                    visited[nR][nC] = false;
                }
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;

        visited = new boolean[row][col];

        outer: for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (found) {
                    break outer;
                }

                if(board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    searchWord(board, word, 0, i, j);
                    visited[i][j] = false;
                }
                if (found) {
                    break outer;
                }
            }
        }
        return found;
    }
    // endregion backtracking
 }
