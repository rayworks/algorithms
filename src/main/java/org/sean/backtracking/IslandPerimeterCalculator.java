package org.sean.backtracking;

// 463. Island Perimeter
public class IslandPerimeterCalculator {
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        directions = new int[row][col];

        int startRow = -1, startCol = -1;
        out:
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;

                    break out;
                }
            }
        }

        traverse(startRow, startCol, grid);

        return boardCnt;
    }

    // 0001
    // 0010
    // 0100
    // 1000
    int boardCnt = 0;

    final int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int[] dirValues = {1, 2, 4, 8};
    int[][] directions;

    int getOppositeDirValue(int dir) {
        switch (dir) {
            case 1:
                return 4;
            case 4:
                return 1;
            case 2:
                return 8;
            case 8:
                return 2;
            default:
                throw new IllegalStateException("No opposite dir for : " + dir);
        }
    }

    private void traverse(int startRow, int startCol, int[][] grid) {
        if (grid[startRow][startCol] == 0) {
            ++boardCnt;
            return;
        }

        int dirCnt = offsets.length;
        for (int i = 0; i < dirCnt; i++) {
            if ((directions[startRow][startCol] & dirValues[i]) >> i != 1) {
                int nR = startRow + offsets[i][0];
                int nC = startCol + offsets[i][1];

                directions[startRow][startCol] += dirValues[i];

                if (!isInvalidPos(nR, nC, grid)) {
                    directions[nR][nC] += getOppositeDirValue(dirValues[i]);
                    traverse(nR, nC, grid);
                } else { // position cross the boundary
                    ++boardCnt;
                }
            }
        }
    }

    private boolean isInvalidPos(int startRow, int startCol, int[][] grid) {
        return startRow < 0
                || startRow >= grid.length
                || startCol < 0
                || startCol >= grid[0].length;
    }
}
