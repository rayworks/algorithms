package org.sean.array;

import java.util.*;

/***
 * 130. Surrounded Regions
 */
public class RegionDetector {
    private int[] parent; // parent[i] = parent of i
    private int[] size; // size[i] = number of sites in subtree rooted at i
    private int count; // number of components

    private void init(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /** The number of components. */
    private int count() {
        return count;
    }

    private int find(int p) {
        validate(p);
        while (p != parent[p]) p = parent[p];

        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        count--;
    }

    private void link(int p, int q) {
        if (!connected(p, q)) {
            union(p, q);
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length <= 1) return;
        int row = board.length;
        int col = board[0].length;

        int k = row * col;
        init(k);

        char chBigO = 'O';
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j;
                if (board[i][j] == chBigO) {
                    if (i + 1 < row) {
                        if (board[i + 1][j] == chBigO) {
                            int p2 = (i + 1) * col + j;
                            link(pos, p2);

                            // System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos,
                            // p2));
                        }
                    }

                    if (j + 1 < col) {
                        if (board[i][j + 1] == chBigO) {
                            int p2 = i * col + j + 1;
                            link(pos, p2);

                            // System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos,
                            // p2));
                        }
                    }
                }
            }
        }

        if (count <= 1) {
            return;
        }

        Map<Integer, List<Integer>> posMap = new HashMap<>();
        for (int m = 0; m <= row - 1; m++) {
            for (int n = 0; n <= col - 1; n++) {
                if (board[m][n] == chBigO) {
                    int pos = m * col + n;
                    int p = find(pos);

                    if (posMap.containsKey(p)) {
                        posMap.get(p).add(pos);

                    } else {
                        LinkedList<Integer> list = new LinkedList<>();
                        list.add(pos);

                        posMap.put(p, list);
                    }
                }
            }
        }

        for (List<Integer> list : posMap.values()) {
            boolean boardFound = false;

            for (Integer loc : list) {
                int p = loc;
                int r = p / col;
                int c = p % col;
                if (r == 0 || r == (row - 1) || c == 0 || c == (col - 1)) {
                    boardFound = true;
                    break;
                }
            }

            if (!boardFound) {
                for (Integer l : list) {
                    board[l / col][l % col] = 'X';
                }
            }
        }
    }
}
