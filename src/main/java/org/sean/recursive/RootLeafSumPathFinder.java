package org.sean.recursive;

import org.sean.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/***
 * 113. Path Sum II
 */
public class RootLeafSumPathFinder {
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    LinkedList<Integer> path = new LinkedList<>();

    private int sum;

    private List<Integer> copy(List<Integer> path) {
        List<Integer> list = new LinkedList<>();
        list.addAll(path);
        return list;
    }

    private boolean addNode(TreeNode root) {
        if (root != null) {
            path.add(root.val);

            if (root.left == null && root.right == null) {

                int total = 0;
                int len = path.size();
                for (int i = 0; i < len; i++) {
                    int digit = path.get(i);
                    total += digit;
                }

                if (total == sum) {
                    result.add(copy(path));
                }
            }

            boolean leftAdded = addNode(root.left);
            if (leftAdded) {
                path.removeLast();
            }

            boolean rightAdded = addNode(root.right);
            if (rightAdded) {
                path.removeLast();
            }

            return true;
        }
        return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return result;
        }

        this.sum = sum;

        addNode(root);

        return result;
    }
}
