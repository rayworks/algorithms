package org.sean.tree;

import java.util.*;

/**
 * Created by Sean on 11/13/16.
 * <p/>
 * Update from CTCI 5e
 */
public class BinarySearchTreeChecker {

    public boolean isBST(TreeNode tree) {
        return isBSTree(tree, null, null);
    }

    public boolean isBSTree(TreeNode tree, Integer min, Integer max) {
        if (tree == null) {
            return true;
        }

        if ((min != null && tree.val < min) || (max != null && tree.val >= max)) {
            return false;
        }

        return isBSTree(tree.left, min, tree.val) && isBSTree(tree.right, tree.val, max);

    }

    private LinkedList<Integer> elems = new LinkedList<>();

    private void inorder(TreeNode node) {
        if (node == null)
            return;

        if (node.left != null)
            inorder(node.left);

        elems.add(node.val);

        if (node.right != null)
            inorder(node.right);

    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null)
            return 0;

        TreeNode node = root;
        inorder(node);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < elems.size(); i++) {
            minDiff = Math.min(Math.abs(elems.get(i) - elems.get(i - 1)), minDiff);
        }

        return minDiff;
    }

    /***
     *
     * @param s source word
     * @param d dictionary
     * @return the longest word with letter alpha order, empty string if not found.
     */
    public String findLongestWord(String s, List<String> d) {
        if (s == null || d == null || d.size() == 0) {
            return "";
        }

        Iterator<String> iterator = d.iterator();
        while (iterator.hasNext()) {
            boolean valid = true;
            String elem = iterator.next();
            for (int i = 0; i < elem.length(); i++) {
                if (s.indexOf(elem.charAt(i)) == -1) {// not found
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                iterator.remove();
            }
        }

        //longest words && letter alpha order reverse
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int length1 = o1.length();
                int length2 = o2.length();
                if (o1.equals(o2)) {
                    return 0;
                }

                boolean result = length1 > length2 ||
                        (length1 == length2 && (o1.compareTo(o2) < 0));


                if (result)
                    return 1;
                else
                    return -1;
            }
        });

        if (d.size() > 0)
            return d.get(d.size() - 1);
        else
            return "";
    }
}
