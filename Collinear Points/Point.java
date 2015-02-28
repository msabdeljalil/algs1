/*************************************************************************
 * Name: Mohammad Abdeljalil
 * Email: msabdeljalil@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private class BySlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) { return p1.compareTo(p2); }
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double temp = (double)(that.y - this.y) / (double)(that.x - this.x);
        if (temp == Double.NEGATIVE_INFINITY) { return Double.POSITIVE_INFINITY;}
        if (temp == -0.0) { return 0.0;}
        if (temp == Double.NaN) { return Double.NEGATIVE_INFINITY;}
        return temp;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that.y > this.y) { return -1;}
        if (that.y < this.y) { return +1;}
        if (that.x > this.x) { return -1;}
        if (that.x < this.x) { return +1;}
        return 0;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,10);
        Point p3 = new Point(0,10);
        Point p4 = new Point(10,0);


        double temp1 = p2.slopeTo(p1);
        double temp2 = p3.slopeTo(p1);
        double temp3 = p4.slopeTo(p1);
        double temp4 = p1.slopeTo(p1);
        StdOut.println("1: " +temp1);
        StdOut.println("2: " +temp2);
        StdOut.println("3: " +temp3);
        StdOut.println("4: " +temp4);
    }
}

