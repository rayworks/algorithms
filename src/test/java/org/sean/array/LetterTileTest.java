package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LetterTileTest {

    private LetterTile letterTile;

    @Before
    public void setup() {
        letterTile = new LetterTile();
    }

    @Test
    public void numTilePossibilities() {
        assertEquals(8, letterTile.numTilePossibilities("AAB"));
        assertEquals(188, letterTile.numTilePossibilities("AAABBC"));
        assertEquals(1, letterTile.numTilePossibilities("V"));
    }
}