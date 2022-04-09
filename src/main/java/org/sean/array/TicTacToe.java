package org.sean.array;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.util.*;

/***
 * The Tic-tac-toe game
 */
public class TicTacToe {
    public static final char CHAR_EMPTY_CELL = '-';
    public static final char CHAR_BLANK = ' ';
    int nRow = 3;
    int nCol = 3;
    private final char[][] board;
    boolean isOTurn = true;

    public TicTacToe(boolean isOFirst) {
        board = new char[nRow][nCol];
        for (char[] r : board) {
            Arrays.fill(r, CHAR_EMPTY_CELL);
        }

        isOTurn = isOFirst;

        printBoard();
    }


    // For testing usage only
    public TicTacToe(char[][] board) {
        this.board = board;
    }

    private void printBoard() {
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(board[i][j]);
                System.out.print(CHAR_BLANK);
            }
            System.out.println("");
        }
    }

    /***
     * Check and return the game status.
     * @return 'O'/'X' as the winner, or 'U' as uncompleted, 'D' as a draw game.
     */
    public char checkWinner() {
        int sum = 0;
        char pre = CHAR_EMPTY_CELL;
        for (int i = 0; i < nRow; i++) {
            sum = 1;
            pre = board[i][0];
            for (int j = 1; j < nCol; j++) {
                if (pre != CHAR_EMPTY_CELL && board[i][j] == pre) sum++;
                else break;
            }
            if (sum == nCol) return pre;
        }


        for (int i = 0; i < nCol; i++) {
            sum = 1;
            pre = board[0][i];
            for (int j = 1; j < nRow; j++) {
                if (pre != CHAR_EMPTY_CELL && pre == board[j][i]) sum++;
                else break;
            }
            if (sum == nRow) return pre;
        }

        pre = board[0][0];
        if (pre != CHAR_EMPTY_CELL && pre == board[1][1] && pre == board[2][2])
            return pre;

        pre = board[2][0];
        if (pre != CHAR_EMPTY_CELL && pre == board[1][1] && pre == board[0][2])
            return pre;

        boolean emptyCellFound = false;
        out:
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (board[i][j] == CHAR_EMPTY_CELL) {
                    emptyCellFound = true;
                    break out;
                }
            }
        }

        return emptyCellFound ? 'U' : 'D';
    }

    public boolean isCellUpdatable(int r, int c) {
        if (r < 0 || r >= nRow || c < 0 || c >= nCol)
            return false;

        return board[r][c] == CHAR_EMPTY_CELL;
    }

    public void update(int r, int c) {
        board[r][c] = isOTurn ? 'O' : 'X';
        isOTurn = !isOTurn;

        printBoard();
    }

    private static int[] retrieveInputRowCol(Scanner scanner) {
        String row = scanner.next();
        String col = scanner.next();
        int r;
        int c;
        try {
            r = Integer.parseInt(row);
            c = Integer.parseInt(col);
        } catch (NumberFormatException e) {
            r = -1;
            c = -1;
        }
        return new int[]{r, c};
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> trans = applyTranslations(args);

        System.out.println(trans.get("welcome"));
        String s = scanner.next().toUpperCase(Locale.ROOT);

        int r;
        int c;
        TicTacToe ticTacToe = new TicTacToe(s.charAt(0) == 'O');
        while (ticTacToe.checkWinner() == 'U') {
            System.out.println(String.format(trans.get("input_tips"), ticTacToe.isOTurn ? "O" : "X"));

            int[] pair = retrieveInputRowCol(scanner);
            r = pair[0] - 1;
            c = pair[1] - 1;

            while (!ticTacToe.isCellUpdatable(r, c)) {
                while (r < 0 || r >= ticTacToe.nRow || c < 0 || c >= ticTacToe.nCol) {
                    System.out.println(trans.get("invalid_input"));
                    pair = retrieveInputRowCol(scanner);
                    r = pair[0] - 1;
                    c = pair[1] - 1;
                }
                if (!ticTacToe.isCellUpdatable(r, c)) {
                    System.out.println(trans.get("cell_occupied"));
                    r = -1;
                    c = -1;
                }
            }

            System.out.printf(trans.get("your_input"), r + 1, c + 1);

            ticTacToe.update(r, c);
        }

        char winner = ticTacToe.checkWinner();
        switch (winner) {
            case 'O':
            case 'X':
                System.out.println(String.format(trans.get("result_winner"), winner));
                break;
            case 'D':
                System.out.println(trans.get("result_draw"));
                break;
        }
    }

    private static Map<String, String> applyTranslations(String[] args) throws FileNotFoundException {
        String fileName = "zh_cn.json";
        if (args.length > 0) {
            String language = args[0];
            if (language != null && language.contains("cn")) {
                fileName = "zh_cn.json";
            } else {
                fileName = "en.json";
            }
        }

        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream("src/main/resources/" + fileName));
            Map<String, String> trans = new Gson().fromJson(reader, new TypeToken<Map<String, String>>() {
            }.getType());
            return trans;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
