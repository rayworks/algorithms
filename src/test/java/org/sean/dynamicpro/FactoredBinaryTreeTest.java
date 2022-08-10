package org.sean.dynamicpro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoredBinaryTreeTest {

    private FactoredBinaryTree tree;

    @Before
    public void setUp() throws Exception {
        tree = new FactoredBinaryTree();
    }

    @Test
    public void numFactoredBinaryTrees() {
        assertEquals(7, tree.numFactoredBinaryTrees(new int[]{2, 4, 5, 10}));
    }
}