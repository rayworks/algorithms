package org.sean.recursive;

import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RootLeafSumPathFinderTest {

    @Test
    public void pathSum() {
        // 5,4,8,11,null,13,4,7,2,null,null,5,1
        String[] values = {"5", "4", "8", "11", "null", "13", "4", "7", "2", "null", "null", "5", "1",
                 "null", "null", "null", "null", "null", "null",  "null", "null"};
        TreeNode root = TreeHelper.buildTreeFrom(values);

        List<List<Integer>> lists = new RootLeafSumPathFinder().pathSum(root, 22);
        List<String> result = new LinkedList<>();
        for (List<Integer> list : lists) {
            result.add(Joiner.on(',').join(list));
        }

        String[] strings = {"5,4,11,2", "5,8,4,5"};
        Assert.assertArrayEquals(strings, result.toArray(new String[result.size()]));
    }
}