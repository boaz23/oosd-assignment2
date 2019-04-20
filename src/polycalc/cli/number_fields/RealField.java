package polycalc.cli.number_fields;

import polycalc.cli.NumberField;
import polycalc.logic.RealScalar;
import polycalc.logic.Scalar;

public class RealField implements NumberField {
    @Override
    public String getPolynomialSplitRegex(String baseRegex) {
        return baseRegex;
    }

    @Override
    public Scalar parseScalar(String s) {
        double coefficient = Double.parseDouble(s);
        return new RealScalar(coefficient);
    }
}
