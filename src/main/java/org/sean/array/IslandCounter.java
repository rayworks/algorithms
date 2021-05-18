package org.sean.array;

import org.sean.graph.UnionFind;

/***
 * 200. Number of Islands
 */
public class IslandCounter {
    private UnionFind unionFind;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;

        int n = row * col;
        int p = -1;
        int q = -1;

        unionFind = new UnionFind(n);

        int zeroCnt = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j;

                if (grid[i][j] == '1') {
                    if (i + 1 < row) {
                        if (grid[i + 1][j] == '1') {
                            int p2 = (i + 1) * col + j;
                            unionFind.union(pos, p2);

                            System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos, p2));
                        }
                    }
                    if (j + 1 < col) {
                        if (grid[i][j + 1] == '1') {
                            int p2 = i * col + j + 1;
                            unionFind.union(pos, p2);

                            System.out.println(String.format(">>> Evaluate pos [%d, %d]", pos, p2));
                        }
                    }
                } else {
                    ++zeroCnt;
                }
            }
        }

        return unionFind.count() - zeroCnt;
    }
}
