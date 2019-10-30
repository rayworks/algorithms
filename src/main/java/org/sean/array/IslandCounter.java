package org.sean.array;

/***
 * 200. Number of Islands
 */
public class IslandCounter {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    private void init(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * The number of components.
     */
    private int count() {
        return count;
    }

    private int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
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

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int row = grid.length;
        int col = grid[0].length;

        int n = row * col;
        int p = -1;
        int q = -1;

        // take advantage of WeightedQuickUnionUF from Algs4
        // https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html
        init(n);

        int zeroCnt = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j;

                if (grid[i][j] == '1') {
                    if (i + 1 < row) {
                        if (grid[i + 1][j] == '1') {
                            int p2 = (i + 1) * col + j;
                            link(pos, p2);

                            System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos, p2));
                        }
                    }
                    if (j + 1 < col) {
                        if (grid[i][j + 1] == '1') {
                            int p2 = i * col + j + 1;
                            link(pos, p2);

                            System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos, p2));
                        }
                    }
                } else {
                    ++zeroCnt;
                }
            }
        }

        return count() - zeroCnt;
    }
}
