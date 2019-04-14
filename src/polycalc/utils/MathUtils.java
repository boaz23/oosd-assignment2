package polycalc.utils;

public class MathUtils {
    public static int gcd(int m, int n) {
        int r = m % n;
        while (r != 0) {
            m = n;
            n = r;
            r = m % n;
        }

        return n;
    }
}
