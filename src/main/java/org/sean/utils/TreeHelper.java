package org.sean.utils;

import org.sean.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Sean on 11/18/16.
 */
public final class TreeHelper {
    public static boolean isSameTree(TreeNode treeNodeLeft, TreeNode treeNodeRight) {
        final List<String> listLeft = new LinkedList<>();
        final List<String> listRight = new LinkedList<>();

        traverseTreeByLevel(treeNodeLeft, new NodeProcessor() {
            @Override
            public void precessNode(TreeNode node) {
                listLeft.add(node.getValueStr());
            }
        });

        traverseTreeByLevel(treeNodeRight, new NodeProcessor() {
            @Override
            public void precessNode(TreeNode node) {
                listRight.add(node.getValueStr());
            }
        });

        return listLeft.equals(listRight);
    }

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

    public interface NodeProcessor {
        /***
         * Callback for processing current {@link TreeNode}.
         * @param node TreeNode instance.
         */
        void precessNode(TreeNode node);
    }

    private static void traverseTreeByLevel(TreeNode treeNode, NodeProcessor processor) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            processor.precessNode(node);

            if (node.left != null) {
                queue.add(node.left);
            } else {
                if (!node.isNone()) {
                    queue.add(new TreeNode.NoneNode());
                }
            }

            if (node.right != null) {
                queue.add(node.right);
            } else {
                if (!node.isNone())
                    queue.add(new TreeNode.NoneNode());
            }
        }
    }

    public static void printLevelTree(TreeNode root) {
        if (root != null) {
            traverseTreeByLevel(root, new NodeProcessor() {
                @Override
                public void precessNode(TreeNode node) {
                    if (node != null) {
                        System.out.print(node.getValueStr() + " ");
                    }
                }
            });

            System.out.print("\n");
        }
    }
}
