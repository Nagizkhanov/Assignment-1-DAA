import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class ClosestPair {
    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Result {
        public final Point p1, p2;
        public final double distance;

        public Result(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }
    }

    public static Result closestPair(Point[] points) {
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, Comparator.comparingDouble(p -> p.x));

        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));

        return closestPairRecursive(pointsByX, pointsByY);
    }

    private static Result closestPairRecursive(Point[] pointsByX, Point[] pointsByY) {
        int n = pointsByX.length;

        if (n <= 3) {
            return bruteForce(pointsByX);
        }

        int mid = n / 2;
        Point midPoint = pointsByX[mid];

        Point[] leftByX = Arrays.copyOfRange(pointsByX, 0, mid);
        Point[] rightByX = Arrays.copyOfRange(pointsByX, mid, n);

        ArrayList<Point> leftList = new ArrayList<>();
        ArrayList<Point> rightList = new ArrayList<>();
        for (Point p : pointsByY) {
            if (p.x <= midPoint.x) leftList.add(p);
            else rightList.add(p);
        }
        Point[] leftByY = leftList.toArray(new Point[0]);
        Point[] rightByY = rightList.toArray(new Point[0]);

        Result leftResult = closestPairRecursive(leftByX, leftByY);
        Result rightResult = closestPairRecursive(rightByX, rightByY);

        Result best = leftResult.distance < rightResult.distance ? leftResult : rightResult;
        Result splitResult = closestSplitPair(pointsByX, pointsByY, best.distance, midPoint.x);

        return splitResult.distance < best.distance ? splitResult : best;
    }

    private static Result bruteForce(Point[] points) {
        double minDist = Double.MAX_VALUE;
        Point p1 = null, p2 = null;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                    p1 = points[i];
                    p2 = points[j];
                }
            }
        }
        return new Result(p1, p2, minDist);
    }

    private static Result closestSplitPair(Point[] pointsByX, Point[] pointsByY, double delta, double midX) {
        int n = pointsByX.length;
        Point[] strip = new Point[n];
        int count = 0;
        for (Point p : pointsByY) {
            if (Math.abs(p.x - midX) < delta) {
                strip[count++] = p;
            }
        }

        double minDist = delta;
        Point p1 = null, p2 = null;

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count && (strip[j].y - strip[i].y) < minDist; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < minDist) {
                    minDist = dist;
                    p1 = strip[i];
                    p2 = strip[j];
                }
            }
        }

        if (p1 == null) {
            return new Result(null, null, Double.MAX_VALUE);
        } else {
            return new Result(p1, p2, minDist);
        }
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        Result result = closestPair(points);
        System.out.printf("Closest points: (%.2f, %.2f) and (%.2f, %.2f), Distance: %.4f%n",
                result.p1.x, result.p1.y, result.p2.x, result.p2.y, result.distance);
    }
}
