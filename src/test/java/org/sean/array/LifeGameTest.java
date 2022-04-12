package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeGameTest {

    private LifeGame game;

    @Before
    public void setUp() throws Exception {
        game = new LifeGame();
    }

    @Test
    public void gameOfLife() {
        int[][] matrix = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] next = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };

        game.gameOfLife(matrix);
        assertEquals(next, matrix);
    }
}