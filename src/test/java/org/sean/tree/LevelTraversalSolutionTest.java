package org.sean.tree;

import org.junit.Before;
import org.junit.Test;
import org.sean.tree.LevelTraversalSolution;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sean on 11/18/16.
 */
public class LevelTraversalSolutionTest {
    @Test
    public void levelOrderBottom() throws Exception {
        // 1,2,5,null, 3, 4, 6, 7

        // tree diagram

        //    1
        //  /   \
        // 2     5
        //  \   / \
        //   3 4   6
        //  /
        // 7


        // filtered node values
        String[] values = Arrays.asList("1", "2", "5", "null", "3", "4", "6", "null", "null", "7")
                .toArray(new String[10]);

        TreeNode root = TreeHelper.buildTreeFrom(values);

        TreeHelper.printLevelTree(root);

        System.out.println("level order from bottom to top:");

        List<List<Integer>> result = new LevelTraversalSolution().levelOrderBottom(root);
        for (List<Integer> list : result) {
            System.out.println(Arrays.toString(list.toArray(new Integer[list.size()])));
        }

    }

}