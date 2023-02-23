package org.sean.divide_conquer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestPair {
    public static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Point {

        double x, y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public double distanceTo(Point other) {
            return Math.sqrt(Math.pow(Math.abs(x - other.x), 2) + Math.pow(Math.abs(y - other.y), 2));
        }
    }

    // Time : O(N*lgN)
    private Pair<Point, Point> closestPair(Point[] pointsX, Point[] pointsY, int low, int high) {
        if (high <= low) return null;
        int mid = low + (high - low) / 2;

        Point median = pointsX[mid];

        // find the closest points on the left side
        Pair<Point, Point> left = closestPair(pointsX, pointsY, low, mid);
        double leftMinDist = Double.POSITIVE_INFINITY;
        if (left != null) {
            leftMinDist = left.first.distanceTo(left.second);
        }

        // find the closest points on the right side
        Pair<Point, Point> right = closestPair(pointsX, pointsY, mid + 1, high);
        double rightMinDist = Double.POSITIVE_INFINITY;
        if (right != null) {
            rightMinDist = right.first.distanceTo(right.second);
        }

        double minDist = Math.min(leftMinDist, rightMinDist);

        Pair<Point, Point> cloestPair = combine(pointsY, median, minDist);
        if (cloestPair == null) {
            if (leftMinDist < rightMinDist) {
                return left;
            } else {
                return right;
            }
        }
        return cloestPair;
    }

    private Pair<Point, Point> combine(Point[] pointsY, Point median, double minDist) {
        // Filter out the points on the x-axis
        List<Point> candidates = Arrays.stream(pointsY)
                .filter(p -> Math.abs(p.x - median.x) <= minDist).collect(Collectors.toList());

        Pair<Point, Point> pair = null;
        double closestDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < candidates.size() - 1; i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                Point p1 = candidates.get(i);
                Point p2 = candidates.get(j);

                double dist = p1.distanceTo(p2);
                if (dist < closestDist) {
                    pair = new Pair<>(p1, p2);
                    closestDist = dist;
                }
            }
        }
        return pair;
    }

    public Pair<Point, Point> closestPair(Point[] points) {
        Point[] pointsX = points.clone();
        Arrays.sort(pointsX, Comparator.comparingDouble(o -> o.x));

        Point[] pointsY = points.clone();
        Arrays.sort(pointsY, Comparator.comparingDouble(o -> o.y));

        return closestPair(pointsX, pointsY, 0, points.length - 1);
    }

    public static void main(String[] args) {
        ClosestPair pair = new ClosestPair();
        Point[] P = new Point[5];

        P[0] = new Point();
        P[0].x = 2;
        P[0].y = 3;

        P[1] = new Point();
        P[1].x = 12;
        P[1].y = 30;

        P[2] = new Point();
        P[2].x = 40;
        P[2].y = 50;

        P[3] = new Point();
        P[3].x = 5;
        P[3].y = 1;

        P[4] = new Point();
        P[4].x = 12;
        P[4].y = 10;

        Pair<Point, Point> res = pair.closestPair(P);
        System.out.println("Closest pair : from " + res.first + " to " + res.second);
    }

}
