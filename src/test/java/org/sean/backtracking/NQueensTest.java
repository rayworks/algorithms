package org.sean.backtracking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NQueensTest {

    private NQueens nQueens;

    @Before
    public void setUp() throws Exception {
        nQueens = new NQueens();
    }

    @Test
    public void solveNQueens() {
        Assert.assertEquals(2, nQueens.solveNQueens(4).size());
        Assert.assertEquals(10, nQueens.solveNQueens(5).size());
    }
}
