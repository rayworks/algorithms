package org.sean.graph;

import java.util.*;

/***
 * 1202. Smallest String With Swaps
 */
public class SmallStrWithSwaps {

    private Map<Integer, List<Integer>> graph;
    private ArrayList<Integer> vertexes;
    private boolean[] visited;

    // Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    // Output: "abcd"
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0)
            return s;

        // construct the graph
        graph = new HashMap<>();
        for (List<Integer> edge : pairs) {
            Integer from = edge.get(0);
            Integer to = edge.get(1);

            List<Integer> list = graph.getOrDefault(from, new ArrayList<>());
            list.add(to);
            graph.put(from, list);

            List<Integer> backList = graph.getOrDefault(to, new ArrayList<>());
            backList.add(from);
            graph.put(to, backList);
        }

        List<Set<Integer>> connectedComp = new ArrayList<>();

        vertexes = new ArrayList<>(graph.keySet());
        visited = new boolean[s.length()];

        // gather the CCs
        for (int v : vertexes) {
            TreeSet<Integer> compSet = new TreeSet<>();
            dfs(v, graph, compSet);

            if (!compSet.isEmpty()) {
                connectedComp.add(compSet);
            }
        }

        char[] dst = new char[s.length()];
        Arrays.fill(dst, ' ');
        for (Set<Integer> cc : connectedComp) {
            int[] array = cc.stream().mapToInt(value -> value).toArray();
            System.out.println(Arrays.toString(array));

            char[] grpChars = new char[cc.size()];
            int index = 0;
            for (int i : cc) {
                grpChars[index++] = s.charAt(i);
            }
            Arrays.sort(grpChars);
            System.out.println(Arrays.toString(grpChars));

            index = 0;
            for (int j : cc) {
                dst[j] = grpChars[index++];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (dst[i] == ' ')
                dst[i] = s.charAt(i);
        }

        return new String(dst);
    }

    private void dfs(int start, Map<Integer, List<Integer>> graph, Set<Integer> out) {
        if (!visited[start]) {
            visited[start] = true;
            out.add(start);

            List<Integer> edges = graph.get(start);
            if (edges != null) {
                for (int e : edges) {
                    dfs(e, graph, out);
                }
            }
        }
    }
}
