import org.junit.Assert;
import org.junit.Before;
import org.sean.tree.BinarySearchTreeChecker;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import java.util.Arrays;

/**
 * Created by Sean on 11/18/16.
 */
public class BinarySearchTreeCheckerTest {
    @Before
    public void setup() {

    }

    @org.junit.Test
    public void isBST() throws Exception {

        String[] values = Arrays.asList("3", "2", "10", "null", "9", "1")
                .toArray(new String[6]);

        TreeNode root = TreeHelper.buildTreeFrom(values);
        BinarySearchTreeChecker checker = new BinarySearchTreeChecker();
        boolean isBSTree = checker.isBST(root);

        Assert.assertFalse(isBSTree);


        values = Arrays.asList("5", "3", "9", "null", "4", "8")
                .toArray(new String[6]);
        Assert.assertTrue(checker.isBST(TreeHelper.buildTreeFrom(values)));


        int min = -(int) Math.pow(2, 31);
        int max = (int) Math.pow(2, 31) - 1;
        values = Arrays.asList("0", min + "", "42", "null", "null", "null", max + "")
                .toArray(new String[7]);
        Assert.assertTrue(checker.isBST(TreeHelper.buildTreeFrom(values)));

        values = Arrays.asList("3", "2", "10", "null", "9", "1")
                .toArray(new String[6]);
        Assert.assertFalse(checker.isBST(TreeHelper.buildTreeFrom(values)));
    }

}