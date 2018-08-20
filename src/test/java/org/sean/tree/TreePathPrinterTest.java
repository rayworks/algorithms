package org.sean.tree;

import org.junit.Test;
import org.sean.utils.TreeHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TreePathPrinterTest {

    @Test
    public void binaryTreePaths() {
        String[] values = Arrays.asList("1", "2", "3", "null", "5")
                .toArray(new String[5]);

        TreeNode root = TreeHelper.buildTreeFrom(values);

        TreePathPrinter pathPrinter = new TreePathPrinter();
        List<String> paths = pathPrinter.binaryTreePaths(root);

        System.out.println(Arrays.toString(paths.toArray()));
    }
}