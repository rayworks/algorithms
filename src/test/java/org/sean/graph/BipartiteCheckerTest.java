package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BipartiteCheckerTest {

    private BipartiteChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new BipartiteChecker();
    }

    @Test
    public void isBipartite() {
        boolean bipartite = checker.isBipartite(new int[][]{
                {1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}
        });
        assertFalse(bipartite);

        bipartite = checker.isBipartite(new int[][]{
                {1, 3}, {0, 2}, {1, 3}, {0, 2}
        });
        assertTrue(bipartite);

        bipartite = checker.isBipartite(new int[][]{
                {2, 4}, {2, 3, 4}, {0, 1}, {1}, {0, 1}, {7}, {9}, {5}, {}, {6}, {12, 14}, {}, {10}, {}, {10}, {19},
                {18}, {}, {16}, {15}, {23}, {23}, {}, {20, 21}, {}, {}, {27}, {26}, {}, {}, {34}, {33, 34}, {}, {31},
                {30, 31}, {38, 39}, {37, 38, 39}, {36}, {35, 36}, {35, 36}, {43}, {}, {}, {40}, {}, {49}, {47, 48, 49},
                {46, 48, 49}, {46, 47, 49}, {45, 46, 47, 48}
        });
        assertFalse(bipartite);
    }
}