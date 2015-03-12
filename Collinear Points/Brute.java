import java.util.ArrayList;
import java.util.Collections;

public class Brute {
    public Brute() {}

    public static boolean onSameLine(Point p1, Point p2, Point p3, Point p4) {
        boolean pq_pr = p1.slopeTo(p2) == p1.slopeTo(p4);
        boolean pq_ps = p1.slopeTo(p2) == p1.slopeTo(p3);
        boolean pr_ps = p1.slopeTo(p4) == p1.slopeTo(p3);
        return pq_pr && pq_ps && pr_ps;
    }

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
        Collections.sort(points, Point.COORD_ORDER);

        // int F = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i+1; j < points.size(); j++) {
                for (int k = j+1; k < points.size(); k++) {
                    for (int l = k+1; l < points.size(); l++) {
                        // StdOut.println("::"+i+" "+j+" "+k+" "+l+" F: "+ ++F);
                        if (Brute.onSameLine(points.get(i), points.get(j), points.get(k), points.get(l) )) {
                          drawAndPrintConnectedPoints( points.get(i), points.get(j), points.get(k), points.get(l) );
                        }
                    }
                }
            }
        }

    } // main()
}
