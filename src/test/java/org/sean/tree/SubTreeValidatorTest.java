package org.sean.tree;

import org.junit.Assert;
import org.junit.Test;
import org.sean.utils.TreeHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SubTreeValidatorTest {

    @Test
    public void isSubtree() {
        // [3,4,5,1,2,null,null,0]
        //[4,1,2]
        List<String> stringList = Arrays.asList("3", "4", "5", "1", "2", "null", "null", "0");
        String[] values = stringList.toArray(new String[stringList.size()]);

        TreeNode root = TreeHelper.buildTreeFrom(values);

        TreeNode dst = TreeHelper.buildTreeFrom(new String[]{"4", "1", "2"});

        SubTreeValidator subTreeValidator = new SubTreeValidator();
        Assert.assertFalse(subTreeValidator.isSubtree(root, dst));
    }
}