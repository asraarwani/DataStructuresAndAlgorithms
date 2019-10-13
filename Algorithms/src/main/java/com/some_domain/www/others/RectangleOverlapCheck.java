package com.some_domain.www.others;

/**
 * @author : waniasra
 * @date : 10/11/2019 6:10 PM
 * This program demonstrates how to check if two rectangles overlap each other
 * Steps:
 * The two given rectangles won't overlap if either of the below conditions is true:
 * <p>
 * One of the two rectangles is above the top edge of the other rectangle
 * One of the two rectangles is on the left side of the left edge of the other rectangle
 */
//Link : https://www.baeldung.com/java-check-if-two-rectangles-overlap
public class RectangleOverlapCheck {

    public static void main(String[] args) {

        Rectangle rectangleOne = new Rectangle(
                new Point(0.0, 0.0), new Point(2.0, 2.0));

        Rectangle rectangleTwo = new Rectangle(
                new Point(1.0, 1.0), new Point(3.0, 3.0));

        boolean isOverlapping = rectangleOne.isOverlappingOtherRectangle(rectangleTwo);
        System.out.println("Rectangle 1  is overlapping  Rectangle 2 : " + isOverlapping);
        System.out.println("Rectangle 1 " + rectangleOne);
        System.out.println("Rectangle 2 " + rectangleTwo);
    }

    private static class Rectangle {

        private Point bottomLeftPoint;
        private Point topRightPoint;


        public Rectangle(Point bottomLeftPoint, Point topRightPoint) {
            this.bottomLeftPoint = bottomLeftPoint;
            this.topRightPoint = topRightPoint;
        }

        public boolean isOverlappingOtherRectangle(Rectangle otherRectangle) {

            if (this.getTopRightPoint().getY() < otherRectangle.getBottomLeftPoint().getY() ||
                    this.getBottomLeftPoint().getY() > otherRectangle.getTopRightPoint().getY()) {
                return false;
            }

            if (this.getTopRightPoint().getX() < otherRectangle.getBottomLeftPoint().getX() ||
                    this.getBottomLeftPoint().getX() > otherRectangle.getTopRightPoint().getX()) {
                return false;
            }
            return true;
        }

        public Point getTopRightPoint() {
            return topRightPoint;
        }

        public void setTopRightPoint(Point topRightPoint) {
            this.topRightPoint = topRightPoint;
        }

        public Point getBottomLeftPoint() {
            return bottomLeftPoint;
        }

        public void setBottomLeftPoint(Point bottomLeftPoint) {
            this.bottomLeftPoint = bottomLeftPoint;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "topRightPoint=" + topRightPoint +
                    ", bottomLeftPoint=" + bottomLeftPoint +
                    '}';
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
