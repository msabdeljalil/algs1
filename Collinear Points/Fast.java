import java.util.ArrayList;
import java.util.Collections;

public class Fast {
    public Fast() {}

    private static void drawAndPrintConnectedPoints(Point p0, Point p1, Point p2, Point p3) {
        StdOut.println(p0 + " -> " + p1 + " -> " + p2 + " -> " + p3);
        p0.drawTo(p3);
    }

   public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();

        ArrayList<Point> points = new ArrayList<Point>();
        while (!in.isEmpty()) {
          points.add(new Point(in.readInt(), in.readInt()));
        }
        Collections.sort(points, Point.SLOPE_ORDER);

        p = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point q         = points.get(i);
            int pq_slope = p.slopeTo(q);

        }

    // 1. Consider p as the origin
    // 2. For each other point q, 
        // determine the slope it makes with p
    // 3. Sort the points according to the slopes they makes with p
    // 4. If any of 3+ adjacent points in the sorted order have slopes equal to p
        // then these points, together with p, are collinear
   }

}
