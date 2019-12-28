package org.sean.recursive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniquePathsTest {

    private UniquePaths paths;

    @Before
    public void setUp() throws Exception {
        paths = new UniquePaths();
    }

    @Test
    public void uniquePaths() {
        Assert.assertEquals(3, paths.uniquePaths(3,2));
    }
}