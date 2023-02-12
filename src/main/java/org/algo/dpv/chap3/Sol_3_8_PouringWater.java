package org.algo.dpv.chap3;

import java.util.*;

public class Sol_3_8_PouringWater {

    private final List<String> results = new ArrayList<>();

    private final Set<String> historicalStates = new HashSet<>();

    // Pouring water
    public void pour() {
        int[][] capacityAndValues = new int[][]{
                {10, 0},
                {7, 7},
                {4, 4}
//                {8,8},
//                {5,0},
//                {3,0}
        };

        ArrayList<ContainerNode> nodes = new ArrayList<>();
        for (int i = 0; i < capacityAndValues.length; i++) {
            nodes.add(new ContainerNode(capacityAndValues[i][0], capacityAndValues[i][1], i));
        }

        for (int m = 0; m < nodes.size(); m++) {
            for (int n = 0; n < nodes.size(); n++) {
                if (m != n) {
                    nodes.get(m).adjacents.add(nodes.get(n));
                }
            }
        }

        // target states of containers:  <Order, Val>
        Map<Integer, Integer> targets = new HashMap<>();
        targets.put(2, 2);
        targets.put(1, 2);

        // Brute force
        calcState(new ArrayDeque<>(), nodes, targets);

        if (!results.isEmpty()) {
            System.out.printf("All %d steps are : \n", results.size());
            for (String s : results) {
                System.out.println(s);
            }
        } else {
            System.out.println("No solution can be found");
        }
    }

    private void calcState(Deque<String> ans, List<ContainerNode> nodes, Map<Integer, Integer> targets) {
        int len = nodes.size();
        System.out.printf(">>> containers : %d, %d, %d\n", nodes.get(0).val, nodes.get(1).val, nodes.get(2).val);


        String currState = getContainersState(nodes);
        if (historicalStates.contains(currState)) // pruning : retreat from the same state
            return;

        historicalStates.add(currState);

//        if ( nodes.get(1).val == 4 && nodes.get(2).val == 4 ||
//                nodes.get(0).val == 4 && nodes.get(1).val == 4 ||
//                nodes.get(0).val == 4 && nodes.get(2).val == 4) {
//            results.add(Arrays.toString(ans.toArray()));
//            return;
//        }

        for (ContainerNode node : nodes) {
            if (targets.get(node.order) != null) {
                if (targets.get(node.order) == node.val) {
                    System.out.printf(">>> 🍺🍺🍺Found value %d in container %d-pint\n", node.val, node.capacity);
                    results.add(Arrays.toString(ans.toArray()));
                    return;
                }
            }
        }

        for (int j = 0; j < len; j++) {
            for (int k = 0; k < len; k++) {
                if (k == j) // don't touch the same container
                    continue;

                ContainerNode s = nodes.get(j);
                ContainerNode t = nodes.get(k);
                if (s.val > 0 && (t.capacity - t.val > 0)) {
                    int m = Math.min(s.val, t.capacity - t.val);

                    s.val -= m;
                    t.val += m;

                    String nState = getContainersState(nodes);
                    if (!historicalStates.contains(nState)) {
                        ans.add(nState);

                        //System.out.printf("+ MOV from container %d to container %d with %d water\n", s.capacity, t.capacity, m);
                        calcState(ans, nodes, targets);

                        // undo :try another state
                        historicalStates.remove(nState);
                        ans.removeLast();
                        //System.out.printf("- RESTORE from container %d to container %d with %d water\n", s.capacity, t.capacity, m);
                    }

                    t.val -= m;
                    s.val += m;
                }
            }
        }


    }

    private static String getContainersState(List<ContainerNode> nodes) {
        return String.format("%d-%d-%d", nodes.get(0).val, nodes.get(1).val, nodes.get(2).val);
    }

    public static void main(String[] args) {
        Sol_3_8_PouringWater pouringWater = new Sol_3_8_PouringWater();
        pouringWater.pour();
    }


    static class ContainerNode {
        int capacity;
        int val;
        int order;

        List<ContainerNode> adjacents = new ArrayList<>();

        public ContainerNode(int capacity, int val, int order) {
            this.capacity = capacity;
            this.val = val;
            this.order = order;
        }
    }

}
