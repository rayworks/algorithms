package org.sean.stack;

import java.util.Stack;

/**
 * Created by Sean on 6/7/17.
 * <p>
 * The basic maze algorithm using a Stack to solve the problem.
 */
public class Maze {

    public static final char FOOT_PRINT = '*';
    public static final char MARK_INITIAL = '-';
    public static final char MARK_BLOCKED = '@';

    enum Direction {

        EAST(1),
        SOUTH(2),
        WEST(3),
        NORTH(4);

        private int val;

        Direction(int v) {
            this.val = v;
        }

        public int getVal() {
            return val;
        }

        public static Direction next(Direction direction) {
            if (direction.equals(NORTH)) {
                return NORTH; // no more
            }
            switch (direction) {
                case EAST:
                    return SOUTH;
                case WEST:
                    return NORTH;
                case SOUTH:
                    return WEST;
            }

            throw new IllegalStateException("No next direction available.");
        }
    }

    public static class PosType {
        int r; // row
        int c; // column

        public PosType(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void advance(Direction direction) {
            switch (direction) {
                case EAST:
                    c += 1;
                    break;
                case SOUTH:
                    r += 1;
                    break;
                case WEST:
                    c -= 1;
                    break;
                case NORTH:
                    r -= 1;
                    break;
            }
        }

        @Override
        public String toString() {
            return "PosType{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    class ElemType {
        int step;// order
        PosType seat; // coordinate
        Direction directionNext; // next dir

        public ElemType(int step, PosType seat, Direction directionNext) {
            this.step = step;
            this.seat = seat;
            this.directionNext = directionNext;
        }
    }

    private int currStep;
    private PosType currPos;
    private Stack<ElemType> stack = new Stack<>();
    private boolean found;

    int rowCnt;
    int colCnt;
    int[][] array;

    char path[][];

    private boolean wasHere(int row, int col) {
        return path[row][col] == FOOT_PRINT;
    }

    public Maze() {
    }

    public Maze(int[][] array) {
        update(array);
    }

    public void update(int data[][]) {
        if (data == null || data.length < 1) {
            throw new IllegalArgumentException("invalid input data");
        }

        rowCnt = data.length;
        colCnt = data[0].length;
        array = data;

        path = new char[rowCnt][colCnt];
        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                path[i][j] = MARK_INITIAL;
            }
        }

        printPath();
    }

    /***
     * Finds the path from start position to end position
     *
     * @param start {@link PosType}
     * @param end {@link PosType}
     * @return ture if there is a path, otherwise false.
     */
    public boolean locatePath(PosType start, PosType end) {
        currPos = start;
        currStep = 1;
        found = false;

        do {
            if (pass(currPos)) {
                setFootprint(currPos);

                // To get rid of the reference issue
                // add the new dot with initial direction(E)
                ElemType et = new ElemType(currStep, new PosType(currPos.r, currPos.c), Direction.EAST);
                stack.push(et);

                if (isSame(currPos, end))
                    found = true;
                else {
                    currPos = nextPos(currPos, Direction.EAST);
                    currStep++;
                }
            } else {
                if (!stack.empty()) {
                    ElemType e = stack.pop();
                    while (e.directionNext.equals(Direction.NORTH) && !stack.empty()) {
                        setBlockedMark(e.seat);

                        e = stack.pop();
                        currStep--; // can't pass, fallback one step
                    }

                    if (e.directionNext.getVal() < Direction.NORTH.getVal()) {
                        e.directionNext = Direction.next(e.directionNext); // next search direction

                        stack.push(e);

                        currPos = nextPos(e.seat, e.directionNext); // reset current pos
                    }
                }
            }

        } while (!stack.empty() && !found);

        return found;
    }

    private void setBlockedMark(PosType seat) {
        path[seat.r][seat.c] = MARK_BLOCKED;
        printPath();
    }

    private void setFootprint(PosType currPos) {
        path[currPos.r][currPos.c] = FOOT_PRINT;
        printPath();
    }

    void printPath() {
        int m = path.length;
        int n = path[0].length;

        System.out.println("=================================================");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%c ", path[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println("-------------------------------------------------");
    }

    private PosType nextPos(PosType currPos, Direction direction) {
        PosType pos = new PosType(currPos.r, currPos.c);
        pos.advance(direction);
        return pos;
    }

    private boolean isSame(PosType currPos, PosType end) {
        return currPos.c == end.c && currPos.r == end.r;
    }

    private boolean pass(PosType currPos) {
        if (currPos.r < 0 || currPos.c < 0 || currPos.r >= rowCnt || currPos.c >= colCnt)
            return false;

        // not the block and wasn't here before.
        return array[currPos.r][currPos.c] != 1 && !wasHere(currPos.r, currPos.c);
    }

    public static void main(String[] args) {

        int data[][] = {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        };

        Maze maze = new Maze(data);

        PosType start = new PosType(0, 0);
        PosType end = new PosType(2, 3);

        boolean result = maze.locatePath(start, end);
        if (result) {
            System.out.println("There is a way to get out:");
            maze.printPath();
        } else {
            System.out.println("Can't find the way out.");
        }
    }
}
