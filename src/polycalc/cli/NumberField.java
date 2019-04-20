package polycalc.cli;

import polycalc.logic.Scalar;

public interface NumberField {
    String getPolynomialSplitRegex(String baseRegex);
    Scalar parseScalar(String s);
}
