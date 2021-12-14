package org.sean.backtracking;

// Rat in a Maze | Backtracking-2
// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2
//
// Count number of ways to reach destination in a Maze
// Given a maze with obstacles, count the number of paths to reach the rightmost-bottommost cell from the
// topmost-leftmost cell. A cell in the given maze has a value of -1 if it is a blockage or dead-end, else 0.
//
// From a given cell, we are allowed to move to cells (i+1, j) and (i, j+1) only.
//
// Examples:
// Input: maze[R][C] =  {{0,  0, 0, 0},
//                      {0, -1, 0, 0},
//                      {-1, 0, 0, 0},
//                      {0,  0, 0, 0}};
//Output: 4

import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

public class MazePathFinder {
    // Returns count of possible paths in
    // a maze[R][C] from (0,0) to (R-1,C-1)
    int[][] moves = new int[][]{{1, 0}, {0, 1}};
    int total = 0;

    Deque<int[]> paths = new LinkedList<>();

    void move(int row, int col, int maze[][]) {
        int maxRow = maze.length - 1;
        int maxCol = maze[0].length - 1;
        if (row == maxRow && col == maxCol) {

            for (int[] loc : paths) {
                System.out.print(String.format(Locale.ENGLISH, "[%d,%d]", loc[0], loc[1]));
                System.out.print(" ");
            }
            System.out.println("");

            ++total;
            return;
        }

        for (int[] mv : moves) {
            int r = row + mv[0];
            int c = col + mv[1];

            if (r <= maxRow && c <= maxCol && maze[r][c] != -1) {
                paths.add(new int[]{r, c});
                move(r, c, maze);
                paths.removeLast();
            }
        }
    }

    int countPaths(int[][] maze) {
        paths.clear();
        paths.add(new int[]{0, 0});

        move(0, 0, maze);
        return total;
    }

    public static void main(String[] args) {
        int[][] input = {
                {0, 0, 0, 0},
                {0, -1, 0, 0},
                {-1, 0, 0, 0},
                {0, 0, 0, 0}
        };
        MazePathFinder finder = new MazePathFinder();
        int count = finder.countPaths(input);

        System.out.println(">>> total paths : " + count);
    }

}
