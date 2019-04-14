package polycalc.utils;

import polycalc.logic.Scalar;

public class ScalarUtils {
    public static boolean isZero(Scalar s) {
        if (s == null) {
            throw new IllegalArgumentException("s is null.");
        }

        return s.equals(s.mul(0));
    }

    public static boolean isOne(Scalar s) {
        return !isZero(s) & s.equals(s.mul(s));
    }
}
