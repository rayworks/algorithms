package org.sean.graph;

/***
 * 785. Is Graph Bipartite?
 */
public class BipartiteChecker {
    private boolean[] visited;
    private boolean[] colored;

    private boolean bipartite;
    private boolean finished;

    private void dfs(int[][] graph, int pos) {
        if (!finished && !visited[pos]) {
            visited[pos] = true;
            for (int v : graph[pos]) {
                if (!visited[v]) {
                    colored[v] = !colored[pos];
                    dfs(graph, v);
                } else {
                    if (colored[v] == colored[pos]) {
                        finished = true;
                        bipartite = false;
                    }
                }
            }
        }
    }

    public boolean isBipartite(int[][] graph) {
        int vertexCnt = graph.length;

        visited = new boolean[vertexCnt];
        colored = new boolean[vertexCnt];

        bipartite = true;
        finished = false;

        for (int i = 0; i < graph.length; i++) {
            if (finished)
                break;

            if (!visited[i]) {
                dfs(graph, i);
            }
        }


        return bipartite;
    }
}
