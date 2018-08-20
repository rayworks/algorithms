package org.sean.tree;

import java.util.*;

public class TreePathPrinter {
    private LinkedList<Integer> deque = new LinkedList<>();
    private HashSet<String> result = new HashSet<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        deque.push(root.val);// added to the first

        if (isLeaf(root)) {
            int len = deque.size();
            String str = "";
            for (int i = len - 1; i >= 0; i--) {
                if (i != 0) {
                    str += deque.get(i) + "->";
                } else {
                    str += deque.get(i);
                }
            }
            result.add(str);

            deque.pop();
        } else {
            if (root.left != null) {
                result.addAll(binaryTreePaths(root.left));

                if (!isLeaf(root.left))
                    deque.pop();
            }

            if (root.right != null) {
                result.addAll(binaryTreePaths(root.right));

                if (!isLeaf(root.right))
                    deque.pop();
            }
        }

        return Arrays.asList(result.toArray(new String[result.size()]));
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
