import java.util.Objects;

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Driver {
    public static void main(String[] args) {
        Point[] points = new Point[] {
                new Point(1, 2),
                new Point(3, 4),
                new Point(1, 2),
                new Point(5, 6),
                new Point(3, 4)
        };
        int distinctCount = 0;

        for (int i = 0; i < points.length; i++) {
            boolean alreadyAppeared = false;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    alreadyAppeared = true;
                    break;
                }
            }
            if (!alreadyAppeared) {
                distinctCount++;
            }
        }
        System.out.println("Distinct: " + distinctCount);
    }
}