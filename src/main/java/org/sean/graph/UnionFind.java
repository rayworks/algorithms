package org.sean.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Case Study: Union-Find
// https://algs4.cs.princeton.edu/15uf/
public class UnionFind {
    private int components;
    private final int[] parents;
    private final int[] sz;

    public UnionFind(int n) {
        components = n;

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i; // self
        }

        sz = new int[n];
        Arrays.fill(sz, 1);
    }

    private int topParent(int p) {
        int curr = p;
        while (parents[curr] != curr) {
            curr = parents[curr];
        }
        return curr;
    }

    public void union(int p, int q) {
        int pt1 = topParent(p);
        int pt2 = topParent(q);
        if (pt1 != pt2) {
            // weighted quick union:
            // connect the smaller tree to the larger
            if (sz[pt1] > sz[pt2]) {
                parents[pt2] = pt1;
                sz[pt1] += sz[pt2];
            } else {
                parents[pt1] = pt2;
                sz[pt2] += sz[pt1];
            }

            --components;
        }
    }

    public boolean isConnected(int p, int q) {
        return topParent(p) == topParent(q);
    }

    public int count() {
        return components;
    }

    public static void main(String[] args) {
        List<int[]> arr = new LinkedList<>();
        arr.add(new int[] {4, 3});
        arr.add(new int[] {3, 8});
        arr.add(new int[] {6, 5});
        arr.add(new int[] {9, 4});
        arr.add(new int[] {2, 1});
        arr.add(new int[] {5, 0});
        arr.add(new int[] {7, 2});
        arr.add(new int[] {6, 1});

        UnionFind unionFind = new UnionFind(10);
        for (int[] pair : arr) {
            unionFind.union(pair[0], pair[1]);
        }

        System.out.println("Components : " + unionFind.count());
    }
}
