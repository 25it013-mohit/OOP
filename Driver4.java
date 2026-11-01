public class Driver4 {

    public static void main(String[] args) {

        Shape[] shapes = {
                new Circle(3),
                new Rectangle(4, 5),
                new Triangle(6, 2),
                new Circle(2)
        };

        double total = 0;
        double max = 0;

        for (Shape s : shapes) {

            double a = s.area();
            System.out.println("Area: " + a);

            total += a;

            if (a > max) {
                max = a;
            }
        }

        System.out.println("Total Area: " + total);
        System.out.println("Largest Area: " + max);
    }
}