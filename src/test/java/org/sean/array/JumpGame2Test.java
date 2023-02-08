package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JumpGame2Test {

    private JumpGame2 game;

    @Before
    public void setUp() throws Exception {
        game = new JumpGame2();
    }

    @Test
    public void jump() {
        assertEquals(2, game.jump(new int[]{2, 3, 1, 1, 4}));
    }
}