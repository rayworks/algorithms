package org.sean.design;

import java.util.*;

/***
 * 1396. Design Underground System
 */
class UndergroundSystem {
    // <S:E, [timeSpent]>
    private final Map<String, List<Integer>> routineMap;

    // <id,  <checkedIn, time>>
    private final Map<Integer, String[]> checkinMap;

    public UndergroundSystem() {
        routineMap = new HashMap<>();
        checkinMap = new HashMap<>();
    }

    /***
     * Checks in at the station stationName at time t.
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkIn(int id, String stationName, int t) {
        checkinMap.put(id, new String[]{stationName, String.valueOf(t)});
    }

    /***
     * Checks out from the station stationName at time t.
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkOut(int id, String stationName, int t) {
        String[] pair = checkinMap.get(id);
        if (pair != null) {
            String key = pair[0] + ":" + stationName;
            List<Integer> lst = routineMap.getOrDefault(key, new ArrayList<>());
            lst.add(t - Integer.parseInt(pair[1]));
            routineMap.put(key, lst);

            checkinMap.remove(id);
        }
    }

    /***
     * Returns the average time it takes to travel from startStation to endStation.
     *
     * The average time is computed from all the previous traveling times from startStation to endStation that happened
     * directly, meaning a check in at startStation followed by a checkout from endStation.
     *
     * @param startStation
     * @param endStation
     * @return
     */
    public double getAverageTime(String startStation, String endStation) {
        List<Integer> lst = routineMap.getOrDefault(startStation + ":" + endStation, new ArrayList<>());

        if (lst.size() == 0)
            return 0;

        int sum = lst.stream().mapToInt(value -> value).sum();
        return sum * 1.0 / lst.size();
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
