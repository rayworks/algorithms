package org.sean.tree;

/**
 * Created by Sean on 11/13/16.
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    private boolean none;

    public boolean isNone() {
        return none;
    }

    public TreeNode(boolean none) {
        this.none = none;
    }

    public String getValueStr() {
        if (none)
            return "null";
        else
            return String.valueOf(val);
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public static class NoneNode extends TreeNode {
        public NoneNode() {
            super(true);
        }
    }
}
