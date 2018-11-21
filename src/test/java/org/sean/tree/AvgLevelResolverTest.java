package org.sean.tree;

import org.junit.Test;
import org.sean.utils.TreeHelper;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AvgLevelResolverTest {

    @Test
    public void averageOfLevels() {
        String[] values = Arrays.asList("3", "9", "20", "null", "null", "15", "7")
                .toArray(new String[6]);

        TreeNode root = TreeHelper.buildTreeFrom(values);

        List<Double> averages = new AvgLevelResolver().averageOfLevels(root);
        System.out.println(Arrays.toString(averages.toArray(new Double[averages.size()])));
    }
}