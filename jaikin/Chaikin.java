package jaikin;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Chaikin {
    public static List<Point> chaikinStep(List<Point> points) {
        if (points.size() < 2) {
            return points;
        }
        List<Point> newPoints = new ArrayList<>();
        newPoints.add(points.get(0));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            Point q = new Point(
                    (int) Math.round(0.75 * p1.x + 0.25 * p2.x),
                    (int) Math.round(0.75 * p1.y + 0.25 * p2.y));
            Point r = new Point(
                    (int) Math.round(0.25 * p1.x + 0.75 * p2.x),
                    (int) Math.round(0.25 * p1.y + 0.75 * p2.y));

            newPoints.add(q);
            newPoints.add(r);
        }
        newPoints.add(points.get(points.size() - 1));
        return newPoints;
    }

}
