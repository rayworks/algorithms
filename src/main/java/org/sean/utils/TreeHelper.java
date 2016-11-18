package org.sean.utils;

import org.sean.tree.TreeNode;

import java.util.LinkedList;


/**
 * Created by Sean on 11/18/16.
 */
public final class TreeHelper {

    public static TreeNode buildTreeFrom(String[] values) {

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        TreeNode root = null;

        int length = values.length;

        for (String value : values) {
            TreeNode node = null;
            if (!value.equals("null")) {
                node = new TreeNode(Integer.valueOf(value));
                if (root == null) {
                    root = node;
                }
            }

            nodes.add(node);
        }

        for (int k = 0; k < length; k++) {
            TreeNode node = nodes.get(k);
            int lh = k * 2 + 1;
            int rh = k * 2 + 2;

            if (node == null)
                continue;

            if (lh < length) {
                node.left = nodes.get(lh);
            }
            if (rh < length) {
                node.right = nodes.get(rh);
            }
        }
        return root;
    }

    public static void printLevelTree(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                System.out.print(node.val + " ");

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            System.out.print("\n");
        }
    }
}
