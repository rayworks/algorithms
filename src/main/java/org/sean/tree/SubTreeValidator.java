package org.sean.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// #572 Subtree of Another Tree
public class SubTreeValidator {
    private void locateNode(TreeNode s, TreeNode t, List<TreeNode> nodes) {
        if (s != null) {

            if (s.left != null)
                locateNode(s.left, t, nodes);

            if (s.right != null)
                locateNode(s.right, t, nodes);

            if (s.val == t.val) {
                boolean leftEqual = false;
                boolean rightEqual = false;
                if (s.left != null && t.left != null && s.left.val == t.left.val || (t.left == null && s.left == null)) {
                    leftEqual = true;
                }

                if (s.right != null && t.right != null && s.right.val == t.right.val ||
                        (t.right == null && s.right == null)) {
                    rightEqual = true;
                }

                if (leftEqual && rightEqual) {
                    nodes.add(s);
                }
            }
        }
    }

    private void getPostTraversalPath(TreeNode node, List<Integer> output) {
        if (node != null) {

            if (node.left != null)
                getPostTraversalPath(node.left, output);

            if (node.right != null)
                getPostTraversalPath(node.right, output);

            output.add(node.val);
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null)
            return false;


        List<TreeNode> outList = new LinkedList<>();
        locateNode(s, t, outList);

        List<Integer> dstNodes = new LinkedList<>();
        getPostTraversalPath(t, dstNodes);

        Integer[] integers = dstNodes.toArray(new Integer[dstNodes.size()]);

        boolean isSubtree = false;
        for (TreeNode node : outList) {
            List<Integer> path = new LinkedList<>();
            getPostTraversalPath(node, path);

            Integer[] tmpInts = path.toArray(new Integer[path.size()]);
            if (Arrays.equals(tmpInts, integers)) {
                isSubtree = true;
                break;
            }
        }

        return isSubtree;
    }
}
