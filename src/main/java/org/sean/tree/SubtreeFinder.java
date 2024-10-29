package org.sean.tree;

import java.util.*;

/***
 * 3331. Find Subtree Sizes After Changes
 */
public class SubtreeFinder {

    public int[] findSubtreeSizes(int[] parent, String s) {
        // modify the trees along the way
        // count the size of subtrees

        int n = s.length();
        List<TNode> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new TNode(i, s.charAt(i)));
        }

        for (int i = 0; i < n; i++) {
            int pIdx = parent[i];
            if (pIdx >= 0) {
                nodes.get(pIdx).children.put(nodes.get(i).ord, nodes.get(i));
            }
        }

        int[] indexes = new int[26];
        Arrays.fill(indexes, -1);

        dfsTNode(nodes, parent, nodes.get(0), indexes);

        int[] res = new int[n];
        count(nodes.get(0), res);
        return res;
    }

    private int count(TNode node, int[] res) {
        if (node == null) return 0;
        if (node.children.isEmpty()) {
            res[node.ord] = 1;
            return 1;
        }
        int sum = 1;
        for (TNode child : node.children.values()) {
            sum += count(child, res);
        }
        res[node.ord] = sum;
        return sum;
    }

    private void dfsTNode(List<TNode> nodes, int[] parent, TNode node, int[] alphaIdx) {
        if (node == null) return;
        char currCh = node.val;
        int idx = node.ord;

        int charPos = currCh - 'a';
        int prev = alphaIdx[charPos];

        if (prev >= 0) {
            int p = parent[node.ord];
            if (p != prev) { // rebind the nodes if not its direct parent
                nodes.get(p).children.remove(node.ord);
                nodes.get(prev).children.put(node.ord, node);
            }
        }

        alphaIdx[charPos] = idx;

        List<TNode> values = new ArrayList<>();
        values.addAll(node.children.values());
        for (TNode child : values) {
            if (child == null) continue;
            dfsTNode(nodes, parent, child, alphaIdx);
        }

        alphaIdx[charPos] = prev;
    }

    static class TNode {
        int ord;
        char val;
        Map<Integer, TNode> children = new HashMap<>();

        public TNode(int ord, char val) {
            this.ord = ord;
            this.val = val;
        }
    }
}
