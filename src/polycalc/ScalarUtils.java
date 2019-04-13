package polycalc;

public class ScalarUtils {
    public static boolean isZero(Scalar s) {
        if (s == null) {
            throw new IllegalArgumentException("s is null.");
        }

        return s.equals(s.mul(0));
    }
}
