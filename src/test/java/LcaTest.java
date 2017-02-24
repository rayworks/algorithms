import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sean.tree.Lca;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import java.util.Arrays;

/**
 * Created by Sean on 1/16/17.
 */
public class LcaTest {

    private TreeNode root;
    private TreeNode t1;
    private TreeNode t4;

    @Before
    public void setup(){

        //5,3,6,2,4,null,null,1
        String[] values = Arrays.asList("5", "3", "6",  "2", "4", "null", "null", "1")
                .toArray(new String[8]);

        root = TreeHelper.buildTreeFrom(values);

        t1 = new TreeNode(1);
        t4 = new TreeNode(4);

    }

    @Test
    public void testLowestCommonAncestor() throws Exception {
        //Assert.assertEquals("12", "12");
        Lca lca = new Lca();
        TreeNode treeNode = lca.lowestCommonAncestor(root, t1, t4);
        Assert.assertEquals(3, treeNode.val);

    }
}
