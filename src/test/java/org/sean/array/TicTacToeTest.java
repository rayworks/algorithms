package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeTest {

    private TicTacToe ticTacToe;

    @Before
    public void setUp() throws Exception {
        ticTacToe = new TicTacToe(new char[][]{
                {'O', 'X', 'O'},
                {'X', 'O', 'X'},
                {'X', 'O', 'O'}
        });
    }

    @Test
    public void checkWinner() {
        char winner = ticTacToe.checkWinner();
        System.out.println(">>> " + winner);
        assertEquals('O', winner);
    }
}