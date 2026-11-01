import java.util.Objects;

class FractionTest {
    private final int num;
    private final int den;
    public FractionTest(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }

        int g = gcd(Math.abs(num), Math.abs(den));
        this.num = num / g;
        this.den = den / g;
    }
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    @Override
    public String toString() {
        return num + "/" + den;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FractionTest fraction = (FractionTest) obj;
        return num == fraction.num && den == fraction.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}
public class Fraction {
    public static void main(String[] args) {
        FractionTest f1 = new FractionTest(1, 2);
        FractionTest f2 = new FractionTest(2, 4);
        FractionTest f3 = new FractionTest(3, 6);
        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println("f3: " + f3);
        System.out.println("f2 equals f1? " + f2.equals(f1));
        System.out.println("f3 equals f1? " + f3.equals(f1));
    }
}