package org.sean.graph;

import java.util.*;

/***
 * 54. [21] The 49 postal codes in graph (17) are AL, AR, AZ, CA, CO, CT, DC, DE, FL, GA,
 * IA, ID, IL, IN, KS, KY, LA, MA, MD, ME, MI, MN, MO, MS, MT, NC, ND, NE, NH, NJ, NM, NV,
 * NY, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT, VA, VT, WA, WI, WV, WY, in alphabetical order.
 * <p>
 * a) Suppose we consider two states to be adjacent if their postal codes agree in one
 * place(namely AL−−−AR−−−OR−−−OH,etc.). What are the components of this graph?
 * b) Now form a directed graph with XY −−→YZ (for example, AL −−→LA −−→AR, etc.).
 * What are the strongly connected components of this digraph?
 * c) The United States has additional postal codes AA, AE, AK, AP, AS, FM, GU, HI, MH,
 * MP, PW, PR, VI, besides those in (17). Reconsider question (b), using all 62 codes.
 * </p>
 */
public class PostalCode {

    public static void main(String[] args) {
        PostalCode obj = new PostalCode();

        String[] codes = {
                "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA",
                "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME",
                "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ",
                "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD",
                "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY",
                // extra
//                "AA", "AE", "AK", "AP", "AS", "FM", "GU", "HI", "MH",
//                "MP", "PW", "PR", "VI"
        };

        // a)
        obj.components(codes);

        // b), c)
        // direction rule : XY → YZ
        obj.getSCCsOfGraph(codes);
    }

    public void components(String[] codes) {
        Set<String> visited = new HashSet<>();
        for (String code : codes) {
            if (visited.contains(code)) continue;

            ArrayList<String> outCodes = new ArrayList<>();
            dfs(codes, code, visited, outCodes);
            System.out.println("Simple Component : " + Arrays.toString(outCodes.toArray()));
        }
    }

    private void dfs(String[] codes, String code, Set<String> visited, ArrayList<String> out) {
        if (visited.contains(code)) return;

        visited.add(code);
        out.add(code);

        for (String to : codes) {
            if (!visited.contains(to)) {
                if (to.charAt(0) == code.charAt(0) || to.charAt(0) == code.charAt(1) ||
                        to.charAt(1) == code.charAt(0) || to.charAt(1) == code.charAt(1)) {
                    dfs(codes, to, visited, out);
                }
            }
        }
    }


    // Kosaraju-Sharir
    // Find the SCCs for the directed graph
    public void getSCCsOfGraph(String[] codes) {
        // SCCs
        Map<Character, Set<String>> graph = new HashMap<>();
        for (String code : codes) {
            if (graph.containsKey(code.charAt(0))) {
                graph.get(code.charAt(0)).add(code);
            } else {
                Set<String> set = new HashSet<>();
                set.add(code);
                graph.put(code.charAt(0), set);
            }
        }

        // direction rule : XY → YZ
        Map<String, Set<String>> map = new HashMap<>();
        for (String code : codes) {
            char prefix = code.charAt(1);
            if (graph.containsKey(prefix)) {
                map.put(code, graph.get(prefix));
            }
        }

        // transpose graph
        Map<String, Set<String>> reversedMap = new HashMap<>();
        for (String key : map.keySet()) {
            for (String e : map.get(key)) {
                if (reversedMap.containsKey(e)) {
                    reversedMap.get(e).add(key);
                } else {
                    Set<String> set = new HashSet<>();
                    set.add(key);
                    reversedMap.put(e, set);
                }
            }
        }

        // iterate the vertexes in DFS and collect them with post order
        Set<String> visited = new HashSet<>();
        ArrayList<String> outVertexes = new ArrayList<>();
        for (String code : codes) {
            if (!visited.contains(code)) {
                collect(map, visited, code, outVertexes);
            }
        }

        // collect the SCCs
        visited.clear();
        ArrayList<List<String>> outLists = new ArrayList<>();
        for (int i = outVertexes.size() - 1; i >= 0; i--) {
            String vertex = outVertexes.get(i);
            if (visited.contains(vertex)) continue;

            ArrayList<String> scc = new ArrayList<>();
            collect(reversedMap, visited, vertex, scc);

            outLists.add(scc);
        }

        for (List<String> out : outLists) {
            System.out.println("SCC : " + Arrays.toString(out.toArray()));
        }
    }

    private void collect(Map<String, Set<String>> graph, Set<String> visited,
                         String code, List<String> outCodes) {
        if (visited.contains(code)) {
            return;
        }
        visited.add(code);
        for (String to : graph.getOrDefault(code, Collections.emptySet())) {
            collect(graph, visited, to, outCodes);
        }
        outCodes.add(code);
    }
}