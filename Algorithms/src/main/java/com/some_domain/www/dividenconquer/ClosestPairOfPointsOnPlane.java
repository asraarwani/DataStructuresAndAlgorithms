package com.some_domain.www.dividenconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author : waniasra
 * @date : 10/10/2019 11:09 AM
 * This class demonstrates how to find the closest pair of points on plane both using brute force and divide and conquer technique
 */
public class ClosestPairOfPointsOnPlane {

    public static void main(String[] args) {

        Random randomNumberGenerator = new Random();
        List<Point> listOfPoints = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            listOfPoints.add(new Point(randomNumberGenerator.nextDouble(), randomNumberGenerator.nextDouble()));
        }

        long startingTime = System.currentTimeMillis();
        Pair closestPair = findClosestPairUsingBruteForceApproach(listOfPoints);
        System.out.println("Closest pair of points using divide  and conquer is " + closestPair);
        System.out.println("Total time taken  is " + (System.currentTimeMillis() - startingTime) + " ms");
        System.out.println("Time complexity is N^2");

        startingTime = System.currentTimeMillis();
        closestPair = findClosestPairUsingDivideAndConquer(listOfPoints);
        System.out.println("Closest pair of points using brute force is " + closestPair);
        System.out.println("Total time taken  is " + (System.currentTimeMillis() - startingTime) + " ms");
        System.out.println("Time complexity is NLogN");
    }

    public static Pair findClosestPairUsingDivideAndConquer(List<Point> pointList) {
        List<Point> sortedByX = new ArrayList<>(pointList);
        sortPointsByXCoordinate(sortedByX);

        List<Point> sortedByY = new ArrayList<>(pointList);
        sortPointsByYCoordinate(sortedByY);

        return divideAndConquer(sortedByX, sortedByY);
    }

    private static Pair divideAndConquer(List<Point> sortedByX, List<Point> sortedByY) {
        int numberOfPoints = sortedByX.size();
        if (numberOfPoints <= 3) {
            return findClosestPairUsingBruteForceApproach(sortedByX);
        }

        int dividingIndex = (numberOfPoints) / 2;
        List<Point> leftOfCenter = sortedByX.subList(0, dividingIndex);
        List<Point> rightOfCenter = sortedByX.subList(dividingIndex, numberOfPoints);

        List<Point> temporaryList = new ArrayList<>(leftOfCenter);
        sortPointsByYCoordinate(temporaryList);
        Pair closestPair = divideAndConquer(leftOfCenter, temporaryList);

        temporaryList.clear();
        temporaryList.addAll(rightOfCenter);
        sortPointsByYCoordinate(temporaryList);
        Pair closestRightPair = divideAndConquer(rightOfCenter, temporaryList);

        //Pick the closest pair of the two
        if (closestRightPair.getDistance() < closestPair.getDistance()) {
            closestPair = closestRightPair;
        }

        //Look for the pairs of points having points on both the sides
        temporaryList.clear();
        double shortestDistance = closestPair.getDistance();
        double centerX = rightOfCenter.get(0).getX();
        for (Point point : sortedByY) {
            if (Math.abs(centerX - point.getX()) < shortestDistance) {
                temporaryList.add(point);
            }
        }

        for (int i = 0; i < temporaryList.size(); i++) {
            Point firstPoint = temporaryList.get(i);
            for (int j = i + 1; j < temporaryList.size(); j++) {
                Point secondPoint = temporaryList.get(j);
                if (Math.abs(secondPoint.getY() - firstPoint.getY()) >= shortestDistance) {
                    break;
                }
                double distance = calculateDistanceBetweenTwoPoints(firstPoint, secondPoint);
                if (distance < closestPair.getDistance()) {
                    closestPair.updatePair(firstPoint, secondPoint, distance);
                    shortestDistance = distance;
                }
            }
        }
        return closestPair;
    }


    public static Pair findClosestPairUsingBruteForceApproach(List<Point> pointList) {
        int numberOfPoints = pointList.size();
        if (numberOfPoints < 2)
            return null;
        Pair closestPair = new Pair(pointList.get(0), pointList.get(1));
        if (numberOfPoints > 3) {
            for (int i = 0; i < pointList.size(); i++) {
                Point firstPoint = pointList.get(i);
                for (int j = i + 1; j < pointList.size(); j++) {
                    Point secondPoint = pointList.get(j);
                    double distance = calculateDistanceBetweenTwoPoints(firstPoint, secondPoint);
                    if (distance < closestPair.getDistance()) {
                        closestPair.updatePair(firstPoint, secondPoint, distance);
                    }
                }
            }
        }
        return closestPair;
    }

    private static void sortPointsByYCoordinate(List<Point> pointList) {
        Collections.sort(pointList, (firstPoint, secondPoint) -> {
            if (firstPoint.getY() > secondPoint.getY())
                return 1;
            else if (firstPoint.getY() < secondPoint.getY())
                return -1;
            else
                return 0;
        });
    }

    private static void sortPointsByXCoordinate(List<Point> pointList) {
        Collections.sort(pointList, (firstPoint, secondPoint) -> {
            if (firstPoint.getX() > secondPoint.getX())
                return 1;
            else if (firstPoint.getX() < secondPoint.getX())
                return -1;
            else
                return 0;
        });
    }

    private static double calculateDistanceBetweenTwoPoints(Point firstPoint, Point secondPoint) {
        double differenceX = secondPoint.getX() - firstPoint.getX();
        double differenceY = secondPoint.getY() - firstPoint.getY();
        return Math.hypot(differenceX, differenceY);
    }

    private static class Pair {

        private Point firstPoint;
        private Point secondPoint;
        private double distance;

        public Pair(Point firstPoint, Point secondPoint) {
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;
            this.distance = getDistanceBetweenTwoPoints(firstPoint, secondPoint);
        }

        public Point getFirstPoint() {
            return firstPoint;
        }

        public void setFirstPoint(Point firstPoint) {
            this.firstPoint = firstPoint;
        }

        public Point getSecondPoint() {
            return secondPoint;
        }

        public void setSecondPoint(Point secondPoint) {
            this.secondPoint = secondPoint;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "firstPoint=" + firstPoint +
                    ", secondPoint=" + secondPoint +
                    ", distance=" + distance +
                    '}';
        }

        public double getDistanceBetweenTwoPoints(Point firstPoint, Point secondPoint) {
            return calculateDistanceBetweenTwoPoints(firstPoint, secondPoint);
        }

        public void updatePair(Point firstPoint, Point secondPoint, double distance) {
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;
            this.distance = distance;
        }
    }

    private static class Point {

        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
