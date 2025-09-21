public class TestClosestPair {
    public static void main(String[] args) {
        ClosestPair.Point[] points = new ClosestPair.Point[]{
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };

        ClosestPair.Result result = ClosestPair.closestPair(points);
        System.out.printf("Closest points: (%.2f, %.2f) and (%.2f, %.2f), Distance: %.4f%n",
                result.p1.x, result.p1.y, result.p2.x, result.p2.y, result.distance);
    }
}
