package org.sean.backtracking;

import java.util.*;

/***
 * 332. Reconstruct Itinerary
 */
public class ItineraryBuilder {
    private int ticketNum = 0;

    // Note:
    //
    // Using DS Map<String, List<String>> and trying to list all the possibilities will be Time Out !
    //
    private Map<String, Map<String, Integer>> map = new HashMap<>();

    private List<String> out = new ArrayList<>();

    boolean backtrack(String start, LinkedList<String> itinerary) {
        // number of stops = number of flights + 1
        if (itinerary.size() == ticketNum + 1) {
            out = new ArrayList<>(itinerary);
            return true;
        }

        Map<String, Integer> endCntMap = map.get(start);
        if (endCntMap == null)
            return false;

        for (Map.Entry<String, Integer> next : map.get(start).entrySet()) {
            int count = next.getValue();
            if (count > 0) {
                itinerary.add(next.getKey());
                next.setValue(count - 1);

                if (backtrack(next.getKey(), itinerary)) return true;

                itinerary.removeLast();
                next.setValue(count);
            }
        }

        return false;
    }


    public List<String> findItinerary(List<List<String>> tickets) {
        ticketNum = tickets.size();

        // Map<String, Map<String, Integer>> map
        for (List<String> ticket : tickets) { // ticket size : 2
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (map.containsKey(from)) {
                Integer cnt = map.get(from).getOrDefault(to, 0);
                map.get(from).put(to, cnt + 1);
            } else {
                // make the 'next' sorted, so the first succeed Itinerary is the result
                Map<String, Integer> inner = new TreeMap<>();
                inner.put(to, 1);
                map.put(from, inner);
            }
        }

        LinkedList<String> itinerary = new LinkedList<>();
        itinerary.add("JFK");

        backtrack("JFK", itinerary);

        //System.out.printf(">>> %s", String.join(",", out));
        return out;
    }
}
