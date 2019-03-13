package org.sean.recursive;

import org.sean.tree.TreeNode;

import java.util.LinkedList;

/***
 * 129. Sum Root to Leaf Numbers
 */
public class RootLeafPathCalculator {
    LinkedList<Integer> path = new LinkedList<>();

    private int sum = 0;
    private boolean addNode(TreeNode root) {
        if(root != null) {
            path.add(root.val);

            if(root.left == null && root.right == null) {
                int result = 0;
                int len = path.size();
                for(int i = 0; i < len; i++) {
                    int digit = path.get(i);
                    result += digit * Math.pow(10, (len -1 -i));
                }

                sum+= result;
            }

            boolean leftAdded = addNode(root.left);
            if(leftAdded) {
                path.removeLast();
            }

            boolean rightAdded = addNode(root.right);
            if(rightAdded) {
                path.removeLast();
            }

            return true;
        }
        return false;
    }

    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;

        if(root.left == null && root.right == null) {
            return root.val;
        }

        addNode(root);

        return sum;

    }
}
