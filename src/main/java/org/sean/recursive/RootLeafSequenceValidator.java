package org.sean.recursive;

import org.sean.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/532/week-5/3315/
public class RootLeafSequenceValidator {
    Boolean matched = false;

    boolean gatherAllPaths(TreeNode parent, TreeNode root, List<Integer> path, int[] arr) {
        if (root == null) {
            if (parent != null && parent.left == null && parent.right == null) {
                if (!path.isEmpty()) {
                    if (isMatched(arr, path)) {
                        matched = true;
                    }
                }
            }

            return false;
        }

        path.add(root.val);

        boolean added = gatherAllPaths(root, root.left, path, arr);
        if (!matched) {
            if (added) {
                path.remove(path.size() - 1);
            }
        }
        if (!matched) {
            added = gatherAllPaths(root, root.right, path, arr);
            if (added) {
                path.remove(path.size() - 1);
            }
        }

        return true;
    }

    private boolean isMatched(int[] arr, List<Integer> p) {
        boolean matched;
        if (p.size() == arr.length) {
            matched = true;
            for (int i = 0; i < arr.length; i++) {
                if (p.get(i) != arr[i]) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return true;
            }

        }
        return false;
    }

    // Valid Sequence from Root to Leaves Path in a Binary Tree
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (arr == null || arr.length == 0)
            return false;

        gatherAllPaths(root, root, new LinkedList<>(), arr);

        return matched;
    }
}
