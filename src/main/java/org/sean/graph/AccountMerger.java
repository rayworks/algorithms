package org.sean.graph;

import java.util.*;

/***
 * 721. Accounts Merge
 */
public class AccountMerger {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // create the graph
        // merge via dfs
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToNameMap = new HashMap<>();

        for (List<String> list : accounts) {
            int sz = list.size();
            String name = list.get(0);

            for (int i = 1; i < sz; i++) {
                String email = list.get(i);
                emailToNameMap.put(email, name);
            }

            // grouped emails
            String headNode = list.get(1); // first email for current account
            for (int i = 2; i < sz; i++) {
                String email = list.get(i);

                List<String> stringList = graph.getOrDefault(headNode, new ArrayList<>());
                if (stringList.isEmpty()) {
                    stringList.add(email);
                    graph.put(headNode, stringList);
                } else {
                    stringList.add(email);
                }

                List<String> reversedList = graph.getOrDefault(email, new ArrayList<>());
                if (reversedList.isEmpty()) {
                    reversedList.add(headNode);
                    graph.put(email, reversedList);
                } else {
                    reversedList.add(headNode);
                }
            }
        }

        List<List<String>> out = new ArrayList<>();

        for (List<String> account : accounts) {
            String accountFirstEmail = account.get(1);

            ArrayList<String> list = new ArrayList<>();
            list.add(emailToNameMap.get(accountFirstEmail));

            dfs(graph, accountFirstEmail, list);

            if (list.size() > 1) {
                Collections.sort(list.subList(1, list.size()));

                out.add(list);
            }
        }

        return out;
    }

    private final Set<String> visitedEmailSet = new HashSet<>();

    private void dfs(Map<String, List<String>> g, String email, List<String> group) {
        if (visitedEmailSet.contains(email))
            return;

        group.add(email);
        visitedEmailSet.add(email);

        for (String edge : g.getOrDefault(email, new ArrayList<>())) {
            if (!visitedEmailSet.contains(edge)) {
                dfs(g, edge, group);
            }
        }
    }
}
