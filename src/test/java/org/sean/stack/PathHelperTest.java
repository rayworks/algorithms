package org.sean.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathHelperTest {

    private PathHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new PathHelper();
    }

    @Test
    public void simplifyPath() {
        assertEquals("/TJbrd/owxdG", helper.simplifyPath("///TJbrd/owxdG//"));
        assertEquals("/c", helper.simplifyPath("/a/./b/../../c/"));
    }
}