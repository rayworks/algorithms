package org.sean.array;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class TicTacToeAuto {
    public static final int COMP_LOSS = -1;
    public static final int DRAW = 0;
    public static final int COMP_WIN = 1;

    public static final int COMP = 0x1;
    public static final int HUMAN = 0x2;
    private final char[] board;
    private static final char chessEmpty = '-';
    private static final char chessComp = 'O';
    private static final char chessHuman = 'X';

    // all the possible winning lines
    private static final int[][] lineIdxTable =
            new int[][]{
                    {0, 1, 2}, // horizontal lines
                    {3, 4, 5},
                    {6, 7, 8},
                    {0, 3, 6}, // vertical lines
                    {1, 4, 7},
                    {2, 5, 8},
                    {0, 4, 8}, // cross
                    {2, 4, 6},
            };

    public static class MoveInfo {
        public int move;
        public int value;

        public MoveInfo(int m, int v) {
            move = m;
            value = v;
        }

        @Override
        public String toString() {
            return "MoveInfo{" + "move=" + move + ", value=" + value + '}';
        }
    }

    public TicTacToeAuto() {
        board = new char[9];
        Arrays.fill(board, chessEmpty);
    }

    private void place(int pos, int player) {
        update(pos, player == HUMAN ? chessHuman : chessComp);
    }

    void printBoard() {
        for (int i = 1; i <= board.length; i++) {
            System.out.printf("%c ", board[i - 1]);
            if (i % 3 == 0) System.out.println();
        }
    }

    private void unplace(int pos) {
        update(pos, chessEmpty);
    }

    private void update(int pos, char chess) {
        board[pos] = chess;
    }

    private boolean hasWon(char chess) {
        int oCnt, xCnt;
        for (int[] line : lineIdxTable) {
            xCnt = oCnt = 0;

            for (int coord : line) {
                if (chessComp == board[coord]) {
                    oCnt++;
                } else if (chessHuman == board[coord]) {
                    xCnt++;
                }
            }
            if (oCnt == 3 && chess == chessComp || xCnt == 3 && chess == chessHuman) {
                return true;
            }
        }
        return false;
    }

    private MoveInfo immediateWin(char chess) {
        int oCnt, xCnt;
        int nextPos;
        for (int[] line : lineIdxTable) {
            nextPos = xCnt = oCnt = 0;

            for (int coord : line) {
                if (chessComp == board[coord]) {
                    oCnt++;
                } else if (chessHuman == board[coord]) {
                    xCnt++;
                } else {
                    nextPos = coord;
                }
            }

            // search for the empty terminal position for current player
            if (chess == chessComp) {
                if (oCnt == 2 && xCnt == 0) return new MoveInfo(nextPos, COMP_WIN);
            } else {
                if (xCnt == 2 && oCnt == 0) return new MoveInfo(nextPos, COMP_LOSS);
            }
        }

        return null;
    }

    private boolean fullBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i * 3 + j] == chessEmpty) return false;
            }
        }
        return true;
    }

    private boolean isEmpty(int pos) {
        return board[pos] == chessEmpty;
    }

    /***
     *  Recursive method to find best move for computer while performing alpha-beta pruning.
     *  Possible evaluations satisfy COMP_LOSS < DRAW < COMP_WIN.
     *
     *  The main routine should make the call with alpha = COMP_LOSS and beta = COMP_WIN.
     *
     * @return The next {@link MoveInfo} for computer. <code>MoveInfo.move </code>returns a number from 1-9 indicating square.
     */
    public MoveInfo findCompMove(int alpha, int beta) {
        int i, responseValue;
        int value, bestMove = 1;
        MoveInfo quickWinInfo;

        if (fullBoard()) value = DRAW;
        else if ((quickWinInfo = immediateWin(chessComp)) != null) return quickWinInfo;
        else {
            value = alpha;
            for (i = 0; i < 9 && value < beta; i++) {
                if (isEmpty(i)) {
                    place(i, COMP);
                    responseValue = findHumanMove(value, beta).value;
                    unplace(i); // Restore board

                    if (responseValue > value) {
                        value = responseValue;
                        bestMove = i;
                    }
                }
            }
        }

        return new MoveInfo(bestMove, value);
    }

    private MoveInfo findHumanMove(int alpha, int beta) {
        int i, responseValue;
        int value, bestMove = 1;
        MoveInfo quickWinInfo;

        if (fullBoard()) value = DRAW;
        else if ((quickWinInfo = immediateWin(chessHuman)) != null) return quickWinInfo;
        else {
            value = beta;
            for (i = 0; i < 9 && value > alpha; i++) {
                if (isEmpty(i)) {
                    place(i, HUMAN);
                    responseValue = findCompMove(alpha, value).value;
                    unplace(i);

                    if (responseValue < value) {
                        // Update best move
                        value = responseValue;
                        bestMove = i;
                    }
                }
            }
        }
        return new MoveInfo(bestMove, value);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, String> trans = TicTacToe.applyTranslations(args);

        Scanner scanner = new Scanner(System.in);
        System.out.println(trans.get("welcome_challenge"));
        int order = scanner.nextInt();
        boolean isCompTurn = order == 2;

        TicTacToeAuto auto = new TicTacToeAuto();
        auto.printBoard();

        for (int i = 0; !(auto.fullBoard()); i++) {
            if (isCompTurn) {
                MoveInfo compMove = auto.findCompMove(COMP_LOSS, COMP_WIN);
                int cord = compMove.move;
                System.out.printf(
                        trans.get("computer_placed") + "[%d, %d]%n", cord / 3 + 1, cord % 3 + 1);

                auto.place(compMove.move, COMP);
                auto.printBoard();
                if (auto.hasWon(chessComp)) {
                    System.out.println(trans.get("computer_win"));
                    exit(0);
                }
            } else {
                System.out.println(trans.get("input_now"));
                int[] loc;
                do {
                    loc = TicTacToe.retrieveInputRowCol(scanner);
                } while (loc[0] > 3
                        || loc[0] < 1
                        || loc[1] > 3
                        || loc[1] < 1
                        || !auto.isEmpty(3 * (loc[0] - 1) + loc[1] - 1));

                System.out.printf(trans.get("your_input"), loc[0], loc[1]);
                auto.place(3 * (loc[0] - 1) + loc[1] - 1, HUMAN);
                auto.printBoard();
                if (auto.hasWon(chessHuman)) {
                    System.out.println(trans.get("you_win"));
                    exit(0);
                }
            }
            isCompTurn = !isCompTurn;
        }
        System.out.println(trans.get("result_draw"));
    }
}
